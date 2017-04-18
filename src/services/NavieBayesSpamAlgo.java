package services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by My Surface on 4/17/2017.
 */
public class NavieBayesSpamAlgo {
    HashMap<String, Integer> spam;
    HashMap<String, Integer> ham;
    int totalHam;
    int totalSpam;
    public static final int HAM = 0;
    public static final int SPAM = 1;

    public NavieBayesSpamAlgo(HashMap<String, Integer> spam, HashMap<String, Integer> ham){
        this.ham = ham;
        totalHam = ham.size();
        this.spam = spam;
        totalSpam = spam.size();
    }

    public int classifiy(File file) throws FileNotFoundException {
        return 0;
    }



}
