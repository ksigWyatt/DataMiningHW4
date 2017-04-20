package services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import models.FileData;
import models.KNNModel;


public class KNNAlgo {
	
	private double hamClass; // true ham
	private double spamClass; // true spam
		
	private double trueHam;
    private double trueSpam;
    double falseHam;
    double falseSpam;
    
	HashMap<String,Double> terms;

		
	KNNModel model;
	
    
    public KNNAlgo() {
    	terms = new HashMap<>();
    }


	public void train(ArrayList<FileData> files) {
		
		model = new KNNModel();
		ArrayList<String> seen;
		StringTokenizer st;
        String str;
		        
        for(FileData file : files){
            seen = new ArrayList<>();
            for(String line : file.getWords()) {
            	
                st = new StringTokenizer(line);
                
                while(st.hasMoreTokens()) {
                    str = st.nextToken();

                    if (!seen.contains(str)) {
                    	model.addWord(str);
                    } 
                    else {
                    	model.addWord(str);
                    }

                    seen.add(str);
                    int occurence = model.getWordOccurance(str);
//                  System.out.println("\nThe word OR character '" + str + "' Occurs " + occurence + " times");
                }
            }

//            if(file.getName().contains("sp")){
//                numSpam++;
//            } else {
//                numHam++;
//            }
        }
		
	}


	public void classifyKNN(ArrayList<FileData> files) {

		
		
	}
	
//	not needed just yet until we can get the algo working
	public void results() {
//	double accuracy = (hamClass + spamClass) / (falseHam + falseSpam + hamClass + spamClass);
//	System.out.printf("The Accuracy of this run was: %.2f\n", accuracy * 100);
//    System.out.println("Number of emails classified as Ham: " + hamClass);
//    System.out.println("Number of emails classified as Spam: " + spamClass);
//    System.out.println("Number of false Ham: " + falseHam);
//    System.out.println("Number of false Spam: " + falseSpam);
	}


}
