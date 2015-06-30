<?php


    /*
    
    Appname        	: phpChannel
    Classname    	: readrss
    Author        	: Hans Wolters (hans@linux.gelrevision.nl)
    License        	: GPL
    Version        	: 1.0.3
    
    Description    : 
    
    This class file produces a news ticker from any given Netscape channel file. 
              
    Usage        : 
    
    See the INSTALL file included in the package.

    */

class readrss {

    var $xml_parser;
    var $xml_file;

    var $open_tag ;
    var $close_tag ;
    var $current_tag;

    var $error = false;
    var $lines = 0;

    var $itemstart = false;
    var $searchbox = false;
    var $image     = false;

    var $maxlines = 0;		                  // Optional
    var $show_descriptions              = false;  // Optional
    var $show_channeldescription        = false;  // Optional
    var $show_channeldescription_always = false;  // Optional
    var $show_channelpicture            = false;  // Optional
    var $show_channelsearchbox          = false;  // Optional
    var $show_channelPubDate		= false;  // Optional
    
    var $links = array();
    var $titles = array();
    var $descriptions = array(); 

    var $channeltitle;
    var $channellink;
    var $channeldescription;

    var $searchboxtitle;
    var $searchboxlink;
    var $searchboxname;
    var $searchboxdescription;

    var $channelimagetitle;
    var $channelimagepointto;
    var $channelimagepicurl;
    var $channelimagewidth;
    var $channelimageheight;

    var $head_fgcolor = '#ffffff';
    var $head_bgcolor = '#000000';
    var $body_bgcolor = '#ffffff';
    
    var $browse_method = 'new';    // Spawn a new browser window

    var $pubDate	= "";
    var $imageCacheDir 	= "";

    function destroy() {
        xml_parser_free($this->xml_parser);
    }


    function startElement($parser, $name, $attrs) {
        $this->current_tag = $name;
	if ($this->current_tag == "ITEM") {
		$this->itemstart = true;
	}
	if ($this->current_tag == "TEXTINPUT") {
		$this->searchbox = true;
	}
	if ($this->current_tag == "IMAGE") {
		$this->image = true;
	}
    }

    function endElement($parser, $name) {
	$this->current_tag = $name;
        if ($this->current_tag == "ITEM" && $this->itemstart) {
	    $this->itemstart = false;
	    $this->lines++;
	}
        if ($this->current_tag == "TEXTINPUT" && $this->searchbox) {
	    $this->searchbox = false;
	}
        if ($this->current_tag == "IMAGE" && $this->image) {
	    $this->image = false;
	}
    }

    function characterData($parser, $data) {
	$data = trim($data);

        if ($this->current_tag == "LINK" && $data != "" && substr($data, 0, 7) == "http://") {
		if ($this->searchbox) {
		    	$this->searchboxlink = $data;
		} elseif ($this->image) {
		    	$this->channelimagepointto = $data;
		} elseif ($this->itemstart == true) {
		    	$this->links[$this->lines] .= $data;
		}else{
			$this->channellink = $data;
		}
        } 


        /*
	 * Process other <channel> properties that are not common
	 * to other sections of <channel>
	 */
	if ($data != "") {
	    if ($this->current_tag == "NAME" && $this->searchbox) {
		$this->searchboxname = $data;
	    } elseif ($this->current_tag == "URL" && $this->image) {
		$this->channelimagepicurl = $data;
	    } elseif ($this->current_tag == "WIDTH" && $this->image) {
		$this->channelimagewidth = $data;
	    } elseif ($this->current_tag == "HEIGHT" && $this->image) {
		$this->channelimageheight = $data;
	    } elseif ($this->current_tag == "PUBDATE") {
		$this->pubDate = $data;
	    }
	}


        if ($this->current_tag == "TITLE" && $data != "" ) {
		if ($this->searchbox) {
		    	$this->searchboxtitle = $data;
		} elseif ($this->image) {
		    	$this->channelimagetitle = $data;
		} elseif ($this->itemstart == true) {
		        
		    	$this->titles[$this->lines] .= $data;
		}else{
			$this->channeltitle = $data;
		}
        }
        


        if ($this->current_tag == "DESCRIPTION" && $data != "" ) {
	    if ($this->searchbox) {
		    $this->searchboxdescription = $data;
	    } elseif ($this->itemstart == true) {
		    $this->descriptions[$this->lines] .= $data;
	    }else{
		    $this->channeldescription = $data;
	    }
	}

    }


    function headingColors($fghdr = 'white', $bghdr='black') {
        $this->head_fgcolor = $fghdr;
        $this->head_bgcolor = $bghdr;
    }
    
    function bodyColor($color) {
	$this->body_bgcolor = $color;    
    }
    
    function enableItemDescriptions() {
        $this->show_descriptions = true;
    }
    
    function enableChannelDescription() {
        $this->show_channeldescription = true;
    }
    
    function enableChannelSearchbox() {
        $this->show_channelsearchbox = true;
    }
    
    function enableChannelPicture() {
        $this->show_channelpicture = true;
    }
    
    function enablePubdate() {
        $this->show_channelPubDate = true;
    }

    function setImageCache($cdir) {
        $this->imageCacheDir = $cdir;
    }
    
    function browseMethod($m='new') {
        $this->browse_method = $m;
    }
    
