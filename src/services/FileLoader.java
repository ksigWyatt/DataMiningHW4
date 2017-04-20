package services;

import models.FileData;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Used to load the files into memory
 */
public class FileLoader {
    ArrayList<FileData> files; // holds the info we need for the email
    public void loadFiles(File[] folder) throws FileNotFoundException {
        files = new ArrayList<>();
        ArrayList<String> words;
        Scanner scan;
        for(File file : folder){
            scan = new Scanner(file);
            words = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            while(scan.hasNext()){
                sb.append(scan.nextLine());
            }
            words.add(sb.toString());
            files.add(new FileData(words, file.getName()));
        }
    }


    public ArrayList<FileData> getFiles() {
        return files;
    }
}
