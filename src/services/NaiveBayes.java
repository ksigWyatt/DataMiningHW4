package services;

import models.FileData;
import models.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;


public class NaiveBayes {

    Model emailModel;

    private double trueHam;
    private double trueSpam;
    double falseHam;
    double falseSpam;

    HashMap<String,Integer> testWordCount;

    public void train(ArrayList<FileData> files){
        emailModel = new Model();
        StringTokenizer st;
        String str;
        for(FileData file : files){
            for(String line : file.getWords()){
                st = new StringTokenizer(line);
                while(st.hasMoreTokens()){
                    str = st.nextToken();
                    if(file.getName().contains("sp")){
                        emailModel.addSpam(str);
                    } else {
                        emailModel.addHam(str);
                    }
                }
            }
        }
    }

    public void wordCounts(ArrayList<FileData> files){
        testWordCount = new HashMap<>();
        for(FileData file : files) {
            String word;
            StringTokenizer st;
            for (String line : file.getWords()) {
                st = new StringTokenizer(line);
                while (st.hasMoreTokens()) {
                    word = st.nextToken();
                    testWordCount.put(word,(testWordCount.getOrDefault(word,1) + 1));
                }
            }
        }
    }

    public void classify(ArrayList<FileData> files){
        StringTokenizer st;
        String str;

        double hamProbability;
        double spamProbability;


        double ham;
        double spam;

        trueSpam = 0;
        trueHam = 0;
        falseSpam = 0;
        falseHam = 0;

        wordCounts(files);

        for(FileData file : files){
            hamProbability = 0;
            spamProbability = 0;

            String word;
            for(String line : file.getWords()){
                st = new StringTokenizer(line);
                while(st.hasMoreTokens()){
                    word = st.nextToken();
                    spamProbability += Math.log(emailModel.probabilityOfWordGivenSpam(word));
                    hamProbability += Math.log(emailModel.probabilityOfWordGivenHam(word));
                }
            }

            hamProbability = hamProbability + Math.log(emailModel.probabilityOfHam());
            spamProbability = spamProbability + Math.log(emailModel.probabilityOfSpam());

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

    public void results(){
        System.out.println("Number of true ham: " + trueHam);
        System.out.println("Number of true spam: " + trueSpam);
        System.out.println("Number of false ham: " + falseHam);
        System.out.println("Number of false spam: " + falseSpam);
        double accuracy = (trueHam + trueSpam) / (trueSpam + trueHam + falseHam + falseSpam);
        System.out.println("The accuracy of this run was " + accuracy);
    }
}
