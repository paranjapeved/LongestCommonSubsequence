# Longest Common Subsequence

#### Algorithms and Data Structures - Programming Assignment 3
***

Adhish Thite

Ved Paranjape

---

This program is an implementation of the 'Longest Common Subsequence' algorithm in Java, using Dynamic Programming.

---

**Compiler used** : JAVAC<br>
**Platform** :  macOS High Sierra 10.13<br>
**IDE** : IntelliJ IDEA Community 2017.2<br>

---

##### Program Design

+ The start of program begins in the 'main' function where the input file name is captured from the command line.
+ We use a custom inner class called as 'Pair' to make pairs of the input strands. The LCS and LCS Length of each pair are stored in this Pair object itself.
+ The control then passes to the 'readFromFile()' method where the file name is passed as a parameter. This method then reads each line of the file, forms a pair of strands, and discards the last one if a pair is not formed.
+ The control is transferred to the 'initiateLCSCalculation()' method which initiates the LCS calculation for EACH of the pair, one at a time.
+ The 'doLCS()' method finds the length of the LCS and the actual LCS, and stores the data in the 'Pair' inner class.
+ Finally, after the LCS is calculated for all the input pairs, the performance and the output is written in 'ANSWERS.TXT' file.
+ The code handles exceptions : IO Exception while writing to output file and general exceptions while reading the input file.


##### Data Structures

+ We have developed a new custom data structure here - an Inner Class called 'Pair' which stores the input strands, their LCS and the LCS length.
+ The 'toString()' method of this Inner Class is overriden to provide easy output while writing to the file

##### Running the program

+ Open the Command Prompt(Windows) OR Terminal(Mac/Linux/UNIX) and navigate to the folder where the 'LCS.java' file exists.
+ Make sure that the input file (eg. input.txt) files is placed in the same folder; otherwise the program will throw an error.
+ Run the following commands :<br>
    `javac LCS.java`
    
    `java LCS input.txt`
+ After the program is executed, a file called 'answers.txt' will be created in that folder, which contains the desired output.

##### Program Limitations

+ The program will fail if there is a line break between two strands. This will lead to undesired outputs.
+ The program will fail if the strands are located in one single line, divided by COMMA or SEMICOLON.
