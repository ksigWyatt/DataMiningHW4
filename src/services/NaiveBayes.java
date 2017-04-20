package services;

import models.FileData;
import models.Model;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class NaiveBayes {

    Model emailModel;

    private double trueHam;
    private double trueSpam;
    double falseHam;
    double falseSpam;

    public void train(ArrayList<FileData> files){
        emailModel = new Model();
        StringTokenizer st;
        String str;
        ArrayList<String> seen;
        double numSpam = 0;
        double numHam = 0;
        for(FileData file : files){
            seen = new ArrayList<>();
            for(String line : file.getWords()){
                st = new StringTokenizer(line);
                while(st.hasMoreTokens()){
                    str = st.nextToken();

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

//        for(String s : emailModel.getHam().keySet()){
//            System.out.println(s + " " + emailModel.getHam().get(s));
//        }
//        System.out.println(emailModel.getHam().keySet().size());
//
//        System.out.println();
//
//        for(String s : emailModel.getSpam().keySet()){
//            System.out.println(s + " " + emailModel.getSpam().get(s));
//        }
//        System.out.println(emailModel.getSpam().keySet().size());
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

        for(FileData file : files){
            hamProbability = 0;
            spamProbability = 0;

            String word;
            for(String line : file.getWords()){
                st = new StringTokenizer(line);
                while(st.hasMoreTokens()){
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
