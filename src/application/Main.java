package application;

import controller.Controller;
import models.WordCounts;

public class Main {

	public static void main(String[] args){
		WordCounts counts = new WordCounts();
		Controller controller = new Controller(counts);
		controller.runApp();
	}

}
