package controller;
import models.FileData;
import services.FileLoader;
import services.NaiveBayes;
import services.KNNAlgo;

import java.io.File;
import java.io.FileNotFoundException;


public class Controller {
    NaiveBayes navieBayes;
    KNNAlgo knn;
    public Controller(){

    }

    public void runApp(){
        try {
            System.out.println("Loading the files...");
            File folder = new File("data/train");
            FileLoader loader = new FileLoader();
            loader.loadFiles(folder.listFiles());

            System.out.println("Building model...");
            navieBayes = new NaiveBayes();
            navieBayes.train(loader.getFiles()); // train using the training dataset we just read in
            navieBayes.classify(loader.getFiles());
            navieBayes.results();

            System.out.println("Running test...");
            folder = new File("data/test");
            loader.loadFiles(folder.listFiles());
            navieBayes.classify(loader.getFiles());
            navieBayes.classify(loader.getFiles());
            
            knn = new KNNAlgo(wordCount.getSpamWord(), wordCount.getHamWord());
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