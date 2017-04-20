package services;

import java.util.*;

import models.FileData;
import models.KNNModel;
import models.TermVector;

public class KNNAlgo {

	private double trueHam; // counts emails that were classified correctly as ham
	private double trueSpam; // counts emails that were classified correctly as spam
	private double falseHam; // counts emails that were classified incorrectly as ham
	private double falseSpam; // counts emails that were classified incorrectly as spam

	HashMap<String, Integer> terms; // words / frequencies in all docs
	ArrayList<TermVector> testingVectors;

	KNNModel model;

	public KNNAlgo() {
		terms = new HashMap<>();
	}

	public void train(ArrayList<FileData> train, ArrayList<FileData> test) {

		model = new KNNModel();
		
		for (FileData file : train) {
			for (String line : file.getWords()) {
				StringTokenizer st = new StringTokenizer(line);
				int index = 0;
				while (st.hasMoreTokens()) {
					String str = st.nextToken();
					model.addWord(str);
					if (!model.termIndecies.containsKey(str)) {
						model.termIndecies.put(str, index);
						index++;
					}
				}
			}
		}

		for (FileData file : test) {
			for (String line : file.getWords()) {
				StringTokenizer st = new StringTokenizer(line);
				int index = 0;
				while (st.hasMoreTokens()) {
					String str = st.nextToken();
					model.addWord(str);
					if (!model.termIndecies.containsKey(str)) {
						model.termIndecies.put(str, index);
						index++;
					}
				}
			}
		}

		for (FileData file : train) {
			HashMap<String, Integer>fileWordCount = new HashMap<>();
			for (String line : file.getWords()) {
				StringTokenizer st = new StringTokenizer(line);
				while (st.hasMoreTokens()) {
					String str = st.nextToken();
					fileWordCount.put(str, fileWordCount.getOrDefault(str, 0) + 1);
				}
			}
			int[] termVector = new int[model.termIndecies.size()];
            Object[] keySet = model.termIndecies.keySet().toArray();
			for (int i = 0; i < termVector.length; i++) {
				termVector[i] = fileWordCount.getOrDefault(keySet[i], 0);
			}
			model.trainingTermVectors.add(new TermVector(termVector, file.getName().contains("sp")));
		}

		testingVectors = new ArrayList<>();
		for (FileData file : test) {
			HashMap<String, Integer>fileWordCount = new HashMap<>();

			for (String line : file.getWords()) {
				StringTokenizer st = new StringTokenizer(line);
				while (st.hasMoreTokens()) {
					String str = st.nextToken();
					fileWordCount.put(str, fileWordCount.getOrDefault(str, 0) + 1);
				}
			}

			int[] termVector = new int[model.termIndecies.size()];
			Object[] keySet = model.termIndecies.keySet().toArray();
			for (int i = 0; i < termVector.length; i++) {
				termVector[i] = fileWordCount.getOrDefault(keySet[i], 0);
			}
			TermVector vector = new TermVector(termVector);
			testingVectors.add(vector);
		}
	}

	public void classifyKNN(ArrayList<FileData> files, int k) {

		trueSpam = 0;
		trueHam = 0;
		falseSpam = 0;
		falseHam = 0;

		int file = 0;
		for (TermVector vector : testingVectors) {
			int spam = 0;
			int ham = 0;

			model.trainingTermVectors.sort((r, l) -> r.cosineSimilarity(vector) < l.cosineSimilarity(vector) ? 1 : -1);

			for (int i = 0; i < k; i++) {
				if (model.trainingTermVectors.get(i).isSpam()) {
					spam++;
				} else {
					ham++;
				}
			}

			if (ham > spam) {
				if (files.get(file).getName().contains("sp")) {
					falseHam++;
				} else {
					trueHam++;
				}
			} else {
				if (files.get(file).getName().contains("sp")) {
					trueSpam++;
				} else {
					falseSpam++;
				}
			}
		}
	}

	/**
	 * Print our results and output the accuracy
	 */
	public void results(){
		System.out.println("Number of true ham: " + trueHam);
		System.out.println("Number of true spam: " + trueSpam);
		System.out.println("Number of false ham: " + falseHam);
		System.out.println("Number of false spam: " + falseSpam);
		double accuracy = (trueHam + trueSpam) / (trueSpam + trueHam + falseHam + falseSpam);
		System.out.println("The accuracy of this run was " + accuracy);
	}
}
