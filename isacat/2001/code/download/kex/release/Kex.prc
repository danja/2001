KVM Expert System                 ��r��r��r           applJf��         code   |tAIB�  data    �Taltp   code    tAIN�  2tver�  6Clas�  :Clas��  
 ClasZ�  �Clas\�  �Clas F  'Clas�  .�Clas\�  3�Clas�  AoClas�\  BQClas�  ]�ClasI}  `�Clas�i  c>Clas	J  m}Clas,�  q�Clas�l  y)Clas�  |�Clas��  ~�Clas�  �Clas{"  ��Clas�K  ��Rsrc��  ��Main   �tAIB�  �     Hz �   �Nu/
/O���Ho "Ho $< /<kJav/<applHo < NO�xO� 6 gB�B�Hm��?<pNO��O� `tHx NO�XO$HBg/
NO�\OB'Hx /
NO�'O� 
B�Hj B�B�Hj B�NO�cXO/NO�LO� /
?< // (?/ *NO��O� 6 gB�B�Hm��?<pNO��O� O� &&$_NuJo fN��0p NuNV��/Hn��Hn��Hn��NO��6 O� gA� dHP?< >A� vHPNO��p O� 
`6 n��?( /( ?Hz Hz �����Nu& /.��/.��/.��NO�� O� &N^Nu�__Startup__ *Error launching application StartupCode.c                              @�� �  ~~  >|  x  �  �  �  �  p  x  >|  ~~  � ��  @                                                   T���΁Ca n�ot find KVM on this deviceA�Ca n�ot launch KVM@    (    (                               KVM Wrapper ^1 OK    0   8         ?< ��Kex 1.0 io/KMemoReader    ����  - j
 $ 0 1DATAmemo
  2	 # 3
  4 5 6
  7
  8
  9 :
  0 ;
  <
  =
  > ?
  0 @
  A
 B C
  D E
  F G
  H I J
 K L M
 K N
 # O Q R db Lcom/sun/kjava/Database; <init> ()V Code 
Exceptions close getNumberOfRecords ()J getDocument (Ljava/lang/String;)[B ' ( com/sun/kjava/Database ' S % & T U util/XException Memo unavailable. ' V + ( , W java/lang/StringBuffer " X Y Z [ \ W java/lang/String Reading record  X ] ^ _ V ` a java/lang/Exception ' b Kex c d 
Found a Kex KB :
 ...
 e f [ * g V , - i io/KMemoReader java/lang/Object (III)V isOpen ()Z (Ljava/lang/String;)V ()I append ,(Ljava/lang/String;)Ljava/lang/StringBuffer; toString ()Ljava/lang/String; length (I)Ljava/lang/StringBuffer; 	util/KLog println 	getRecord (I)[B ([BII)V regionMatches (ZILjava/lang/String;II)Z 	ui/KexRun 
getMessage 
setMessage StackMap [B ! # $     % &     ' (  )   F     )*� *� Y� � *� � � � Y	� 
��    h     (  #   *       + (  )        *� � *� �      , -  )        	*� � ��      . /  )  G     ʻ Y� � +� � � L+� =� Y� :6� �� Y� � � � � *� � N� :�-�� I� Y-� :� � .� Y� � � � � � +� � -�� Y� � �  � � � !��*� "���h�  D N Q   h   c  -  #      Q  #      U  #  P    �  #  P    �  #       kb/KCondition    ����  - 
  
   
  
     <init> ()V Code (Lkb/KCondition;)V 
setNegated (Z)V 	isNegated ()Z  	   negated     kb/KCondition kb/KFactBase (Lkb/KFactBase;)V 
getBoolean (Ljava/lang/String;)Z 
putBoolean (Ljava/lang/String;Z)V !          	  
        *� �         
         *+� *� =*� �         
        *� �         
        *� �      kb/KDomain    ����  - �
 D k l
  k	 C m	 C n o
  k	 C p	 C q	 C r s t
  u	 C v
 C w
  x y z {
  |
  } z ~
 C  �
  �
 C �
  �
 C �
  � �
  k �
  �
  �
  �
 � �
 � �
 � � �
 & �
 � � � �
  �
 � � � �
 - u
 & �
 C � �	 � �
 � �
 � �
  �
  �
 � �
  � � � �
  � � � �
  � � � � facts Ljava/util/Vector; rules lookup Lutil/KHashtable; ruleid I factid name Ljava/lang/String; <init> ()V Code (Lkb/KDomain;)V addRule (Lkb/KRule;)I addFact (Lkb/KFact;)I setName (Ljava/lang/String;)V getName ()Ljava/lang/String; getRules ()Ljava/util/Vector; getFacts 	getLookup ()Lutil/KHashtable; getFact (I)Lkb/KFact; 	getFactId (Ljava/lang/String;)I 
Exceptions (Ljava/lang/String;)Lkb/KFact; update (Lkb/KFact;)V (Lkb/KRule;)V getRemainingGoals toString O P java/util/Vector E F G F util/KHashtable H I J K L K java/lang/String untitled O X M N ] \ � � kb/KFact � � � O g � � � � [ \ kb/KRule O � ^ _ O � Y Z � � java/lang/StringBuffer 
Adding rule :  � � � � j Z � � X � � Z java/lang/Integer O � � � � 
Adding fact :  Domain Name =  � � � � util/XException Non-existent fact :  � � b c java/lang/Exception � � � � � � � � � � � � � � 

*** Domain :   *** 

--- Facts ---
 
 uninitialized
 
--- Rules ---
 	
*** End  
kb/KDomain java/lang/Object elements ()Ljava/util/Enumeration; java/util/Enumeration nextElement ()Ljava/lang/Object; 
addElement (Ljava/lang/Object;)V hasMoreElements ()Z (Lkb/KDomain;Lkb/KRule;)V (Lutil/KHashtable;)V setId (I)V append ,(Ljava/lang/String;)Ljava/lang/StringBuffer; (I)Ljava/lang/StringBuffer; 	ui/KexRun 
setMessage kb/KFactBase getValue java/util/Hashtable put 8(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; 	elementAt (I)Ljava/lang/Object; get &(Ljava/lang/Object;)Ljava/lang/Object; intValue ()I java/lang/System err Ljava/io/PrintStream; java/io/PrintStream println getId setElementAt (Ljava/lang/Object;I)V isGoal isKnown StackMap ! C D     E F    G F    H I     J K     L K    M N     O P  Q   I     =*� *� Y� � *� Y� � *� Y� � *� 	*� 
*� Y� � �      O R  Q       �*� *� Y� � *� Y� � *� Y� � *� 	*� 
*� Y� � +� � M� *� � Y,�  � � � ,�  ���+� � N� -�  � :*� � Y*� � -�  ���*� Y+� � � *+� � �    �   D  G  C C z   ^  C C z   r  C C z z   �  C C z z    S T  Q   C     7+*Y� 	`Z� 	� *� +� � Y� �  *� 	`� !� "� #*� 	�      U V  Q   Z     N+*Y� 
`Z� 
� $*� +� *� +� %� &Y*� 
� '� (W� Y� )�  *� 
`� !� "� #*� 
�      W X  Q   (     � Y� *�  +�  � "� #*+� �      Y Z  Q        *� �      [ \  Q        *� �      ] \  Q        *� �      ^ _  Q        *� �      ` a  Q        *� � +� �      b c  Q   V     2*� +� ,N-� � -Y� Y� .�  +�  � "� /�-� &� 0=�    �     (  C   D   d     -  ` e  Q   X     =*+� 1=� N� 3-� 4*� � +� �     2  �       C   2   C     f g  Q        +� 5=*� +� 6�      f h  Q        +� 7=*� +� 6�      i \  Q   o     ;� Y� M*� � N�  -�  � L+� 8� +� 9� ,+� -�  ���,�    �   "    C   z   0  C   z    j Z  Q  �    � Y� :�  *� �  ;�  � "L� Y� +�  <�  � "L=*� � =*� � N� &� Y� +�  -�  � � =�  >�  � "L-�  ��ק � Y� +�  ?�  � "L� Y� +�  @�  � "L*� � =*� � N� &� Y� +�  -�  � � A�  >�  � "L-�  ��ק � Y� +�  ?�  � "L� Y� +�  B�  *� �  ;�  � "L+�    �   v  D  C  z   g  C  z   s  C    �  C    �  C  z   �  C  z   �  C    �  C     kb/KEngine    ����  - �
 > Y Z
  Y	 = [	 = \	 = ]	 = ^	 = _	 = `
 a b
  c d e f
  g
 h i j
 h k
  l
  m
  n
  o d p
  q
 h r
 a s
  t
 = u v
  Y w
  x
  y
  z
 { | }
  ~ 
  z
  �
 a �
  � � �
 * z
 * �
 * �
 h �
  � �
 a �
  �
  � �
 = �
 a � � �
 9 Y �
 a z � � domain Lkb/KDomain; 	factqueue Ljava/util/Vector; asked done Z answer Ljava/lang/String; count I <init> (Lkb/KDomain;)V Code getNextQuestion ()Ljava/lang/String; 
assertFact (Ljava/lang/String;Z)V 
Exceptions � (Lkb/KFact;)V getRemainingGoalsList isDone ()Z 	getAnswer toString J � java/util/Vector A B C B D E F G H I ? @ � � � � � � � � kb/KFact � V � � V java/lang/Integer � � J � � � � � � � � V � � � N � � � � O S java/lang/StringBuffer No rules matching  � � � � X N � � � 

Assertion  � � :  � � � � � � kb/KRule 
Checking rule :  � V � � � V � V Updating :  � S � � � V 
Remaining goals :  T N � � none. java/lang/String 
 
kb/KEngine java/lang/Object util/XException ()V 
kb/KDomain getFacts ()Ljava/util/Vector; elements ()Ljava/util/Enumeration; java/util/Enumeration nextElement ()Ljava/lang/Object; isKnown kb/KFactBase 
isQuestion getId ()I (I)V contains (Ljava/lang/Object;)Z getRuleRefs size hasMoreElements 
addElement (Ljava/lang/Object;)V getValue getFact (Ljava/lang/String;)Lkb/KFact; setAffirmed (Z)V append ,(Ljava/lang/String;)Ljava/lang/StringBuffer; ,(Ljava/lang/Object;)Ljava/lang/StringBuffer; 	util/KLog println (Ljava/lang/String;)V (I)Ljava/lang/StringBuffer; intValue getRules 	elementAt (I)Ljava/lang/Object; isFired trigger (Lkb/KFact;)Lkb/KFact; isGoal 
isAffirmed update removeElement isEmpty getRemainingGoals StackMap ! = >     ? @    A B    C B    D E    F G    H I     J K  L   @     4*� *� Y� � *� Y� � *� *� *� *� *+� 	�      M N  L   �     �M>*� 	� 
� :� Q�  � L+� � ?+� � 8*� � Y+� � � � #�� +M� +� � ,� � � +M�  ���� 
*� �*� � Y,� � � ,� �    �   H    =   d   N  =   d   a  =   d   v  =   d    O P  L         *� 	+� N-� *-� �     Q     R  O S  L  /    e*� Y� � +� � � � Y� � +�  � !� "�� Y� #� *Y� `Z� � $%� +� &� � !� "+� � :� ��  � � '=*� 	� (� )� *N� Y� +� -� ,� � !� "-� -� �-+� .:-� -� �� /� � 0� *� *� � �� Y� 1� � &� � !� "*� 	� 2*� � � =*� � :� '�  � :*� *� � �*� � 3W�  ���*� � 4����  ��� Y� 5� *� 6� � !� "�    �   � 	 ,  =    d  =     d   �  =  *  d   �  =  *  d  	  =  *  d d  #  =  *  d d   -  =  *  d d  7  =  *  d  A  =     d   Q     R  T N  L   �     V*� 	� 7� � 8�� 9Y� :L*� 	� 7� N� (-�  � M� Y� +� ;� ,� � � !L-�  ���+�    �   +    =   &  = 9  d   K  = 9  d    U V  L        *� �      W N  L        *� �      X N  L        *� 	� <�      kb/KFact    ����  - z
 % >	 $ ?	 $ @ A
  >	 $ B
 % C
 $ D E F
 	 G H
  I
  J K
  >
 % L
  M N
  O	 % P
  Q
 % R S
 % T U
  V W
  X Y Z [ \	 ] ^
 _ ` b c 
stateknown Z affirmed rulerefs Ljava/util/Vector; <init> ()V Code (Lkb/KFact;)V setKnown (Z)V isKnown ()Z setAffirmed 
isAffirmed 
Exceptions setRuleRefs (Ljava/util/Vector;)V getRuleRefs ()Ljava/util/Vector; 
addRuleRef (I)V toString ()Ljava/lang/String; + , & ' ( ' java/util/Vector ) * + d 8 9 util/XException Unknown fact state.  + e java/lang/Integer + ; f g java/lang/StringBuffer h i j k   j l m n < = o 2 ? p 2 * q i  [ j r ]  [TRUE]  [FALSE] java/lang/Exception s t u v w g y kb/KFact kb/KFactBase (Lkb/KFactBase;)V (Ljava/lang/String;)V 
addElement (Ljava/lang/Object;)V getId ()I append (I)Ljava/lang/StringBuffer; ,(Ljava/lang/String;)Ljava/lang/StringBuffer; value Ljava/lang/String; 
isQuestion isGoal size ,(Ljava/lang/Object;)Ljava/lang/StringBuffer; java/lang/System err Ljava/io/PrintStream; java/io/PrintStream println StackMap java/lang/String ! $ %     & '    ( '    ) *   
  + ,  -   &     *� *� *� *� Y� � �      + .  -   /     #*+� *� *� *� Y� � *+� � �      / 0  -        *� �      1 2  -        *� �      3 0  -        *� *� �      4 2  -   3     *� � � 	Y
� �*� �    x       $   5     	  6 7  -        *+� �      8 9  -        *� �      : ;  -        *� � Y� � �      < =  -  1     ƻ Y� *� � � *� � � L*� � � Y� +� � � L*� � � Y� +� � � L*� � Y=� #� Y� +� � *� � � � L*� � @*� � � Y� +� � � L� "� Y� +�  � � L� N� "-� #+�  � � � !  x   Q  9  $ a   T  $ a   �  $ a   �  $ a   �  $ a  ! �  $ a    kb/KFactBase    ����  - G
  4 5
  4	  6
  7	  8
  9
  :
  ;	  <
  =
  > ?
  @
  A B C D avpairs Lutil/KHashtable; value Ljava/lang/String; id I <init> ()V Code (Lkb/KFactBase;)V 
setAVPairs (Lutil/KHashtable;)V 
getAVPairs ()Lutil/KHashtable; setValue (Ljava/lang/String;)V getValue ()Ljava/lang/String; setId (I)V getId ()I 
putBoolean (Ljava/lang/String;Z)V 
getBoolean (Ljava/lang/String;)Z setGoal (Z)V isGoal ()Z setQuestion 
isQuestion toString   util/KHashtable   # $        ' (   ) E + F goal ) * + , question kb/KFactBase java/lang/Object (Ljava/lang/Object;Z)V (Ljava/lang/Object;)Z !                                     *� *� Y� � �            ;     /*� *� Y� � *+� � *� Y+� � � *+� 	� 
�                 *+� �                  *� �      ! "          *+� �      # $          *� �      % &          *� 
�      ' (          *� 
�      ) *          
*� +� �      + ,          	*� +� �      - .          *� �      / 0          *� �      1 .          *� �      2 0          *� �      3 $          *� �      kb/KRule    ����  - �
 7 `	 6 a	 6 b c
  `	 6 d	 6 e	 6 f g
 	 `	 6 h	 6 i	 6 j
 6 k
 6 l
 6 m
  n
 6 o
 6 p
 q o
 r s t
  u
 v p
  w
 x y z {
  |
  }
 ~  �	 � �
 � �
 x �
 r � �
 $ ` �
 $ �
 $ �
 $ �
 � �
 x �
 6 �
 ~ � z � �
 	 � � �
 q � � � � � domain Lkb/KDomain; id I factid 
conditions Lutil/KHashtable; negated Z fired 	outstring Ljava/lang/String; dirty <init> (Lkb/KDomain;)V Code (Lkb/KDomain;Lkb/KRule;)V getConditionsTable ()Lutil/KHashtable; setFact (Lkb/KFact;)V getFact ()Lkb/KFact; 	getFactId ()I addCondition (Lkb/KCondition;)V setId (I)V getId 
setNegated (Z)V 	isNegated ()Z setFired isFired trigger (Lkb/KFact;)Lkb/KFact; toString ()Ljava/lang/String; E � : ; < ; util/KHashtable = > ? @ A @ java/lang/String B C D @ 8 9 E F O P I J E � U P X Y � � M � java/lang/Integer E T � � � � � � � � � � P � � � � Y java/lang/Exception � � � � � � � � � � java/lang/StringBuffer Updating :  � � � � ^ _ � � � � P M N � W � Y uninitialized � �  IF  NOT  � _  AND   THEN  kb/KRule java/lang/Object ()V (Lutil/KHashtable;)V kb/KFactBase 
kb/KDomain (I)Lkb/KFact; kb/KCondition 
putBoolean (Ljava/lang/Object;Z)V java/util/Hashtable keys ()Ljava/util/Enumeration; java/util/Enumeration nextElement ()Ljava/lang/Object; intValue 
getBoolean (Ljava/lang/Object;)Z kb/KFact 
isAffirmed java/lang/System err Ljava/io/PrintStream; java/io/PrintStream println (Ljava/lang/Object;)V remove &(Ljava/lang/Object;)Ljava/lang/Object; update (Lkb/KRule;)V append ,(Ljava/lang/String;)Ljava/lang/StringBuffer; ,(Ljava/lang/Object;)Ljava/lang/StringBuffer; 	util/KLog (Ljava/lang/String;)V size setAffirmed hasMoreElements valueOf (I)Ljava/lang/String; getValue StackMap ! 6 7     8 9    : ;    < ;    = >    ? @    A @    B C    D @     E F  G   E     9*� *� *� *� Y� � *� *� *� 	Y� 
� *� *+� �      E H  G   9     -*+� *,� � *� Y,� � � *,� � *,� � �      I J  G        *� �      K L  G        *� *+� � �      M N  G        *� *� � �      O P  G        *� �      Q R  G   (     *� *� � Y+� � +� � �      S T  G        *� *� �      U P  G        *� �      V W  G        *� *� �      X Y  G        *� �      Z W  G        *� *� �      [ Y  G        *� �      \ ]  G  �     �+� 666*� � :	� �	�  :

� � 6*� 
� 6� � =� �+� �6� :�  � !� B*� 
� "W*� *� *� #� $Y� %&� '*� (� )� **� � +� *� � *� *� � *� �6*� ,:� -�	� . ��K�  G P S   �   �   
 6 ~     z   A  6 ~   z 7   B  6 ~   z 7  S  6 ~  z 7   ]  6 ~  z 7   �  6 ~  z 7   �  6 ~  z 7   � 
 6 ~     z    ^ _  G  
    �*� � /�*� � *� �**� � 0� � $Y� %*Z� � '1� '� )� *� � :� . � ��  N-� � <*� -� =� � $Y� %*Z� � '2� '� )� � $Y� %*Z� � '*� � � 3� '� )� � w� $Y� %*Z� � '4� '� )� �  N-� � <*� -� =� � $Y� %*Z� � '2� '� )� � $Y� %*Z� � '*� � � 3� '� )� � . ���� $Y� %*Z� � '5� '� )� *� � � $Y� %*Z� � '2� '� )� � $Y� %*Z� � '*� *� � � 3� '� )� *� *� �    �   c    6     6   �  6 7 z   �  6    z   �  6 7 z     6    z  e  6    z    Kex     ����  - 
   
   
    <init> ()V Code main ([Ljava/lang/String;)V   	ui/KexRun Kex java/lang/Object !            	        *� � Y� W�     	 
   	        	� Y� W�      ui/KexRun    ����  -}	 r �	 r �
 r �	 r �	 r �	 r �	 r �
 r �	 r �	 r �	 r �	 r �	 r �	 r �	 r �	 r �	 r �	 r �	 r 	 r	 r	 r	 r
 s
 	 r	
 	 r

 
 !	 r
 $	 r
 '

 +
 /
 2	 r
 6 !" �	 r#$%&'	 r()	 r* �	 r+
 r,
-	 r.
 s/	 r0
 21	 r2
 r3
 r4
5
 23      �
678	9:
;<       d
=
 +3
 /3
 63
 />?
 ^
 ^@A
 ^B
CD	 rE
CFG
HI
HJKL
HMN
HOPQR
HSUV ErrorHandler InnerClasses ConcludeHandler QueryHandler StartHandler LoadHandler ERROR I ConstantValue���� LOAD     START    QUERY    CONCLUDE    current old message Ljava/lang/String; 	errorText loadHandler Lui/KexRun$LoadHandler; startHandler Lui/KexRun$StartHandler; queryHandler Lui/KexRun$QueryHandler; concludeHandler Lui/KexRun$ConcludeHandler; errorHandler Lui/KexRun$ErrorHandler; master Lkb/KDomain; domain engine Lkb/KEngine; kbname question concludeText g Lcom/sun/kjava/Graphics; 
buttonLoad Lcom/sun/kjava/Button; buttonLoadExit textFieldKB Lcom/sun/kjava/TextField; textBoxTitle Lcom/sun/kjava/TextBox; checkBoxLog Lcom/sun/kjava/CheckBox; buttonStart buttonStartExit textBoxStart textBoxQuery 
buttonQuit buttonUnknown buttonNo 	buttonYes textBoxConclude buttonOK textBoxError textBoxMessage runcount <init> ()V Code paint 
setMessage (Ljava/lang/String;)V 
getMessage ()Ljava/lang/String; error process ()Z (Z)Z 
access$000 &(Lui/KexRun;)Lcom/sun/kjava/TextField; 	Synthetic 
access$100 %(Lui/KexRun;)Lcom/sun/kjava/CheckBox; 
access$200 %(Lui/KexRun;)Lcom/sun/kjava/Graphics; 
access$300 #(Lui/KexRun;)Lcom/sun/kjava/Button; 
access$402 1(Lui/KexRun;Ljava/lang/String;)Ljava/lang/String; 
access$502 %(Lui/KexRun;Lkb/KDomain;)Lkb/KDomain; 
access$400 (Lui/KexRun;)Ljava/lang/String; 
access$600 ()I 
access$500 (Lui/KexRun;)Lkb/KDomain; 
access$700 $(Lui/KexRun;)Lcom/sun/kjava/TextBox; 
access$602 (I)I 
access$800 
access$900 access$1000 access$1104 (Lui/KexRun;)I access$1202 access$1302 access$1402 %(Lui/KexRun;Lkb/KEngine;)Lkb/KEngine; access$1300 access$1500 (Lui/KexRun;)Z access$1600 %(Lui/KexRun;)Lui/KexRun$QueryHandler; access$1700 access$1800 access$1900 access$2000 (Lui/KexRun;Z)Z access$2100 access$2200 <clinit> � � � � � � � � � � � � � � � � � � � � � � � { � � � � � � � � � { � � � � � � � � � � � � � � ui/KexRun$LoadHandler �W � � ui/KexRun$StartHandler � � ui/KexRun$QueryHandler ui/KexRun$ConcludeHandler � � ui/KexRun$ErrorHandler � � 
kb/KDomain noneXYZ com/sun/kjava/Button Load �[ Exit com/sun/kjava/TextField Knowledge Base  �\ com/sun/kjava/TextBox KVM Expert System � � com/sun/kjava/CheckBox Log �] Start   � � Quit 
Don't Know No Yes � � OK � � � � � �^ � � {_` � �a � � � � � � �bcdef java/lang/Exceptionghijklm`n � java/lang/StringBufferop ?q �rk � � �s � Error :
tu �v � Solution is :  
***************w � ,No further questions.

Possible solutions : x � 
+++ Selected question :  ?
Response :  Processing, please wait...yz| 	ui/KexRun com/sun/kjava/Spotlet (Lui/KexRun;)V com/sun/kjava/Graphics getGraphics ()Lcom/sun/kjava/Graphics; (Ljava/lang/String;II)V (Ljava/lang/String;IIII)V (IILjava/lang/String;)V clearScreen register (I)V setText 
drawBorder 	(IIIIII)V java/lang/Thread sleep (J)V java/lang/System err Ljava/io/PrintStream; java/io/PrintStream println (Ljava/lang/Object;)V 	playSound setFocus append ,(Ljava/lang/String;)Ljava/lang/StringBuffer; toString 	util/KLog print 
kb/KEngine isDone 	getAnswer getNextQuestion getRemainingGoalsList 
assertFact (Ljava/lang/String;Z)V StackMap java/lang/String ! r s   '  z {  |    }  ~ {  |      � {  |    �  � {  |    �  � {  |    � 
 � {   
 � {   
 � �   
 � �    � �    � �    � �    � �    � �    � �    � �    � �    � �    � �    � �    � �    � �    � �    � �    � �    � �    � �    � �    � �    � �    � �    � �    � �    � �    � �    � �    � �    � �    � {   #  � �  �  5    �*� *� Y*� � *� Y*� � *� Y*�  � *� !Y*� "� #*� $Y*� %� &*� 'Y� (� *� 'Y� (� 
*)� *� *� *� +Y, �� -� *� +Y.s �� -� *� /Y0N �� 1� *� 2Y3%d� 4� 5*� 6Yi7� 8� *� +Y9 �� -� *� +Y.s �� -� *� 2Y:Fd� 4� *� 2Y;n(� 4� <*� +Y=l �� -� *� +Y>0P� -� *� +Y?lP� -� *� +Y@P� -� *� 2Y: � �� 4� A*� +YB: �� -� *� 2Y:
2 � �� 4� C*� 2YD:n<� 4� E*� � F*W� G� � H� �    x����      f   $   /   :   P*� � I� L*� � I� A*� <*� J� K*� � I� +*� A*� � K*� #� I� *� C� L� K*� &� I*� M� Q� N� :*W� G*W2 �(� O*� E� N� K*� E� P Q� S� L� U+� V W� S� L� U+� V� � H���*W� Y�� kqt T|�� T {   t �  r  �  r  �  r  �  r    r  &  r  8  r  ?  r  t  r  T|  r  �  r  T�  r    � �  �  �    6*W� G� �   -����        $   l   �   �*� 5� P*Wi� O*� � Z*� � Z*� � [*� � \*� � ]� F*W� Y� �*� 5� P*Wi� O*� � P*� � Z*� � Z*W� Y� �*Ws-� O*� <� ^Y� _*� J� `a� `� b� K*� <� P*� � Z*� � Z*� � Z*� � Z*W� Y� 4*� A� P*� � Z*W� Y� � L� c*� C� P*� � Z*W� Y�   {   8  ,  r   t  r   �  r    r    r  5  r   	 � �  �   ,     *� d� d� *� e�   {      T   	 � �  �         � d�     	 � �  �   1     %� ^Y� _f� `*� `� b� L� L� c� d� �      � �  �   �     �� F*� 	� g� C� *� 	� h� '� ^Y� _*Z� � `i� `*� 	� h� `� b� *� � cj� c�**� 	� k� J*� J� I� ^Y� _*Z� � `l� `� b� � ^Y� _*Z� � `*� 	� m� `� b� *� � c� �� ^Y� _n� `*� J� `o� `� b� e�   {     @  r   N  r   �  r    � �  �   Z     'p� F*� 	*� J� q� M� U,� V�� F*� �     T {       r  T   r    � �  �        *� �      � �  �        *� �      � �  �        *� �      � �  �        *� �      � �  �        *+Z� �      � �  �        *+Z� �      � �  �        *� �      � �  �         � �      � �  �        *� �      � �  �        *� �      � �  �        Y� �      � �  �        *� �      � �  �        *� �      � �  �        *� �      � �  �        *Y� `Z� �      � �  �        *+Z� �      � �  �        *+Z� 
�      � �  �        *+Z� 	�      � �  �        *� 
�      � �  �        *� �      � �  �        *� �      � �  �        *� �      � �  �        *� �      � �  �        *� �      � �  �        *� �      � �  �        *� �      � �  �        *� �      � �  �         � �      ui/KexRun$ConcludeHandler    ����  - 2
 
 	 	 
  
  
  
  
  
    ! this$0 Lui/KexRun; 	Synthetic <init> (Lui/KexRun;)V Code penDown (II)V  "   # $ % & ' ( ) * + , - . / 0 " ui/KexRun$ConcludeHandler ConcludeHandler InnerClasses com/sun/kjava/Spotlet ()V 	ui/KexRun access$2200 #(Lui/KexRun;)Lcom/sun/kjava/Button; com/sun/kjava/Button pressed (II)Z 
access$200 %(Lui/KexRun;)Lcom/sun/kjava/Graphics; com/sun/kjava/Graphics 	playSound (I)V 
access$602 (I)I paint StackMap ! 	 
                      
*� *+� �     !       G     (*� � � � *� � W� � W*� � �    1     '  	    ui/KexRun$ErrorHandler    ����  - 2
 
 	 	 
  
  
  
  
  
    ! this$0 Lui/KexRun; 	Synthetic <init> (Lui/KexRun;)V Code penDown (II)V  "   # $ % & ' ( ) * + , - . / 0 " ui/KexRun$ErrorHandler ErrorHandler InnerClasses com/sun/kjava/Spotlet ()V 	ui/KexRun access$2200 #(Lui/KexRun;)Lcom/sun/kjava/Button; com/sun/kjava/Button pressed (II)Z 
access$200 %(Lui/KexRun;)Lcom/sun/kjava/Graphics; com/sun/kjava/Graphics 	playSound (I)V 
access$602 (I)I paint StackMap ! 	 
                      
*� *+� �     !       G     (*� � � � *� � W� � W*� � �    1     '  	    ui/KexRun$LoadHandler    ����  - �
 6 A	 5 B
 C D
 E F
 C G
 H I
 C J
 K L
 H M	 N O
 C P
 Q I
 6 R
 E S
 C T
 E U V
  A
 C W
  X
 C Y
 C Z
 C [ \
  A ]
  ^ _
  `
 C a
 C b c
 d e f
 g h
 d `
 N i j
 d k
 l m
  n o
 N p
 d q r s
 d t u
 C v
 C w x
 y z { ~ this$0 Lui/KexRun; 	Synthetic <init> (Lui/KexRun;)V Code keyDown (I)V penDown (II)V :  7 8 � � � � � > � � � � � � � � � > � @ � � � � � � �  � � � � �  xxml/KexDocumentHandler � � � � � � � � � � java/lang/StringBuffer " � � " not found. � � � � � � Knowledge Base " � � �  loaded � � � � � 
 � � � � � � �  rules,  � � � �  facts  
including  � �  goals. � � � � 
Exit Pressed. � � > ui/KexRun$LoadHandler LoadHandler InnerClasses com/sun/kjava/Spotlet ()V 	ui/KexRun 
access$000 &(Lui/KexRun;)Lcom/sun/kjava/TextField; com/sun/kjava/TextField handleKeyDown 
access$100 %(Lui/KexRun;)Lcom/sun/kjava/CheckBox; com/sun/kjava/CheckBox pressed (II)Z 
access$200 %(Lui/KexRun;)Lcom/sun/kjava/Graphics; com/sun/kjava/Graphics 	playSound handlePenDown 	util/KLog logging Z 
access$300 #(Lui/KexRun;)Lcom/sun/kjava/Button; com/sun/kjava/Button 
unregister getText ()Ljava/lang/String; 
access$402 1(Lui/KexRun;Ljava/lang/String;)Ljava/lang/String; 	loseFocus 
access$400 (Lui/KexRun;)Ljava/lang/String; load  (Ljava/lang/String;)Lkb/KDomain; 
access$502 %(Lui/KexRun;Lkb/KDomain;)Lkb/KDomain; 
access$600 ()I 
access$500 (Lui/KexRun;)Lkb/KDomain; append ,(Ljava/lang/String;)Ljava/lang/StringBuffer; toString error (Ljava/lang/String;)V 
access$700 $(Lui/KexRun;)Lcom/sun/kjava/TextBox; 
kb/KDomain getName com/sun/kjava/TextBox setText println getRules ()Ljava/util/Vector; java/util/Vector size (I)Ljava/lang/StringBuffer; print getFacts getRemainingGoals 
access$602 (I)I 
access$800 java/lang/System exit StackMap ! 5 6     7 8     : ;  <        
*� *+� �     ! = >  <        *� � � �     ! ? @  <      �*� � � � )*� � W� *� � � 	� 
� � � 
*� � � �A*� � W� *� *� *� � � � W*� � � � Y� N*� -*� � � � WN� � �*� � � %� Y� � *� � � � � � �*� � � Y�  � *� � � !� � "� � � #*� � � $� %� Y� &� *� � � '� (� )*� � � +� Y� *� � � ,� (� )-� � � +� Y� .� *� � � /� (� )0� � � %� 1W*� � W� � '*� � 2� � *� � W� 3� %� 4�    �   R  1  5   2  5  5  5   �  5   �  5  �  5  �  5    ui/KexRun$QueryHandler    ����  - R
  	  
   
 ! "
  #
 $ %
  &
  ' (
 ) *
  +
  , -
  .
  / 0
  1
  2 3 6 this$0 Lui/KexRun; 	Synthetic <init> (Lui/KexRun;)V Code penDown (II)V  7   8 9 : ; < = > ? @ A B C D E : Don't Know. F G H I J K : Yes. L M N : No. O 7 P B ui/KexRun$QueryHandler QueryHandler InnerClasses com/sun/kjava/Spotlet ()V 	ui/KexRun access$1700 #(Lui/KexRun;)Lcom/sun/kjava/Button; com/sun/kjava/Button pressed (II)Z 
access$200 %(Lui/KexRun;)Lcom/sun/kjava/Graphics; com/sun/kjava/Graphics 	playSound (I)V 
access$602 (I)I access$1800 	util/KLog println (Ljava/lang/String;)V access$1500 (Lui/KexRun;)Z access$1900 access$2000 (Lui/KexRun;Z)Z access$2100 paint register StackMap !                        
*� *+� �     !           �>*� � � � *� � W� � W�*� � � � *� � W� 	� 
*� � >� X*� � � �  *� � W� � 
*� � >� ,*� � � � *� � W� � 
*� � >*� � � *� �    Q   >  #     N     z     �     �      ui/KexRun$StartHandler    ����  - �
 % .	 $ /
 0 1
 2 3
 0 4
 5 6 7
 8 9
 : ;
 0 < =
 0 >
 % ? @
  . A
  B
 0 C
  D E
  F G
 0 H I
 0 J
  K
 0 L M
 0 N
  K
 0 O
 0 P
 0 Q
 0 R
 % S T W this$0 Lui/KexRun; 	Synthetic <init> (Lui/KexRun;)V Code penDown (II)V ) X & ' Y Z [ \ ] ^ _ ` a b c 
Exit Pressed. d e f g h c i [ Initialising, please wait... j f k X java/lang/StringBuffer 

**** Run  l m n o l p  **** q r 
*** Conclusions ***

 s t 
kb/KDomain u v ) w x y 
kb/KEngine z v { | } ~  � � � � c ui/KexRun$StartHandler StartHandler InnerClasses com/sun/kjava/Spotlet ()V 	ui/KexRun 
access$900 #(Lui/KexRun;)Lcom/sun/kjava/Button; com/sun/kjava/Button pressed (II)Z 
access$200 %(Lui/KexRun;)Lcom/sun/kjava/Graphics; com/sun/kjava/Graphics 	playSound (I)V 	util/KLog println (Ljava/lang/String;)V java/lang/System exit access$1000 
setMessage 
unregister append ,(Ljava/lang/String;)Ljava/lang/StringBuffer; access$1104 (Lui/KexRun;)I (I)Ljava/lang/StringBuffer; toString ()Ljava/lang/String; access$1202 1(Lui/KexRun;Ljava/lang/String;)Ljava/lang/String; 
access$500 (Lui/KexRun;)Lkb/KDomain; (Lkb/KDomain;)V access$1302 %(Lui/KexRun;Lkb/KDomain;)Lkb/KDomain; access$1300 access$1402 %(Lui/KexRun;Lkb/KEngine;)Lkb/KEngine; access$1500 (Lui/KexRun;)Z 
access$602 (I)I access$1600 � QueryHandler %(Lui/KexRun;)Lui/KexRun$QueryHandler; register ui/KexRun$QueryHandler StackMap ! $ %     & '     ) *  +        
*� *+� �     ! , -  +   �     �*� � � � *� � W� � � 	� �*� � 
� � �*� � W� � *� � Y� � *� � � � � � *� � W*� � Y*� � � � W*� � Y*� � � � W*� �  W� !W*� � "� #�    �     '  $   �  $    util/KHashtable    ����  - 1
  
    
  
       
 	  ! " <init> ()V Code (Lutil/KHashtable;)V 
putBoolean (Ljava/lang/Object;Z)V 
getBoolean (Ljava/lang/Object;)Z   # $ % & ' ( ) * + , - true false java/lang/String .  0 util/KHashtable java/util/Hashtable keys ()Ljava/util/Enumeration; java/util/Enumeration nextElement ()Ljava/lang/Object; get &(Ljava/lang/Object;)Ljava/lang/Object; put 8(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; hasMoreElements ()Z equals StackMap java/lang/Object !                    *� �            \     (*� +� N� -�  M*,+,� � W-�  ���    /   "                         F     � *+� W� *+� W�    /                          N     *+� M,� �,� 	� 
� ��    /                         util/KLog    ����  - !
  	  	  
  
     logging Z <init> ()V Code print (Ljava/lang/String;)V println <clinit> 
   	           	util/KLog java/lang/Object java/lang/System out Ljava/io/PrintStream; java/io/PrintStream StackMap java/lang/String !      	  	     
           *� �     	       +     � � 
� *� �              	       +     � � 
� *� �                           � �      util/XException    ����  - 
  	
 
    <init> ()V Code (Ljava/lang/String;)V      util/XException java/lang/Exception 	ui/KexRun error !                    *� �                 	*� +� �      xxml/KexDocumentHandler    ����  - �
 J o p
  o	 I q	 I r s
  o	 I t u
 	 o v	 w x
 y z {
  o |
  } ~
  
 � �
 	 �
 	 � � � �
  o
  �
  �
  � �
 � � �
  �	 I � �
 � � �
  � Z
 % � �
 ) o	 I �
 � � \ �
 . �	 I �
  � _ �
 3 o	 I �
 � �
  �
 ) �	 I � �
  �
 . �
 . �
 ) �
  �
 � �
 � �
 . � � �
 C �
  �
 . �
  � � � � NONE I ConstantValue     FACT    RULEFACT    	CONDITION    domain Lkb/KDomain; nesting tag fact 
Lkb/KFact; rule 
Lkb/KRule; rulefact 	condition Lkb/KCondition; avpairs Lutil/KHashtable; <init> ()V Code load  (Ljava/lang/String;)Lkb/KDomain; startDocument endDocument startElement &(Ljava/lang/String;Lutil/KHashtable;)V 
characters (Ljava/lang/String;)V 
endElement c d 
kb/KDomain V W Y M util/KHashtable a b io/KMemoReader java/lang/Exception � � � � � � java/lang/StringBuffer Searching for " � � "
 � � � � m � � � d Loading  ...
 xxml/KParser � � � � � d Start Document � � m 
End Document c � X M name � � � java/lang/String � m � � kb/KFact Z [ � � � kb/KRule c � \ ] � � kb/KCondition _ ` � m � � c � ^ [ negated � � � � � � � � � � � � � � util/XException Interpretation error. c m � � � � � � � xxml/KexDocumentHandler java/lang/Object xxml/XDocumentHandler java/lang/System err Ljava/io/PrintStream; java/io/PrintStream println (Ljava/lang/Object;)V append ,(Ljava/lang/String;)Ljava/lang/StringBuffer; toString ()Ljava/lang/String; 	ui/KexRun 
setMessage getDocument (Ljava/lang/String;)[B close 	setSource ([B)V setDocumentHandler (Lxxml/XDocumentHandler;)V parse 	util/KLog (Lutil/KHashtable;)V java/util/Hashtable get &(Ljava/lang/Object;)Ljava/lang/Object; setName equals (Ljava/lang/Object;)Z kb/KFactBase 
setAVPairs (Lkb/KDomain;)V addRule (Lkb/KRule;)I setValue getFact (Ljava/lang/String;)Lkb/KFact; (Lkb/KFact;)V 
getBoolean 
setNegated (Z)V getId ()I 
addRuleRef (I)V update setId addCondition (Lkb/KCondition;)V addFact (Lkb/KFact;)I setFact (Lkb/KRule;)V StackMap [B ! I J  K   L M  N    O  P M  N    Q  R M  N    S  T M  N    U  V W    X M    Y M    Z [    \ ]    ^ [    _ `    a b     c d  e   ,      *� *� Y� � *� *� Y� � �      f g  e   �     �M� 	Y� 
M� N� -� � Y� � +� � � � ,+� N,� -� �� Y� � +� � � � � Y� :-� N*� � � *� �   
    �   3    I %     I % 	   @  I % 	 �    h d  e        � �      i d  e         � �      j k  e  E     �*� Y,� !� *Y� "`� "*� "�    �            :   �*� #� $N-� �-� %:*� � &� �+'� (� *� *� )Y� *� +*� +*� � ,+-� (� X*� .Y*� � /� 0*� *� 0� 1W� :*� +'� (� *� +2� (� *� *� 3Y� 4� 5*� 5*� � ,�    �   \  4  I %    T  I %    x  I %    �  I %    �  I %    �  I %     l m  e  �     �M*� �    �            %   e*� ++� 6� �*� )Y*� +� 7� 8� 9*� :� ;� *� 0� <� s*� 0� <� hN� -� � ]*� +� 7M� N� -� ,*� 0� =� >*� ,� ?*� 5+� 6*� 5,� @� A*� 0*� 5� B� � CYD� E�N� -� �  + ] `  k t w  � � �   �   � 
    I %   +  I %   U  I %   `  I %   k  I %   w  I %     I % )   �  I %   �  I %   �  I % )    n m  e   �     R*Y� "d� "+'� (� *� � *� *� +� FW+-� (� $*� 0*� 9� G� M� ,� *� *� 0� H�  0 ; >   �   5  '  I %   >  I %   F  I %   Q  I %    xxml/KParser    ����  - 
 % B	 $ C	 $ D	 $ E	 $ F  ��
 $ G
 $ H I
 	 B
 	 J
 $ K L
  M
 $ N
  B O
  B P Q
 $ R
 $ S T U
 	 V
 	 W
  X
 $ Y
 Z [ P \
 Z ] P ^ P _ P `	 a b
 c d e f handler Lxxml/XDocumentHandler; ch C source [B ch_count I END ConstantValue <init> ()V Code 	setSource ([B)V setDocumentHandler (Lxxml/XDocumentHandler;)V read ()C isWhite (C)Z 	readWhite isLetterOrDigit 
readString ()Ljava/lang/String; readWord parse readDoc 0 1 * + & ' , - ( ) 7 8 9 : java/lang/StringBuffer g h < : java/lang/String 0 i A 1 util/KHashtable j k 1 ; 1 ? > util/XException !parsing - unexpected character :  g l m > 0 n = > o p q r s t 1 u n v n w 1 x y z { | } xxml/KParser java/lang/Object append (C)Ljava/lang/StringBuffer; (Ljava/lang/StringBuffer;)V xxml/XDocumentHandler startDocument ,(Ljava/lang/String;)Ljava/lang/StringBuffer; toString (Ljava/lang/String;)V java/util/Hashtable put 8(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; startElement &(Ljava/lang/String;Lutil/KHashtable;)V clear 
characters 
endElement endDocument java/lang/System err Ljava/io/PrintStream; java/io/PrintStream println (Ljava/lang/Object;)V StackMap ! $ %     & '    ( )    * +    , -    . )  /       0 1  2        *� �      3 4  2        *+� �      5 6  2        *+� �      7 8  2   U     /*� *� �� **� *Y� Z`� 3�� � 	*� *� �    ~     $  $   *  $    9 :  2   W     $ � 	� � 
� 	z� � �    ~   !    $   "  $   #  $   ; 1  2   U     /**� � � &*� � �*� W**� � � *� ���    ~       $   .  $    < :  2   d     (A� 	Z� a� 	z� 0� 9� ��    ~   *    $     $   $  $   &  $    = >  2   d     8� 	Y� 
L� +*� � W*� W**� � ���**� � ��߻ Y+� �    ~       $ 	     $ 	    ? >  2   Y     -� 	Y� 
L� +*� � W*� W**� � ��� Y+� �    ~       $ 	     $ 	    @ 1  2        
*� *� �      A 1  2  �    o� Y� L� Y� :*� �  �5*� *� <�#*� W**� � � �*� L*� � ~*� M*� *� =� !� Y� 	Y� 
� *� � � � �*� *� W*� "� !� Y� 	Y� 
� *� � � � �*� W*� N*� W,-� W*� *� >� *� ��v*� +�  � *� W**� � � *� :*� �  � J*� W� B*� /� *� W*� L*� +�   � *� W*� >� *� ��� *� W*� ���*� � ! � :� "� #�  ad   ~      $        F  $        v  $        �  $        �  $        �  $         $         $       0  $       5  $       J  $       O  $       d  $       n  $         xxml/XDocumentHandler    ����  -    startDocument ()V endDocument startElement &(Ljava/lang/String;Lutil/KHashtable;)V 
endElement (Ljava/lang/String;)V 
characters xxml/XDocumentHandler java/lang/Object                     	   
 	    META-INF/MANIFEST.MF Manifest-Version: 1.0
Created-By: 1.3.0 (Sun Microsystems Inc.)

Kex  	           ��>��0`	@��