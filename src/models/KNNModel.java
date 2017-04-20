package models;

import java.util.HashMap;

public class KNNModel {
	
	HashMap<String, Integer> terms;
	HashMap<String, Integer> ham;
	HashMap<String, Integer> spam;
 	double distance = 0;
	
	
	public KNNModel() {
		terms = new HashMap<>();
	}
	
	public double calculateDistance() {
	
		
		return distance;
	}


	public void addWord(String word) {
		terms.put(word, (terms.getOrDefault(word, 0) + 1));		
	}
	
	public void addHamWord(String word) {
		ham.put(word, (ham.getOrDefault(word, 0) + 1));
	}
	
	public void addSpamWord(String word) {
		spam.put(word, (spam.getOrDefault(word, 0) + 1));
	}
	
	public int getWordOccurance(String word) {
		return terms.get(word);
	}
	
	public int getHamWordOccurance(String word) {
		return ham.get(word);
	}
	
	public int getSpamWordOccurance(String word) {
		return spam.get(word);
	}

}
