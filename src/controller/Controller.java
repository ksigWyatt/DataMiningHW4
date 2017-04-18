package controller;

import models.WordCounts;
import java.io.FileNotFoundException;

/**
 * Created by My Surface on 4/17/2017.
 */
public class Controller {
    WordCounts wordCount;

    public Controller(WordCounts counts){
        wordCount = counts;
    }

    public void runApp(){
        try {
            wordCount.wordsSet();
            for(String s : wordCount.getNonSpamWordCount().keySet()){
                System.out.println(s + " " + wordCount.getNonSpamWordCount().get(s));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
