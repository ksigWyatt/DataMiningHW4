package services;

import models.FileData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Filter {
    public int numFiltered = 0;
    /**
     * Remove non-alphanumeric characters using regex in the training files
     * @param files the training
     */
    public void filterSpecialCharacters(ArrayList<FileData> files){
        for(FileData file : files){
            int lineNumber = 0;
            for(String string : file.getWords()){
                string = string.replaceAll("[^a-zA-Z0-9 ]",""); // regex to replace the special characters
                file.getWords().remove(lineNumber); // remove the line
                file.getWords().add(lineNumber,string); // add the new line
                lineNumber++; // increment line number
            }
        }
    }

    /**
     * Used to remove uncommon words in the training. uncommon is words less than 10
     * @param files the training files
     */
    public void filterUnCommonWords(ArrayList<FileData> files){

        HashMap<String, Integer> wordCounts = new HashMap<>();
        StringTokenizer st;
        String str;
        for(FileData file : files){
            for(String line : file.getWords()){
                st = new StringTokenizer(line);
                while(st.hasMoreTokens()){
                    str = st.nextToken();
                    wordCounts.put(str,(wordCounts.getOrDefault(str,0) + 1));
                }
            }
        }

        for(FileData file : files){
            int lineNumber = 0;
            for(String line : file.getWords()){
                st = new StringTokenizer(line);
                while(st.hasMoreTokens()){
                    str = st.nextToken();
                    if(wordCounts.get(str) < 10){
                        line = line.replaceAll(str,"");
                        file.getWords().remove(lineNumber);
                        file.getWords().add(lineNumber,line);
                        numFiltered++;
                    }
                }
                lineNumber++;
            }
        }
    }
}
