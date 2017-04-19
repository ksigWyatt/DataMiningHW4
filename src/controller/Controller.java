package controller;

import models.WordCounts;
import services.NaiveBayes;
import services.KNNAlgo;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by My Surface on 4/17/2017.
 */
public class Controller {
    WordCounts wordCount;
    NaiveBayes navieBayes;
    KNNAlgo knn;
    public Controller(WordCounts counts){
        wordCount = counts;
    }

    public void runApp(){
        try {
            wordCount.wordsSet();
            navieBayes = new NavieBayesSpamAlgo(wordCount.getSpamWordCount(), wordCount.getHamWordCount());
            knn = new KNNAlgo(wordCount.getSpamWordCount(), wordCount.getHamWordCount());
            File folder = new File("data/train");
            wordCount.wordsSet(folder.listFiles());
            navieBayes = new NaiveBayes(wordCount);
            navieBayes.classify();
            navieBayes.results();

            for(File file : folder.listFiles()) {
                knn.classifyKNN(file);
                break; // just see one file
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
