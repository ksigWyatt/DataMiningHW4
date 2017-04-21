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

	ArrayList<TermVector> testingVectors; // contains the testing emails as term vectors

	KNNModel model; // model we built in training

	/**
	 * Used to build our model. Also used to vectorize the testing emails since we are already here looking at those files
	 * @param train the training emails
	 * @param test the testing emails
	 */
	public void train(ArrayList<FileData> train, ArrayList<FileData> test) {
		model = new KNNModel(); // model we are going to build
		testingVectors = new ArrayList<>(); // the testing vectors

		// find all the words in the training emails and index them
		for (FileData file : train) {
			for (String line : file.getWords()) {
				StringTokenizer st = new StringTokenizer(line);
				int index = 0;
				while (st.hasMoreTokens()) {
					String str = st.nextToken();
					if (!model.getTermIndecies().containsKey(str)) {
						model.getTermIndecies().put(str, index);
						index++;
					}
				}
			}
		}

		// find all the words in the testing emails and index them
		for (FileData file : test) {
			for (String line : file.getWords()) {
				StringTokenizer st = new StringTokenizer(line);
				int index = 0;
				while (st.hasMoreTokens()) {
					String str = st.nextToken();
					if (!model.getTermIndecies().containsKey(str)) {
						model.getTermIndecies().put(str, index);
						index++;
					}
				}
			}
		}

		// build all the term vectors in the training
		for (FileData file : train) {
			HashMap<String, Integer> fileWordCount = new HashMap<>();
			for (String line : file.getWords()) {
				StringTokenizer st = new StringTokenizer(line);
				while (st.hasMoreTokens()) {
					String str = st.nextToken();
					fileWordCount.put(str, fileWordCount.getOrDefault(str, 0) + 1);
				}
			}
			int[] termVector = new int[model.getTermIndecies().size()];
            Object[] keySet = model.getTermIndecies().keySet().toArray();
			for (int i = 0; i < termVector.length; i++) {
				termVector[i] = fileWordCount.getOrDefault(keySet[i], 0);
			}
			TermVector vector = new TermVector(termVector, file.getName().contains("sp"));
			vector.setFileName(file.getName());
			model.getTrainingTermVectors().add(vector);
		}

		// build all the testing vectors using
		for (FileData file : test) {
			HashMap<String, Integer> fileWordCount = new HashMap<>();

			for (String line : file.getWords()) {
				StringTokenizer st = new StringTokenizer(line);
				while (st.hasMoreTokens()) {
					String str = st.nextToken();
					fileWordCount.put(str, fileWordCount.getOrDefault(str, 0) + 1);
				}
			}

			int[] termVector = new int[model.getTermIndecies().size()];
			Object[] keySet = model.getTermIndecies().keySet().toArray();
			for (int i = 0; i < termVector.length; i++) {
				termVector[i] = fileWordCount.getOrDefault(keySet[i], 0);
			}
			TermVector vector = new TermVector(termVector);
			vector.setFileName(file.getName());
			testingVectors.add(vector);
		}
	}

	/**
	 * used to classify the testing vectors given a value k
	 * @param k
	 */
	public void classifyKNN(int k) {

		trueSpam = 0;
		trueHam = 0;
		falseSpam = 0;
		falseHam = 0;

		// loop through the arry of testing vectors
		for(TermVector vector : testingVectors){
			double spam = 0;
			double ham = 0;

			// sort the model using the cosine similarity
			model.getTrainingTermVectors().sort((vector1,vector2) -> vector1.cosineSimilarity(vector) < vector2.cosineSimilarity(vector) ? 1 : -1);

			// add up all the nearest neighbors class
			for(int i = 0; i < k; i++){
				if(model.getTrainingTermVectors().get(i).getFileName().contains("sp")){
					spam++;
				} else {
					ham++;
				}
			}

			// calculate the accuracy of the run
			if(spam > ham){
				if(vector.getFileName().contains("spmsg")){
					trueSpam++;
				} else {
					falseSpam++;
				}
			} else {
				if(vector.getFileName().contains("spmsg")){
					falseHam++;
				} else {
					trueHam++;
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
