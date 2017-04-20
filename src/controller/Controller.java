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

    }

    public void runApp(){
        try {
            // -----------------------------------Naive-Bayes----------------------------//
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
            navieBayes.results();

            System.out.println();

            //------------------------------Knn-----------------------------------------//
            folder = new File("data/train");
            loader = new FileLoader();
            loader.loadFiles(folder.listFiles());

            knn = new KNNAlgo();
            knn.train(loader.getFiles());
            knn.classifyKNN(loader.getFiles());
            knn.results();
     
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}