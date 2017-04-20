package models;

import java.util.HashMap;

/**
 * Created by My Surface on 4/19/2017.
 */
public class Model {

    HashMap<String,Double> ham;
    HashMap<String,Double> spam;
    double m = 2;
    double p;


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
        double probabilityOfWordGivenSpam = spam.getOrDefault(word,mEstimateSpam()) / (double)spam.size();
        return probabilityOfWordGivenSpam;
    }

    public double probabilityOfWordGivenHam(String word){
        double probabilityOfWordGivenHam = ham.getOrDefault(word,mEstimateHam()) / (double)ham.size();
        return probabilityOfWordGivenHam;
    }

    public double mEstimateSpam(){
        p = 1/spam.size();
        return (m*p) / (spam.size() + m);
    }

    public double mEstimateHam(){
        p = 1/ham.size();
        return (m*p) / (ham.size() + m);
    }

    public double probabilityOfHam(){
        return  (double)ham.size() / ((double)ham.size() + (double)spam.size());
    }

    public  double probabilityOfSpam(){
        return (double)spam.size() / ((double)ham.size() + (double)spam.size());
    }
}