    function ticker() {
	
        $tickerowner = "<a href=\"$this->channellink\" target=\"$this->browse_method\" " .
	               "style=\"text-decoration: none; color: $this->head_fgcolor; text-align: center\">" .
	               trim($this->channeltitle) . "</a>";
	
	if ( $this->maxlines > 0 && ($this->lines > $this->maxlines) ) {
		$this->lines = $this->maxlines;
	}
	
	print "<table cellspacing=0 cellpadding=1 border=0 bgcolor=\"$this->head_bgcolor\" width=180><tr><td>\n";
        print "<table cellspacing=0 cellpadding=1 bgcolor=\"$this->body_bgcolor\" border=0 width=178>\n";

	/*
	Channel::Image
	*/
	
	if ($this->show_channelpicture == false ||
	    ($this->show_channelpicture && !$this->haveImage())) {
	    echo "<tr bgcolor=\"$this->head_bgcolor\"><td>" .
	         "<font size=-1 color=\"$this->head_fgcolor\">" .
		 "<strong>$tickerowner</strong></font></td></tr>\n";
	} else {
	    // Omit the channel title, it usually appears in the image
	    if ($this->imageCacheDir != "") {
	    
	        /*
		If specified we use cached versions of the images.
		This assuming the basename is the same.
		*/ 
		
		$orgimage = $this->channelimagepicurl;
		$this->channelimagepicurl = $this->imageCacheDir . "/" .
	                  substr($this->channelimagepicurl, 
	                  strrpos($this->channelimagepicurl, "/") + 1);
			  
		/*
		HW:
		Check if the picture is around, if not, get it.
		*/
		
		if (!file_exists($this->imageCacheDir . "/" . basename($orgimage))) {
			$fp = fopen($orgimage, r);
			$buff = fread($fp,1000000); 
			
			$fileset = fopen($this->imageCacheDir . "/" . basename($this->channelimagepicurl), w);
			fputs( $fileset, $buff);
		}
	    }
	    echo "<tr bgcolor=\"$this->head_bgcolor\">" 
		 . "<td align=\"center\">";
	    echo "<a href=\"$this->channelimagepointto\">" .
		 "<img src=\"$this->channelimagepicurl\" border=0 ";
            if ($this->channelimagewidth != "" &&
	        $this->channelimageheight != "") {
	        echo "width=$this->channelimagewidth " .
		     "height=$this->channelimageheight "; 
	    }
	    echo "alt=\"$this->channelimagetitle\"></a></td></tr>\n";
	}

	if ($this->show_channeldescription_always || (
	    $this->show_channeldescription && (
	    !$this->show_channelpicture))) {
	    echo "<tr bgcolor=\"$this->head_bgcolor\"><td align=center>" .
	         "<font size=-1 color=\"$this->head_fgcolor\">" .
		 "$this->channeldescription</font></td></tr>\n";
	}
	print "<ul>";    // DEGT
	
	/*
	Show the publication date if specified
	*/
	
	if ($this->show_channelPubDate == true && $this->pubDate != "") {
	    echo "<tr><td align=\"center\"><font size=-1><strong>$this->pubDate</strong></font></td></tr>\n";
	}

	/*
	Now show each of the headlines
	*/
	
        for ($x=0; $x <= $this->lines; $x++) {
            
		$link = trim($this->links[$x]);
		$title = trim($this->titles[$x]);

		if ( $title != "") {
			
                	print "<tr>\n";
                	print "<td align=left>\n";
                	print "<font size=-1><li><a href=\"$link\" target=\"$this->browse_method\">$title</a></li>\n";
                	if ($this->show_descriptions) {
			    print "<br>" . $this->descriptions[$x];
			}
			print "</font></td>\n";
                	print "</tr>\n";
		}
	}
        
        print "</ul>\n";   // DEGT

	if ($this->show_channelsearchbox &&
		$this->searchboxtitle != "" 
		&& $this->searchboxname != "" 
	    	&& $this->searchboxlink != "" 
	    	&& $this->searchboxdescription != "") {
		
	    /*
	    RSS DTD 0.91 supports only GET method
	    */
	    
	    echo "<tr><td align=\"center\"><font size=-1>\n";
	    echo "<form method=\"GET\" action=\"$this->searchboxlink\">\n";
	    echo "$this->searchboxdescription<br>\n";
	    echo "<input name=\"$this->searchboxname\" size=15>\n";
	    echo "<input type=\"submit\" value=\"$this->searchboxtitle\">\n";
	    echo "</form>\n";
	    echo "</font></td></tr>\n";
	}

	print "</table>\n";
	print "</td></tr></table><br>\n";
    }
    
    function haveImage() {
	if ($this->channelimagepicurl != "" && 
	    $this->channelimagepointto != "") {
	    return true;
	}
	return false;
    }
    
    function parse() {

        $this->lines = 0;
        $this->xml_parser = xml_parser_create();
        xml_set_object($this->xml_parser, &$this);
        xml_parser_set_option($this->xml_parser, XML_OPTION_CASE_FOLDING, true);
        xml_set_element_handler($this->xml_parser, "startElement", "endElement");
        xml_set_character_data_handler($this->xml_parser, "characterData");


        if (!($fp = fopen($this->xml_file, "r"))) {
            die("could not open XML input");
        }    

        while ($data = fread($fp, filesize($this->xml_file))) {
            if (!xml_parse($this->xml_parser, $data, feof($fp))) {

                $this->error = true;
            }
        }

        $this->destroy();
        $this->ticker();
    }


}

?>
