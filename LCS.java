/* LCS - Programming Assignment 3 - Adhish Thite | Ved Paranjape */

/**
 # Longest Common Subsequence

 #### Algorithms and Data Structures - Programming Assignment 3
 ***

 Adhish Thite

 Ved Paranjape

 ---

 This program is an implementation of the 'Longest Common Subsequence' algorithm in Java, using Dynamic Programming.

 ---

 **Compiler used** : JAVAC
 **Platform** :  macOS High Sierra 10.13
 **IDE** : IntelliJ IDEA Community 2017.2

 ---

 ##### Program Design

 + The start of program begins in the 'main' function where the input file name is captured from the command line.
 + We use a custom inner class called as 'Pair' to make pairs of the input strands. The LCS and LCS Length of each pair are stored in this Pair object itself.
 + The control then passes to the 'readFromFile()' method where the file name is passed as a parameter.
 This method then reads each line of the file, forms a pair of strands, and discards the last one if a pair is not formed.
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
 + Run the following commands :
 `javac LCS.java`
 `java LCS input.txt`
 + After the program is executed, a file called 'answers.txt' will be created in that folder, which contains the desired output.

 ##### Program Limitations

 + The program will fail if there is a line break between two strands. This will lead to undesired outputs.
 + The program will fail if the strands are located in one single line, divided by COMMA or SEMICOLON.
 */

// IMPORT STATEMENTS - BEGIN

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// IMPORT STATEMENTS - END

public class LCS {

    /* Main Method */
    public static void main(String[] args) {

        List<Pair> global_PairList;
        if (args.length != 0 && args[0] != null && args[0].length() != 0) {
            LCS objLCS = new LCS();
            global_PairList = objLCS.readFromFile(args[0]);

            if (!global_PairList.isEmpty()) {
                Long runningTime = objLCS.initiateLCSCalculation(global_PairList);

                objLCS.writeToFile(global_PairList, runningTime);

                System.out.println("\nPlease check ANSWERS.TXT file.");
            } else {
                System.out.println("\nNo Input Provided !\n");
            }
        } else {
            System.out.println("\nPlease specify an input file !\n");
            return;
        }
    }


    /**
     * @param fileName
     * @return Pairs of Input Strings
     * <p>
     * Description: Reads the input from the input file and then makes a 'Pair' of successive input strings.
     * If an extra input string is found, then it is discarded.
     * @name readFromFile
     */
    private List<Pair> readFromFile(String fileName) {
        File inputFile = new File(fileName);
        List<Pair> pairLists = new ArrayList<>();

        try {
            BufferedReader buffReader = new BufferedReader(new FileReader(inputFile));
            String readLine;
            String nextLine;

            while ((readLine = buffReader.readLine()) != null) {
                nextLine = buffReader.readLine();

                if (nextLine != null && readLine.length() != 0) {
                    Pair pair = new Pair(readLine, nextLine);
                    pairLists.add(pair);
                }
            }

            buffReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pairLists;
    }


    /**
     * @name initiateLCSCalculation
     * @param   pairsList
     * @return Performance of the Algorithm
     *
     * Description: Passes each pair, one at a time, to the actual LCS calculation.
     *              Measures the performance of the algorithm.
     */
    private long initiateLCSCalculation(List<Pair> pairsList) {
        long startTime = System.currentTimeMillis();

        for (Pair tempPair : pairsList) {
            doLCS(tempPair);
        }

        return (System.currentTimeMillis() - startTime);
    }

    /**
     * @name doLCS
     * @param   tempPair
     * @return void
     *
     * Description: Performs the actual LCS calculation on a Pair.
     *              The LCS and LCS Length are stored in the pair itself.
     */
    private static void doLCS(Pair tempPair) {
        String input1 = tempPair.input2;
        String input2 = tempPair.input1;
        int m = input1.length();
        int n = input2.length();
        int[][] L = new int[m + 1][n + 1];

        for (int i = 0; i < m; i++) {
            L[i][0] = 0;
        }

        for (int j = 0; j < n; j++) {
            L[0][j] = 0;
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (input1.charAt(i) == input2.charAt(j)) {
                    L[i + 1][j + 1] = L[i][j] + 1;
                } else {
                    L[i + 1][j + 1] = Math.max(L[i][j + 1], L[i + 1][j]);
                }
            }
        }


        int lcs_len = L[m][n];
        int idx = lcs_len;
        char[] lcs = new char[lcs_len];

        int i = m;
        int j = n;

        while (i > 0 && j > 0) {
            if (input1.charAt(i - 1) == input2.charAt(j - 1)) {
                lcs[idx - 1] = input1.charAt(i - 1);
                i--;
                j--;
                idx--;
            } else {
                if (L[i - 1][j] > L[i][j - 1]) {
                    i--;
                } else {
                    j--;
                }
            }
        }

        tempPair.strLCS = new String(lcs);
        tempPair.lengthLCS = lcs_len;
    }


    /**
     * @name writeToFile
     * @param   pairList
     * @param   runningTime
     *
     * Description: Writes the Input Strings, LCS, LCS Length and Performance in a structured manner to ANSWERS.TXT
     */
    private void writeToFile(List<Pair> pairList, Long runningTime) {
        BufferedWriter buffWrite;
        FileWriter fWriter = null;

        try {
            File fileName = new File("answers.txt");

            if (!fileName.exists()) {
                fileName.createNewFile();
            }

            fWriter = new FileWriter(fileName);

            buffWrite = new BufferedWriter(fWriter);

            for (Pair tempPair : pairList) {
                buffWrite.write(tempPair.toString());
            }

            buffWrite.write("\n\nRunning Time is:\t\t" + runningTime + " ms");

            buffWrite.close();
        } catch (IOException ex) {
            System.out.println("\n\t\t** There is an ERROR in ANSWER.TXT file creation **\n\nError Trace is >:\n\n");
            ex.printStackTrace();
        }
    }


    /**
     * INNER CLASS - PAIR()
     *
     * Data Structure used to encapsulate the Input Strings, the LCS and the LCS Length together.
     *
     * Overriden the toString() Method to simplify file writing and display
     */
    private class Pair {
        private String input1;
        private String input2;
        private String strLCS;
        private int lengthLCS;

        private Pair(String input1, String input2) {
            this.input1 = input1;
            this.input2 = input2;
            strLCS = null;
            lengthLCS = 0;
        }

        @Override
        public String toString() {
            String dash = "\n------------------------------------------\n";
            String outString = "For the DNA Strands:\n\t1. " + input1 + "\n\t2. " + input2 + "\n" + "\nLCS is:\t" + strLCS + "\nLength of LCS is:\t" + lengthLCS;
            return dash + outString + dash;
        }
    }
}