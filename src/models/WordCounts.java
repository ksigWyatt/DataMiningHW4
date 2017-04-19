package models;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;


public class WordCounts {
    private HashMap<String, Integer> spamWord; // maps a string to a integer i.e the number of files this word occurs
    private HashMap<String, Integer> hamWord;
    private ArrayList<FileData> files; // store the files this data structure to help access later

    public WordCounts(){
        spamWord = new HashMap<>();
        hamWord = new HashMap<>();
        files = new ArrayList<>();
    }

    /**
     * Used to find all the unique words in spam and non-spam files and counts them
     * @throws FileNotFoundException error
     */
    public void wordsSet(File[] folder) throws FileNotFoundException {
        Scanner scan;
        String str;
        StringTokenizer stringTokenizer;
        int val;
        ArrayList<String> seenWords; // used to hold worlds seen in the file
        for(File file : folder){
            scan = new Scanner(file); // read the file
            seenWords = new ArrayList<>();
            while (scan.hasNext()) { // scan each line of the file
                stringTokenizer = new StringTokenizer(scan.nextLine()); // tokenize the line read
                while (stringTokenizer.hasMoreTokens()){ // go through each token
                    str = stringTokenizer.nextToken();// get the value of the token
                    if(file.getName().contains("spmsgb")) { // if the file is a spam file add the word to spamCount
                        if(spamWord.containsKey(str) && !seenWords.contains(str)){ // only add to the word value if we haven't seen it in this file
                            val = spamWord.get(str) + 1;
                            spamWord.put(str,val);
                        } else {
                            spamWord.put(str,1); // first time seeing word the in the files
                        }
                    } else {
                        if(hamWord.containsKey(str) && !seenWords.contains(str)){ // same logic as above
                            val = hamWord.get(str) + 1;
                            hamWord.put(str,val);
                        } else {
                            hamWord.put(str,1);
                        }
                    }
                    seenWords.add(str); // add the word to seen
                }
            }
            files.add(new FileData(seenWords, file.getName())); // build the file object
        }
    }

    // getters

    public HashMap<String, Integer> getSpamWord() {
        return spamWord;
    }

    public HashMap<String, Integer> getHamWord() {
        return hamWord;
    }

    public ArrayList<FileData> getFiles() {
        return files;
    }
}
