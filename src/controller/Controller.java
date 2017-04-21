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

    /**
     * Used to run the application
     */
    public void runApp() {
        try {
            // -----------------------------------Naive-Bayes NO FILTER----------------------------//
            loadData(); // load the files into memory
            System.out.println("Running Naive-Bayes");
            nonFilteredNaiveBayes(); // run nonFiltered naive bayes classifier
            System.out.println(); // run filtered naive bayes classifier
            filteredNaiveBayes();// run filtered naive bayes classifier
            // -----------------------------------Naive-Bayes FILTER----------------------------//

            System.out.println();

            //------------------------------Knn NO Filter-----------------------------------------//
            loadData(); // reload data since we filtered above
            nonFilteredKNN();
            System.out.println();
            //------------------------------Knn Filter-----------------------------------------//
            filteredKNN();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Runs the naive bayes classifier on non filtered data
     * @throws FileNotFoundException
     */
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

    /**
     * runs the naive bayes classifier filtered data
     * @throws FileNotFoundException
     */
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
        System.out.println("Number of Filtered words: " + filter.numFiltered);
    }

    /**
     * used to run knn with non filtered data
     */
    private void nonFilteredKNN(){
        System.out.println("Building model...");
        knn = new KNNAlgo();
        knn.train(trainingLoader.getFiles(), testLoader.getFiles());

        System.out.println("Running test...");
        System.out.println("k=1");
        knn.classifyKNN(1);
        knn.results();
        System.out.println("k=3");
        knn.classifyKNN(3);
        knn.results();
        System.out.println("k=5");
        knn.classifyKNN(5);
        knn.results();
        System.out.println("k=20");
        knn.classifyKNN(20);
        knn.results();
    }

    /**
     * used to run knn with filtered data
     */
    private void filteredKNN(){
        System.out.println("Filtering files...");
        Filter filter = new Filter();
        filter.filterSpecialCharacters(trainingLoader.getFiles());
        filter.filterUnCommonWords(trainingLoader.getFiles());

        System.out.println("Running test...");
        knn = new KNNAlgo();
        knn.train(trainingLoader.getFiles(), testLoader.getFiles());
        System.out.println("k=1");
        knn.classifyKNN(1);
        knn.results();
        System.out.println("k=3");
        knn.classifyKNN(3);
        knn.results();
        System.out.println("k=5");
        knn.classifyKNN(5);
        knn.results();
        System.out.println("k=20");
        knn.classifyKNN(20);
        knn.results();

        System.out.println("Number of Filtered words: " + filter.numFiltered);
    }
}