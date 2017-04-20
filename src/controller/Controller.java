package controller;
import services.FileLoader;
import services.NaiveBayes;
import services.KNNAlgo;

import java.io.File;
import java.io.FileNotFoundException;


public class Controller {
    NaiveBayes navieBayes;
    KNNAlgo knn;
    public Controller(){
        navieBayes = new NaiveBayes();
    }

    public void runApp(){
        try {
            // -----------------------------------Naive-Bayes----------------------------//
            System.out.println("Loading the training emails...");
            File trainingFolder = new File("data/train");
            FileLoader loader = new FileLoader();
            loader.loadFiles(trainingFolder.listFiles());

            System.out.println("Building model...");
            navieBayes.train(loader.getFiles()); // train using the training dataset we just read in
            navieBayes.classify(loader.getFiles());
            navieBayes.results();

            System.out.println("Loading the test emails...");
            File testingFolder = new File("data/test");
            loader = new FileLoader();
            loader.loadFiles(testingFolder.listFiles());

            System.out.println("Running test...");
            navieBayes.classify(loader.getFiles());
            navieBayes.results();

            System.out.println();

            //------------------------------Knn-----------------------------------------//
//            loader.loadFiles(trainingFolder.listFiles());
//
//            knn = new KNNAlgo();
//            knn.train(loader.getFiles());
//            knn.classifyKNN(loader.getFiles());
//            knn.results();
     
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}