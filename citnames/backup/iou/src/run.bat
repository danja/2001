java -classpath .;%classpath% com.isacat.iou.parse.test.TestParsing C:\isacat_code\iou\testdata\test.txt

java -classpath .;%classpath% com.isacat.iou.parse.test.TestParsing http://localhost/testdata/test.txt

java -classpath .;%classpath% com.isacat.iou.parse.test.TestParsing C:\isacat_code\iou\testdata\test.htm

java -classpath .;%classpath% com.isacat.iou.parse.test.TestParsing http://localhost/testdata/test.htm

java -classpath .;%classpath%;crimson.jar;jaxp.jar com.isacat.iou.parse.test.TestParsing C:\isacat_code\iou\testdata\test.xml

java -classpath .;%classpath%;crimson.jar;jaxp.jar com.isacat.iou.parse.test.TestParsing http://localhost/testdata/test.xml

java -classpath .;%classpath%;crimson.jar;jaxp.jar com.isacat.iou.parse.test.TestParsing C:\isacat_code\iou\testdata\test.rdf
pause

java -classpath .;%classpath%;crimson.jar;jaxp.jar com.isacat.iou.parse.test.TestParsing http://localhost/testdata/test.rdf