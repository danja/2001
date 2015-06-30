require ("connect.php");
require ("createrss.php");

/*
Instance the extended class
*/

$dbcon = new writerss;

/*
Parameters for the databaseconnection
*/
$dbcon->host                    = "host";
$dbcon->db                      = "database";
$dbcon->login                   = "loginname";
$dbcon->password                = "password";

/*
Fields that should be used.
*/      
$dbcon->title_field             = "title fieldname";
$dbcon->descr_field             = "description fieldname";
$dbcon->link_field              = "link name";

/*
Query and database type.

The database type can be either one of the following database servers:

mysql
msql
interbase
mssql
sybase
psql
odbc

*/
$dbcon->sql                     = "Query to get the results";
$dbcon->dbtype                  = "database type";
        
/*
Channel paramaeter.
*/

$dbcon->rss_encoding            = "UTF-8";
$dbcon->use_rss_pubDate		= true;
$dbcon->rss_title               = "Channel title";
$dbcon->rss_link                = "Channel url";
$dbcon->rss_descr               = "Channel description";
$dbcon->rss_lang                = "us";

/*
Image parameters. These are optional.
*/      
$dbcon->rss_imageTitle          = "Image title, used for the ALT tag";
$dbcon->rss_imageUrl            = "picture url";
$dbcon->rss_imageLink           = "url where the picture should point to";
$dbcon->rss_imageWidth          = "51";
$dbcon->rss_imageHeight         = "51";


/*
Textinput parameters. These are optional.
*/
$dbcon->rss_textinputTitle      = "Title for the textinput box";
$dbcon->rss_textinputDescript   = "Description for the textinput box";
$dbcon->rss_textinputName       = "search parameter to be used";
$dbcon->rss_textinputLink       = 'Url for the search';

/*
Triggers for the class. Within the last tag you will have to
mention the filename.
*/

$dbcon->connect();
$dbcon->rss('phpChannel.rss');
