package controller;

import models.WordCounts;
import view.View;

import java.io.FileNotFoundException;

/**
 * Created by My Surface on 4/17/2017.
 */
public class Controller {
    View view;
    WordCounts wordCount;

    public Controller(View theView, WordCounts counts){
        this.view = theView;
        wordCount = counts;
    }

    public void runApp(){
        try {
            wordCount.wordsSet();
            view.NavieBayesAlgo(wordCount.getSpamWordCount(), wordCount.getNonSpamWordCount());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
