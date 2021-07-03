package program;

import java.util.Scanner;

import Model.CitizenAgeIsLessThen18;
import Model.CitizenIDOutOfRnage;
import Model.Elections;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Program extends Application{

	public static void main(String[] args) throws CitizenIDOutOfRnage , CitizenAgeIsLessThen18, Exception{
			launch(args);
		}

		@Override
		public void start(Stage primaryStage) throws CitizenIDOutOfRnage , CitizenAgeIsLessThen18, Exception {
		Elections election = new Elections();
		Model.Model model = new Model.Model(election);
		View.View view = new View.View(primaryStage);
		Scanner s = new Scanner(System.in);
		Controller.Controller controller =  new Controller.Controller(view, model);
	//	election.start(s); // start the basic program
		s.close();
	}
}
