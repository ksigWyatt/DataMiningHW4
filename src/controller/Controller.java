package controller;

import models.WordCounts;
import services.NavieBayesSpamAlgo;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by My Surface on 4/17/2017.
 */
public class Controller {
    WordCounts wordCount;
    NavieBayesSpamAlgo navieBayes;
    public Controller(WordCounts counts){
        wordCount = counts;
    }

    public void runApp(){
        try {
            wordCount.wordsSet();
            navieBayes = new NavieBayesSpamAlgo(wordCount.getSpamWordCount(), wordCount.getHamWordCount());
            File folder = new File("data/train");

            for(File file : folder.listFiles()) {
                navieBayes.classifiy(file);
                break; // just see one file
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
