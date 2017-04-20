package services;

import models.FileData;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by My Surface on 4/19/2017.
 */
public class FileLoader {
    ArrayList<FileData> files;
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
