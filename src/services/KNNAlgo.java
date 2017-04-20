package services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import models.FileData;
import models.KNNModel;

public class KNNAlgo {

	// private double hamClass; // true ham
	// private double spamClass; // true spam
	//
	// private double trueHam;
	// private double trueSpam;
	double falseHam;
	double falseSpam;

	HashMap<String, Integer> terms; // words / frequencies in all docs

	KNNModel model;

	public KNNAlgo() {
		terms = new HashMap<>();
	}

	public void train(ArrayList<FileData> train, ArrayList<FileData> test) {

		HashMap<String, Integer> fileWordCount;

		model = new KNNModel();
		StringTokenizer st;
		String str;
		int index = 0;
		String key;
		int[] termVector;

		for (FileData file : train) {

			for (String line : file.getWords()) {

				st = new StringTokenizer(line);

				while (st.hasMoreTokens()) {
					str = st.nextToken();

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

				st = new StringTokenizer(line);

				while (st.hasMoreTokens()) {
					str = st.nextToken();

					model.addWord(str);

					if (!model.termIndecies.containsKey(str)) {
						model.termIndecies.put(str, index);
						index++;
					}

				}
			}
		}

		for (FileData file : train) {
			fileWordCount = new HashMap<>();

			for (String line : file.getWords()) {

				st = new StringTokenizer(line);

				while (st.hasMoreTokens()) {
					str = st.nextToken();

					fileWordCount.put(str, fileWordCount.getOrDefault(str, 0) + 1);

				}
			}
			
			termVector = new int[model.termIndecies.size()];
			
			for (int i = 0; i < termVector.length; i++) {
				key = (String)model.termIndecies.keySet().toArray()[i];
				termVector[i] = fileWordCount.getOrDefault(key, 0);
			}
			model.termVectors.add(termVector);
		}
		
		for (FileData file : test) {
			fileWordCount = new HashMap<>();

			for (String line : file.getWords()) {

				st = new StringTokenizer(line);

				while (st.hasMoreTokens()) {
					str = st.nextToken();

					fileWordCount.put(str, fileWordCount.getOrDefault(str, 0) + 1);

				}
			}
			
			termVector = new int[model.termIndecies.size()];
			
			for (int i = 0; i < termVector.length; i++) {
				key = (String)model.termIndecies.keySet().toArray()[i];
				termVector[i] = fileWordCount.getOrDefault(key, 0);
			}
			model.termVectors.add(termVector);
		}
		
	}

	public void classifyKNN(ArrayList<FileData> files) {

	}

	// not needed just yet until we can get the algo working
	public void results() {
		// double accuracy = (hamClass + spamClass) / (falseHam + falseSpam +
		// hamClass + spamClass);
		// System.out.printf("The Accuracy of this run was: %.2f\n", accuracy *
		// 100);
		// System.out.println("Number of emails classified as Ham: " +
		// hamClass);
		// System.out.println("Number of emails classified as Spam: " +
		// spamClass);
		// System.out.println("Number of false Ham: " + falseHam);
		// System.out.println("Number of false Spam: " + falseSpam);
	}

}
