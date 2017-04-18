package models;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by My Surface on 4/17/2017.
 */
public class WordCounts {
    HashMap<String, Integer> spamWordCount; // maps a string to a integer i.e the word count
    HashMap<String, Integer> nonSpamWordCount;

    public WordCounts(){
        spamWordCount = new HashMap<>();
        nonSpamWordCount = new HashMap<>();
    }

    /**
     * Used to find all the unique words in spam and non-spam files and counts them
     * @throws FileNotFoundException
     */
    public void wordsSet() throws FileNotFoundException {
        File folder = new File("data/train/");
        Scanner scan;
        String str;

        for(File file : folder.listFiles()){
            if(file.getName().contains("spm")){ // if it contains spm it is a spam file
                scan = new Scanner(file); // read the file
                while (scan.hasNext()) { // scan each line of the file
                    StringTokenizer stringTokenizer = new StringTokenizer(scan.nextLine()); // tokenize the line read
                    while (stringTokenizer.hasMoreTokens()){ // go through each token
                        str = stringTokenizer.nextToken();
                        if(spamWordCount.containsKey(str)){
                            int value = spamWordCount.get(str);
                            spamWordCount.put(str, ++value);
                        } else {
                            spamWordCount.put(str, 1);
                        }
                    }
                }
            } else {
                scan = new Scanner(file); // read the file
                while (scan.hasNext()) { // scan each line of the file
                    StringTokenizer stringTokenizer = new StringTokenizer(scan.nextLine()); // tokenize the line read
                    while (stringTokenizer.hasMoreTokens()){ // go through each token
                        str = stringTokenizer.nextToken().trim();
                        if(nonSpamWordCount.containsKey(str)){
                            int value = nonSpamWordCount.get(str);
                            nonSpamWordCount.put(str, ++value);
                        } else {
                            nonSpamWordCount.put(str, 1);
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
