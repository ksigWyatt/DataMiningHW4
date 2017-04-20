package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class KNNModel {
	
	public HashMap<String, Integer> terms;
	public LinkedHashMap<String, Integer> termIndecies; //word and an index
	public ArrayList<int[]> termVectors;
	
	

 	double distance = 0;
	
	
	public KNNModel() {
		terms = new HashMap<>();
		termVectors = new ArrayList<>();
		termIndecies = new LinkedHashMap<>();
		
	}
	
	public double calculateDistance() {
	
		
		return distance;
	}


	public void addWord(String word) {
		terms.put(word, (terms.getOrDefault(word, 0) + 1));		
	}
	
	public int getWordOccurance(String word) {
		return terms.get(word);
	}
	
}
