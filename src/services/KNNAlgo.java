package services;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import models.FileData;
import models.Model;

/**
 * Created by My Surface on 4/17/2017.
 */
public class KNNAlgo {
	
	private double hamClass; // true ham
	private double spamClass; // true spam
	private double falseHam; // false ham
	private double falseSpam; // false spam
	
    Model emailModel;

	public void train(ArrayList<FileData> files){
		emailModel = new Model();
		StringTokenizer st;
		String str;
		for(FileData file : files){
			for(String line : file.getWords()){
				st = new StringTokenizer(line);
				while(st.hasMoreTokens()){
					str = st.nextToken();
					if(file.getName().contains("sp")){
						emailModel.addSpam(str);
					} else {
						emailModel.addHam(str);
					}
				}
			}
		}
	}

	 public void classifyKNN(ArrayList<FileData> files) throws FileNotFoundException {
		 	
		 int numWords = emailModel.getSpam().size() + emailModel.getHam().size(); // total number of words
		 
		 //in case we need it
//	     double spamWordProbability = (double)counts.getSpamWord().size() / (double)numWords; // probability a word is ham
//	     double hamWordProbability = (double)counts.getHamWord().size() / (double)numWords; // probability the word is spam
		
	     for (FileData data : files) {
	    	 double[] distance = new double[numWords];
	    	 
	    	 for (String s : data.getWords()) {

	    		 double dist = Math.sqrt(Math.pow(((double)emailModel.getHam().getOrDefault(s,0.0) /
	    				 							(double)emailModel.getSpam().size()), 2));
	    		 
	    		 
	    	 }
	    	 
	    	 // need to reconfigure the parenthesis bit of this if statement to get it to where it will do anything useful
/*	    	 if (dist == 0.0) {
	    		 //do something if the distance is equal to zero
	    	 
	    	  	System.out.println(data.getName() + " is classified as HAM");
                if (data.getName().contains("spmsg")) {
                    falseHam++; // incorrect classifications
                } else {
                    hamClass++; // correct classifications
                }
           	} else {
                System.out.println(data.getName() + " is classified as SPAM");
                if (data.getName().contains("spmsg")) {
                    spamClass++; // correct
                } else {
                    falseSpam++; // incorrect
                }
            }
*/
	     }
		 
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

}
