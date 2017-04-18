package models;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by My Surface on 4/17/2017.
 */
public class WordCounts {
    HashMap<String, Integer> spamWordCount; // maps a string to a integer i.e the word count
    HashMap<String, Integer> hamWordCount;
    int hamFileCount = 0;
    int spamFileCount = 0;

    public WordCounts(){
        spamWordCount = new HashMap<>();
        hamWordCount = new HashMap<>();
    }

    /**
     * Used to find all the unique words in spam and non-spam files and counts them
     * @throws FileNotFoundException
     */
    public void wordsSet() throws FileNotFoundException {
        File folder = new File("data/train/");
        Scanner scan;
        String str;
        StringTokenizer stringTokenizer;
        int val;
        ArrayList<String> seenWords;
        for(File file : folder.listFiles()){
            scan = new Scanner(file); // read the file
            while (scan.hasNext()) { // scan each line of the file
                stringTokenizer = new StringTokenizer(scan.nextLine()); // tokenize the line read
                seenWords = new ArrayList<String>();
                while (stringTokenizer.hasMoreTokens()){ // go through each token
                    str = stringTokenizer.nextToken();// get the value of the token
                    if(file.getName().contains("spm")) { // if the file is a spam file add the word to spamCount
                        if(spamWordCount.containsKey(str) && !seenWords.contains(str)){
                            val = spamWordCount.get(str) + 1;
                            spamWordCount.put(str,val);
                        } else {
                            spamWordCount.put(str,1);
                        }
                    } else {
                        if(hamWordCount.containsKey(str) && !seenWords.contains(str)){ // if it is a ham add to hamCount
                            val = hamWordCount.get(str) + 1;
                            hamWordCount.put(str,val);
                        } else {
                            hamWordCount.put(str,1);
                        }
                    }
                    seenWords.add(str);
                }
            }
            if(file.getName().contains("spm")){
                spamFileCount++;
            } else {
                hamFileCount++;
            }
        }
    }

    public HashMap<String, Integer> getSpamWordCount() {
        return spamWordCount;
    }


    public HashMap<String, Integer> getHamWordCount() {
        return hamWordCount;
    }

    public int getHamFileCount() {
        return hamFileCount;
    }

    public int getSpamFileCount() {
        return spamFileCount;
    }
}
