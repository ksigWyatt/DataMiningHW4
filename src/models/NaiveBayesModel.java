package models;

import java.util.HashMap;

public class NaiveBayesModel {

    private HashMap<String,Double> ham; // maps the word to the number of non spam documents it occurs in
    private HashMap<String,Double> spam; // maps the word to the number of spam documents it occurs in
    private double numSpamEmails; // number of spam emails
    private double numHamEmails; // number of ham emails
    private double m = 2; // the m for the mEstimate
    private double p = 1.0/5.0; // the p for the mEstimate

    public NaiveBayesModel(){
        ham = new HashMap<>();
        spam = new HashMap<>();
    }

    /**
     * Used to add words to the ham map
     * @param word the word we wish to add or increment value
     */
    public void addHam(String word){
        ham.put(word,(ham.getOrDefault(word,0.0) + 1));
    }

    /**
     * Used to add words to the spam map
     * @param word the word we wish to add or increment the value
     */
    public void addSpam(String word){
        spam.put(word,(spam.getOrDefault(word,0.0) + 1));
    }

    /**
     * used to calculate the probability of word being spam: P(word | spam)
     * @param word the word we wish to calculate probability for
     * @return the calculated probability
     */
    public double probabilityOfWordGivenSpam(String word){
        return spam.getOrDefault(word,0.0) / numSpamEmails;
    }

    /**
     * used to calculate the probability of word being ham: P(word | ham)
     * @param word the word we wish to calculate probability for
     * @return the calculated probability
     */
    public double probabilityOfWordGivenHam(String word){
        return ham.getOrDefault(word,0.0) / numHamEmails;
    }

    /**
     * Used if the probability P(word | class) = 0.
     * @return the estimate for the spam class
     */
    public double mEstimateSpam(){
        return (m*p) / (numSpamEmails + m);
    }

    /**
     * Used if the probability P(word | class) = 0.
     * @return the estimate for the ham class
     */
    public double mEstimateHam(){
        return (m*p) / (numHamEmails + m);
    }

    /**
     * Used to calculate the probability of the spam class
     * @return probability of the spam class
     */
    public double probabilityOfHam(){
        return  numHamEmails / (numHamEmails + numSpamEmails);
    }

    /**
     * Used to calculate the probability of the ham class
     * @return probability of the ham class
     */
    public  double probabilityOfSpam(){
        return  numSpamEmails / (numHamEmails + numSpamEmails);
    }

    public HashMap<String, Double> getSpam() {
        return spam;
    }

    public void setNumSpamEmails(double numSpamEmails) {
        this.numSpamEmails = numSpamEmails;
    }

    public void setNumHamEmails(double numHamEmails) {
        this.numHamEmails = numHamEmails;
    }
}
