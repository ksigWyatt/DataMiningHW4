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
    HashMap<String, Integer> nonSpamWordCount;
    ArrayList<FileWordCount> fileWordCounts;

    public WordCounts(){
        spamWordCount = new HashMap<>();
        nonSpamWordCount = new HashMap<>();
        fileWordCounts = new ArrayList<>();
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

        for(File file : folder.listFiles()){
            scan = new Scanner(file); // read the file
            while (scan.hasNext()) { // scan each line of the file
                stringTokenizer = new StringTokenizer(scan.nextLine()); // tokenize the line read
                while (stringTokenizer.hasMoreTokens()){ // go through each token
                    str = stringTokenizer.nextToken();
                    if(file.getName().contains("spm")) {
                        if(spamWordCount.containsKey(str)){
                            val = spamWordCount.get(str) + 1;
                            spamWordCount.put(str,val);
                        } else {
                            spamWordCount.put(str,1);
                        }
                    } else {
                        if(nonSpamWordCount.containsKey(str)){
                            val = nonSpamWordCount.get(str) + 1;
                            nonSpamWordCount.put(str,val);
                        } else {
                            nonSpamWordCount.put(str,1);
                        }
                    }
                }
            }
        }
    }

    public HashMap<String, Integer> getSpamWordCount() {
        return spamWordCount;
    }


    public HashMap<String, Integer> getNonSpamWordCount() {
        return nonSpamWordCount;
    }

}
