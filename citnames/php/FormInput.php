<?
function HTML_Head()
{          echo "
         <HTML><HEAD>
         <TITLE>Processing Form</TITLE>
         </HEAD>
         <BODY BGCOLOR='#D5D5AB'>";

}

function HTML_Form()
{        echo "
<FORM NAME = 'AccessInterface' METHOD=post ACTION='DataAccess.php'>
Please enter the details you wish to be inserted into the Access Database.<br>
First Name:<input name='FirstName' TYPE='text' SIZE='25'><br>
Last Name:<input name='LastName' TYPE='text' SIZE='25'><br>
Phone Number: <input name='PhoneNumber' TYPE='text' SIZE='25'><br>
<INPUT type='Submit'>
    </form>


";
}

function HTML_Foot()
{
           echo "</body></html>";
}

HTML_Head();
HTML_Form();
HTML_Foot();
?>