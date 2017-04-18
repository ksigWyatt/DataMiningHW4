package application;

import controller.Controller;
import models.WordCounts;
import view.View;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;


public class Main {

	public static void main(String[] args){
		View theView = new View();
		WordCounts counts = new WordCounts();
		Controller controller = new Controller(theView, counts);
		controller.runApp();
	}

}
