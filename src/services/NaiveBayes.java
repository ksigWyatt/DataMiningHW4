package services;

import models.FileData;
import models.WordCounts;
import java.io.FileNotFoundException;

public class NaiveBayes {
    private WordCounts counts;

    private double hamClass; // true ham
    private double spamClass; // true spam
    private double falseHam; // false ham
    private double falseSpam; // false spam


    public NaiveBayes(WordCounts counts) {
        this.counts = counts;
    }

    public void classify() throws FileNotFoundException {

        int numWords = counts.getSpamWord().size() + counts.getHamWord().size(); // total number of words
        double spamWordProbability = (double)counts.getSpamWord().size() / (double)numWords; // probability a word is ham
        double hamWordProbability = (double)counts.getHamWord().size() / (double)numWords; // probability the word is spam


        // go through each file
        for (FileData data : counts.getFiles()) {
            double hamProbability = 0;
            double spamProbability = 0;
            // go through each string
            for (String s : data.getWords()) {
                // probability the word is ham
                double hamProb = (double)counts.getHamWord().getOrDefault(s,0) / (double)counts.getHamWord().size();
                // probability the word is spam
                double spamProb = (double)counts.getSpamWord().getOrDefault(s,0) / (double)counts.getSpamWord().size();

                // add the normal value of the classical
                hamProbability += (hamProb == 0) ? mEstimate(hamProb,counts.getHamWord().size()) : hamProb;
                spamProbability += (spamProb == 0) ? mEstimate(spamProb,counts.getSpamWord().size()) : spamProb;

            }

            // multiply by the probably of a spam word or ham word
            hamProbability = hamProbability * hamWordProbability;
            spamProbability = spamProbability * spamWordProbability;

            // classify the record and see how we did
            if (hamProbability > spamProbability) {
                System.out.println(data.getName() + " is classified as HAM");
                if (data.getName().contains("spmsg")) {
                    falseHam++; // incorrect classifications
                } else {
                    hamClass++; // correct classifications
                }
            } else {
                System.out.println(data.getName() + " is classified as SPAM");
                if (data.getName().contains("spmsg")) {
                    spamClass++; // correct
                } else {
                    falseSpam++; // incorrect
                }
            }
        }
    }

    /**
     * Used to gauge accuracy and print out metrics
     */
    public void results() {
        double accuracy = (hamClass + spamClass) / (falseHam + falseSpam + hamClass + spamClass);
        System.out.printf("The Accuracy of this run was: %.2f\n", accuracy * 100);
        System.out.println("Number of emails classified as Ham: " + hamClass);
        System.out.println("Number of emails classified as Spam: " + spamClass);
        System.out.println("Number of false Ham: " + falseHam);
        System.out.println("Number of false Spam: " + falseSpam);
    }

    /**
     * used to acquire m-estimate
     * @param val 0
     * @param classNum number of class
     * @return m-estimate for that value
     */
    private double mEstimate(double val, int classNum){
        int m = 1;
        double p = .5;
        double top = val + (m * p);
        double bottom = classNum + m;
        return top/bottom;
    }
}
