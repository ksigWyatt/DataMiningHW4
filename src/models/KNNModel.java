package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class KNNModel {
	
	public HashMap<String, Integer> terms;
	public LinkedHashMap<String, Integer> termIndecies; //word and an index
	public ArrayList<TermVector> trainingTermVectors;
	
	public KNNModel() {
		terms = new HashMap<>();
		trainingTermVectors = new ArrayList<>();
		termIndecies = new LinkedHashMap<>();
	}

	public void addWord(String word) {
		terms.put(word, (terms.getOrDefault(word, 0) + 1));		
	}

	public HashMap<String, Integer> getTerms() {
		return terms;
	}

	public LinkedHashMap<String, Integer> getTermIndecies() {
		return termIndecies;
	}

	public ArrayList<TermVector> getTrainingTermVectors() {
		return trainingTermVectors;
	}
}
