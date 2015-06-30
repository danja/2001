<?
function HTML_Head()
{
 echo "
 <HTML><HEAD>
 <TITLE>Processing Form</TITLE>
 </HEAD>
 <BODY BGCOLOR='#D5D5AB'>";

}
function HTML_Foot()
{
 echo "</body></html>";
}
function Database_Entries($msg)
{
 echo $msg;
}

function Output_Entries()
{
 //Make the connection to the database. The syntax is odbc_connect( 'SYSTEM_DSN' , 'USER', 'PASSWORD' );
 //$cnx will hold the
 //pconnect is used to establish a persistent database connection to the Database
 //until the procedure is completed.
 $cnx = odbc_connect( 'test' , 'root', '' );
 if( ! $cnx )
   {
     Error_handler( "Error in odbc_connect" , $cnx );
   }
 // send a simple odbc query . returns an odbc cursor
 $cur= odbc_exec( $cnx, "select Index,FirstName,LastName,PhoneNumber from People" );
 if( ! $cur )
 {
  Error_handler( "Error in odbc_exec( no cursor returned ) " , $cnx );
 }
 echo "<table border=1><tr><th>Index</th><th>First Name</th><th>Last Name</th><th>Phone Number</th></tr>\n";
 $nbrow=0;   //Local variable to count number of rows
 // fetch the succesive result rows
 while( odbc_fetch_row( $cur ) )
  {
   $nbrow++;
   $Index= odbc_result( $cur, 1 ); // get the field "Index"
   $FirstName= odbc_result( $cur, 2 ); // get the field "FirstName"
   $LastName= odbc_result( $cur, 3 ); // get the field "LastName"
   $PhoneNumber= odbc_result( $cur, 4 ); // get the field "PhoneNumber"

   echo "<tr><td>$Index</td><td>$FirstName</td><td>$LastName</td><td>$PhoneNumber</td></tr>\n";
  }

 echo "<tr><td colspan=2>$nbrow entries </td></tr></table>";
 // close the connection. important if persistent connection are "On"
 odbc_close( $cnx);
}

function Error_Handler( $msg, $cnx )
{
 echo "$msg \n";
 odbc_close( $cnx);
 exit();
}

function Enter_New_Entry($FirstName,$LastName,$PhoneNumber)
{
/*First, we create a connection to our ODBC source. This is done by creating
a connection. Once this is done, we are returned an ODBC connection number.
We use this number to use the ODBC functions within PHP.
*/

 $cnx = odbc_connect( 'test' , 'root', '' );
 if( ! $cnx )
  {
   Error_handler( "Error in odbc_connect" , $cnx );
  }
 $SQL_Exec_String = "Insert Into People (FirstName, LastName, PhoneNumber)
                        Values ('$FirstName', '$LastName', '$PhoneNumber')";

 $cur= odbc_exec( $cnx, $SQL_Exec_String );
 if( ! $cur )
 {
  Error_handler( "Error in odbc_exec( no cursor returned ) " , $cnx );
 }

 odbc_close( $cnx);
}

$strOldEntries = "Previous Entries in database";
$strNewEntries = "Updated version of databse (after entries)";

         HTML_Head();
         Database_Entries($strOldEntries);
         Output_Entries();
         Enter_New_Entry($FirstName,$LastName,$PhoneNumber);
         Database_Entries($strNewEntries);
         Output_Entries();
         HTML_Foot();
?>