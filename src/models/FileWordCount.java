package models;

/**
 * Created by My Surface on 4/18/2017.
 */
public class FileWordCount {
    String fileName;
    int wordCount;

    public FileWordCount(String filename, int wordCount){
        this.fileName = filename;
        this.wordCount = wordCount;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getWordCount() {
        return wordCount;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }
}
