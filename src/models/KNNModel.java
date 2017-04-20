package models;

import java.util.ArrayList;
import java.util.HashMap;

public class KNNModel {
	
	HashMap<String, Integer> terms;
	double distance = 0;
	
	
	public KNNModel() {
		terms = new HashMap<>();
	}
	
	public double calculateDistance() {
	
		
		return distance;
	}


	public void addWord(String word) {
		terms.put(word, (terms.getOrDefault(word,0) + 1));		
	}
	
	public int getWordOccurance(String word) {
		return terms.get(word);
		
	}

}
