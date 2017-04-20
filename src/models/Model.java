package models;

import java.util.HashMap;

public class Model {

    HashMap<String,Double> ham;
    HashMap<String,Double> spam;
    double numSpamEmails;
    double numHamEmails;
    double m = 3;
    double p = 1/5.0;

    public Model(){
        ham = new HashMap<>();
        spam = new HashMap<>();
    }

    public void addHam(String word){
        ham.put(word,(ham.getOrDefault(word,0.0) + 1));
    }

    public void addSpam(String word){
        spam.put(word,(spam.getOrDefault(word,0.0) + 1));
    }

    public double probabilityOfWordGivenSpam(String word){
        return spam.getOrDefault(word,0.0) / numSpamEmails;
    }

    public double probabilityOfWordGivenHam(String word){
        return ham.getOrDefault(word,0.0) / numHamEmails;
    }

    public double mEstimateSpam(){
        return (m*p) / (numSpamEmails + m);
    }

    public double mEstimateHam(){
        return (m*p) / (numHamEmails + m);
    }

    public double probabilityOfHam(){
        return  numHamEmails / (numHamEmails + numSpamEmails);
    }

    public  double probabilityOfSpam(){
        return  numSpamEmails / (numHamEmails + numSpamEmails);
    }

    public HashMap<String, Double> getHam() {
        return ham;
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
