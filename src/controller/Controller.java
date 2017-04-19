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
            navieBayes = new NaiveBayes(wordCount);
            navieBayes.classify();		// getting 86.97% accuracy when I run this BTW
            navieBayes.results();
            
            wordCount.wordsSet(folder.listFiles());
            knn = new KNNAlgo(wordCount);
            knn.classifyKNN();		
            knn.results();
            
            //not sure why this is in here twice
//            wordCount.wordsSet(folder.listFiles());
//            navieBayes = new NaiveBayes(wordCount);
//            navieBayes.classify();
//            navieBayes.results();

     
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}