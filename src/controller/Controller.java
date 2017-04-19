package controller;

import models.WordCounts;
import services.NaiveBayes;
import services.KNNAlgo;

import java.io.File;
import java.io.FileNotFoundException;


public class Controller {
    WordCounts wordCount;
    NaiveBayes navieBayes;
    KNNAlgo knn;
    public Controller(WordCounts counts){
        wordCount = counts;
    }

    public void runApp(){
        try {
            // naive bayes
            File folder = new File("data/train");
            wordCount.wordsSet(folder.listFiles());
            wordCount.wordsSet(folder.listFiles());
            navieBayes = new NaiveBayes(wordCount);
            navieBayes.classify();
            navieBayes.results();
            
            knn = new KNNAlgo(wordCount.getSpamWord(), wordCount.getHamWord());
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
