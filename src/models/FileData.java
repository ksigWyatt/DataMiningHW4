package models;

import java.util.ArrayList;

/**
 * Used to hold the file information
 */
public class FileData {
    private ArrayList<String> words;
    private String name;

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

    /**
     * Used to print out the file content
     * @return the info contained in the file
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        for(String s : words){
            sb.append(s);
            sb.append(" ");
        }

        return sb.toString();
    }
}
