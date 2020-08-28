package panes.vbox_hbox;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class VBoxMain extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
//		Parent root = FXMLLoader.load(getClass().getResource("Root.fxml"));
		VBox root = new VBox();
		root.setPadding(new Insets(10));

		ImageView iv = new ImageView();
		iv.setFitWidth(300);
		iv.setPreserveRatio(true);
		iv.setImage(new Image("/panes/images/javafx.jpg"));

		HBox hbox = new HBox();
		hbox.setAlignment(Pos.CENTER);
		hbox.setSpacing(20);

		Button btn1 = new Button("이전");
		Button btn2 = new Button("다음");
		HBox.setHgrow(btn2, Priority.ALWAYS);
		btn2.setMaxWidth(Double.MAX_VALUE);

		hbox.getChildren().addAll(btn1, btn2);

		VBox.setMargin(hbox, new Insets(10, 0, 0, 0));
		root.getChildren().add(iv);
		root.getChildren().add(hbox);

		Scene scene = new Scene(root);
		primaryStage.setTitle("VBox HBox 예제");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
