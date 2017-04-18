package view;

import java.util.HashMap;

/**
 * Created by My Surface on 4/17/2017.
 */
public class View {


    public View(){

    }

    public void NavieBayesAlgo(HashMap<String, Integer> spamCount, HashMap<String, Integer> nonSpamCount){
//        for(String s : spamCount.keySet()){
//            System.out.println(s + " " + spamCount.get(s));
//        }

        for(String s : nonSpamCount.keySet()){
            System.out.println(s + " " + nonSpamCount.get(s));
        }
    }

    public void KNNAlgo(){

    }
}
