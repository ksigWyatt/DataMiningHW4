package models;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class KNNModel {

	private LinkedHashMap<String, Integer> termIndecies; //word and an index
	private ArrayList<TermVector> trainingTermVectors;
	
	public KNNModel() {
		trainingTermVectors = new ArrayList<>();
		termIndecies = new LinkedHashMap<>();
	}

	public LinkedHashMap<String, Integer> getTermIndecies() {
		return termIndecies;
	}

	public ArrayList<TermVector> getTrainingTermVectors() {
		return trainingTermVectors;
	}

	public void setTermIndecies(LinkedHashMap<String, Integer> termIndecies) {
		this.termIndecies = termIndecies;
	}

	public void setTrainingTermVectors(ArrayList<TermVector> trainingTermVectors) {
		this.trainingTermVectors = trainingTermVectors;
	}
}
