package fxml;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AppRoot extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = (HBox) FXMLLoader.load(getClass().getResource("Root.fxml"));

		Scene scene = new Scene(root);

		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("FXML sample");

	}

	public static void main(String[] args) {
		launch(args);
	}

}
