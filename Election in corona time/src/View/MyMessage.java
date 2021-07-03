package View;


import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MyMessage {
	String msg;
	Label lbl;
	ArrayList<Label> allLabels;
	VBox vb;
	public MyMessage(Stage stage) {
		lbl = new Label();
		allLabels = new ArrayList<>();

		vb = new VBox();
		vb.getChildren().addAll(allLabels);
		Scene scene = new Scene(vb,400,200);
		stage.setScene(scene);
		stage.show();
	}
	
	public void setMessage(String str) {
		lbl.setText(str);
	}
	public void addMessage(String str) {
		allLabels.add(new Label(str));
		// clean vb
		vb.getChildren().clear();
		vb.getChildren().addAll(allLabels);
		vb.setAlignment(Pos.CENTER);
	}
}
