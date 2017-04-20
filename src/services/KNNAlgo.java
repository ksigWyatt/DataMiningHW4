package services;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import models.FileData;
import models.KNNModel;
import models.NaiveBayesModel;

/**
 * Created by My Surface on 4/17/2017.
 */
public class KNNAlgo {
	
	private double hamClass; // true ham
	private double spamClass; // true spam
	private double falseHam; // false ham
	private double falseSpam; // false spam
	public ArrayList<Object> hamTerms;
	public ArrayList<Object> spamTerms;
	public ArrayList<Object> terms;
	public ArrayList<Object> termOccurrences;
	
	KNNModel model;
	
    

    public KNNAlgo() {
        
    }

	

//		not needed just yet until we can get the algo working
	public void results() {
//		double accuracy = (hamClass + spamClass) / (falseHam + falseSpam + hamClass + spamClass);
//		System.out.printf("The Accuracy of this run was: %.2f\n", accuracy * 100);
//        System.out.println("Number of emails classified as Ham: " + hamClass);
//        System.out.println("Number of emails classified as Spam: " + spamClass);
//        System.out.println("Number of false Ham: " + falseHam);
//        System.out.println("Number of false Spam: " + falseSpam);
	}


	public void train(ArrayList<FileData> files) {
		
		model = new KNNModel();
		
	}


	public void classifyKNN(ArrayList<FileData> files) {

		
		
	}

}
