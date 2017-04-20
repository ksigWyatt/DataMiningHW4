package controller;

import services.FileLoader;
import services.Filter;
import services.NaiveBayes;
import services.KNNAlgo;
import java.io.File;
import java.io.FileNotFoundException;

public class Controller {
    private NaiveBayes naiveBayes;
    private KNNAlgo knn;
    private FileLoader testLoader;
    private FileLoader trainingLoader;

    public Controller() {
        naiveBayes = new NaiveBayes();
    }

    /**
     * Used to load files into memory
     * @throws FileNotFoundException if files are not in folder
     */
    private void loadData() throws FileNotFoundException {
        File trainingFolder = new File("data/train");
        System.out.println("Loading training emails....");
        trainingLoader = new FileLoader();
        trainingLoader.loadFiles(trainingFolder.listFiles());

        File testingFolder = new File("data/test");
        System.out.println("Loading testing emails....");
        testLoader = new FileLoader();
        testLoader.loadFiles(testingFolder.listFiles());
        System.out.println();
    }

    public void runApp() {
        try {
            // -----------------------------------Naive-Bayes NO FILTER----------------------------//
            loadData(); // load the files into memory
          //  nonFilteredNaiveBayes(); // run nonFiltered naive bayes classifier
            System.out.println(); // run filtered naive bayes classifier
           // filteredNaiveBayes();// run filtered naive bayes classifier
            // -----------------------------------Naive-Bayes FILTER----------------------------//

            System.out.println();

            //------------------------------Knn-----------------------------------------//

            knn = new KNNAlgo();
            knn.train(trainingLoader.getFiles(), testLoader.getFiles());
            knn.classifyKNN(trainingLoader.getFiles(),1);
            knn.results();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void nonFilteredNaiveBayes() throws FileNotFoundException {
        System.out.println("Building model...");
        naiveBayes.train(trainingLoader.getFiles()); // train using the training dataset we just read in
        //naiveBayes.classify(trainingLoader.getFiles());
        //naiveBayes.results();
        //System.out.println();
        System.out.println("Running test...");
        naiveBayes.classify(testLoader.getFiles());
        naiveBayes.results();
    }

    private void filteredNaiveBayes() throws FileNotFoundException {
        System.out.println("Filtering files...");
        Filter filter = new Filter();
        filter.filterSpecialCharacters(trainingLoader.getFiles());
        filter.filterUnCommonWords(trainingLoader.getFiles());

        System.out.println("Building model...");
        naiveBayes.train(trainingLoader.getFiles()); // train using the training dataset we just read in
        //naiveBayes.classify(trainingLoader.getFiles());
        //naiveBayes.results();
        //System.out.println();
        System.out.println("Running test...");
        naiveBayes.classify(testLoader.getFiles());
        naiveBayes.results();
    }
}