package models;

import java.util.ArrayList;

/**
 * Created by My Surface on 4/18/2017.
 */
public class FileData {
    ArrayList<String> words;
    String name;

    public FileData(ArrayList<String> words, String name) {
        this.words = words;
        this.name = name;
    }

    public ArrayList<String> getWords() {
        return words;
    }

    public String getName() {
        return name;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(name + "\n");
        for(String s : words){
            sb.append(s + " ");
        }

        return sb.toString();
    }
}
