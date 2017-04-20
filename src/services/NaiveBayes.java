package services;

import models.FileData;
import models.NaiveBayesModel;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Naive bayes email classifier.
 * NOTE: Ham = non-spam email
 */
public class NaiveBayes {

    private NaiveBayesModel emailModel; // the model we build from the training emails

    private double trueHam; // counts emails that were classified correctly as ham
    private double trueSpam; // counts emails that were classified correctly as spam
    private double falseHam; // counts emails that were classified incorrectly as ham
    private double falseSpam; // counts emails that were classified incorrectly as spam

    /**
     * Used to train the algorithm to build the model used to calculate the probabilities
     * @param files the files from the data/training folder
     */
    public void train(ArrayList<FileData> files){
        emailModel = new NaiveBayesModel();
        StringTokenizer st;
        String str;
        ArrayList<String> seen; // words we have seen in the file
        double numSpam = 0; // number of spam emails in the folder
        double numHam = 0; // number of ham emails in the folder
        for(FileData file : files){
            seen = new ArrayList<>();
            for(String line : file.getWords()){
                st = new StringTokenizer(line);
                while(st.hasMoreTokens()){
                    str = st.nextToken();
                    // if we have not seen the word yet, we increment the val of the string
                    if (file.getName().contains("sp") && !seen.contains(str)) {
                        emailModel.addSpam(str);
                    } else if(!file.getName().contains("sp") && !seen.contains(str)){
                        emailModel.addHam(str);
                    }
                    seen.add(str);
                }
            }

            if(file.getName().contains("sp")){
                numSpam++;
            } else {
                numHam++;
            }
        }

        emailModel.setNumHamEmails(numHam);
        emailModel.setNumSpamEmails(numSpam);
    }

    /**
     * Method used to classify the email as spam or ham
     * @param files
     */
    public void classify(ArrayList<FileData> files){
        StringTokenizer st;
        double hamProbability;
        double spamProbability;
        trueSpam = 0;
        trueHam = 0;
        falseSpam = 0;
        falseHam = 0;
        String word;

        // go through each file
        for(FileData file : files){
            hamProbability = 0; // probability the email is non spam
            spamProbability = 0; // probability the email is spam
            for(String line : file.getWords()){
                st = new StringTokenizer(line);
                while(st.hasMoreTokens()){
                    // here we calculate the probabilities using the model
                    word = st.nextToken();
                    double val = (emailModel.probabilityOfWordGivenSpam(word));
                    if(val == 0){
                        val = emailModel.mEstimateSpam();
                    }
                    val = Math.log(val);
                    spamProbability += val;

                    val = (emailModel.probabilityOfWordGivenHam(word));
                    if(val == 0){
                        val = emailModel.mEstimateHam();
                    }
                    val = Math.log(val);
                    hamProbability += val;
                }
            }

            // add on the class probability
            hamProbability = hamProbability + Math.log(emailModel.probabilityOfHam());
            spamProbability = spamProbability + Math.log(emailModel.probabilityOfSpam());

            // classify the email and record the result
            if(hamProbability > spamProbability){
                if(file.getName().contains("sp")){
                    falseHam++;
                } else {
                    trueHam++;
                }
            } else {
                if(file.getName().contains("sp")){
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
