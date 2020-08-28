package stages_pack;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
// Root.fxml
// AddForm.fxml
// ScoreChart.fxml
// StageController.java
import javafx.stage.Stage;

public class StageMain extends Application {

	@Override
	public void start(Stage stage) throws Exception {
//		Parent root = FXMLLoader.load(getClass().getResource("Root.fxml"));

		FXMLLoader loader = new FXMLLoader(getClass().getResource("Root.fxml"));
		Parent root = loader.load();

		// Controller 에 stage 값을 넘겨준다.
		StageController cont = loader.getController();
		cont.setPrimaryStage(stage);

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
