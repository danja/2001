KVM Expert System, Version 1.0 
==============================

This application, Kex for short, is a simple expert system for the Palm, 
developed using the J2ME Palm release (CLDC and kjava).

Prerequisites :
	A Palm or compatible device with the KVM from Sun Microsystems loaded 
	(KVM.prc and KVMutil.prc).
	Memo application (as supplied with Palm device).
	A knowledge base is also required, see below.

Contents :
	readme.txt - this file
	Kex.prc - the KVM expert system application
	example.xml - the sample knowledge base
	kex.dtd - the Document Type Definition for Kex knowledge bases

Installation :
	1. Open the file example.xml with a text editor (e.g. Windows notepad) and run 
	the desktop application you normally use for Palm Memos (i.e. Palm Desktop 
	or MS Outlook).
	Select the whole text of example.xml and copy and paste exactly as it reads 
	into your application (as a new 'Memo' in Palm Desktop or a new 'Note' in 
	MS Outlook). 

	2. Use the Palm install tool to load the application file (double-clicking on
	Kex.prc should do this).

	3. Use Hotsync to transfer the application and sample data to your Palm.

Use :
	If you wish to use the logging facility, run KVMutil on the Palm and tick the
	'Save Output' checkbox. Ensure that 'Screen Output' is set to 0 lines.

	Tap the 'Kex' application icon (initially in 'Unfiled') to run the 
	application. Enter the name of the knowledge base you wish to load - the test 
	data provided is called 'example', enter this without punctuation or spaces. 
	Tick the 'Log' checkbox if you wish to use the logging facility (note that 
	this slows the application considerably). Tap the 'Load' button, and the 
	application will take a short while to locate and load the knowledge base. 
	If the application cannot find the example knowledge base, check in Memo that
	there is an entry called Kex "Example". 

	Think of one of the following things : 'mouse', 'bird', 'helicopter' or 
	'stone'. You will be asked a question about the thing you have thought of,
	tap the 'Yes', 'No' or 'Don't Know' as appropriate. Further questions will be 
	presented until the system can reach a conclusion. Tapping 'Quit' will return 
	you to the start screen, from where you may begin another run or exit the 
	application. 	

	The application log can be viewed by running KVMutil and tapping 
	'View Output'.
	

Sample Knowledge Base 
=====================

The knowledge base supplied in example.xml contains the following 'expert' 
information :

There are four different kinds of things in the world : mouse, bird, helicopter
and a stone. You can tell them apart because :

1. A mouse is alive but can't fly.
2. A helicopter can fly but isn't alive.
3. A stone isn't alive and can't fly.

Note that the bird is defined implicitly - if the thing is not one of the 
other three, then (no matter how improbable!) it must be a bird. 

This information is expressed in terms of facts (which may also be questions
or goals) :

Mouse (goal)
Bird	(goal)
Helicopter (goal)
Stone (goal)
Can it fly (question)
Is it alive (question)
alive

The relationship between the facts is expressed in terms of rules :

1.	IF alive AND NOT Can it fly THEN Mouse
2.	IF Can it fly AND NOT alive THEN Helicopter
3.	IF NOT alive AND NOT Can it fly THEN Stone
4.	IF Is it alive THEN alive

The facts and rules above are coded in the XML document example.xml.

Building Knowledge Bases
========================
The expert information should first be expressed in terms of facts and rules 
as above. All the facts that will be used should be listed first. 
The left-hand side of the IF...THEN... statements should contain one or more 
facts, separated by logical AND operators (interpretation of OR isn't 
implemented), and preceded by the logical NOT operator if required. 

The logical operators are strictly applied, and as the reminder the AND 
operation is as follows :

  a      b    (a AND b)
-----------------------
false  false   false
false  true    false
true   false   false
true   true    true

The right-hand side should contain a single fact, preceded by the NOT operator
if required. This information can then be entered as an XML document, either 
using a text editor or preferably using an XML editor with the DTD file 
kex.dtd loaded. The list of all facts must come before the rules. The way in 
which the information is formatted as XML can be seen by comparing the facts 
and rules above with contents of the example.xml file. The size limit of a 
knowledge base has not yet been determined, but larger knowledge bases will 
take substantially more time to process  than small ones. 

The XML file should then have an extra header line added to identify the 
Memo :

Kex "kbname"

where kbname is the name of the knowledge base (the quotes *are* required).
The resulting text file can then be loaded into the Palm Memo database as 
above, and the application run.

D.Ayers, October 2000.

danny@panlanka.net


 




