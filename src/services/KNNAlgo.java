package services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.math.*;

/**
 * Created by My Surface on 4/17/2017.
 */
public class KNNAlgo {
	
    HashMap<String, Integer> spam;
    HashMap<String, Integer> ham;
    int totalHam;
    int totalSpam;
    public static final int HAM = 0;
    public static final int SPAM = 1;
	
	 public KNNAlgo(HashMap<String, Integer> spam, HashMap<String, Integer> ham) {
	        this.ham = ham;
	        totalHam = ham.size();
	        this.spam = spam;
	        totalSpam = spam.size();
	    }
	 
	 public Double classifyKNN(File file)throws FileNotFoundException {
		 Double distance = 0.0;
		 
		 //this is wher ewe would calculate the KNN nearest neighbor and return it's distance
		 distance = Math.sqrt(Math.pow((totalHam - totalSpam), 2));
		 
		 return distance;
	 }

}
