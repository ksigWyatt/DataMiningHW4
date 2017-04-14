package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;


public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException  {
		// TODO Auto-generated method stub
		
		System.out.println("-------Training-------\n");
		readTrainFiles();
		System.out.println("\n-------Testing-------\n");
		readTestFiles();

	}
	
	
	public static void readTrainFiles() throws FileNotFoundException, IOException {

		//reading the directory of files
		try(Stream<Path> paths = Files.walk(Paths.get("data/train"))) {
		    paths.forEach(filePath -> {
		    	
		        if (Files.isRegularFile(filePath)) {
		        	
		            //creating a Path object to store the file's name as it increments through
		            String fileName = filePath.getFileName().toString();
		            
		        System.out.println(fileName);
		            
		        }
		    });
		} 
		
	}
	
	public static void readTestFiles() throws FileNotFoundException, IOException {

		//reading the directory of files
		try(Stream<Path> paths = Files.walk(Paths.get("/data/test"))) {
		    paths.forEach(filePath -> {
		    	
		        if (Files.isRegularFile(filePath)) {
		        	
		            //creating a Path object to store the file's name as it increments through
		            String fileName = filePath.getFileName().toString();
		            
		        System.out.println(fileName);
		            
		        }
		    });
		} 
		
	}

}
