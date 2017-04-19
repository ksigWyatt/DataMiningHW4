package controller;

import models.WordCounts;
import services.NaiveBayes;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by My Surface on 4/17/2017.
 */
public class Controller {
    WordCounts wordCount;
    NaiveBayes navieBayes;
    public Controller(WordCounts counts){
        wordCount = counts;
    }

    public void runApp(){
        try {
            File folder = new File("data/train");
            wordCount.wordsSet(folder.listFiles());
            navieBayes = new NaiveBayes(wordCount);
            navieBayes.classify();
            navieBayes.results();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
