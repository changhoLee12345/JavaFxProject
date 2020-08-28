package menus_pack;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MenuController implements Initializable {

	@FXML
	ImageView ivNew;

	@FXML
	MenuItem menuNew, menuOpen, menuClose;

	@FXML
	BorderPane root;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		menuNew.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				AnchorPane ap = new AnchorPane();
				ap.setPrefSize(300, 300);
				ImageView iv = new ImageView();
				iv.setImage(new Image("/icons/new.png"));
				iv.setFitHeight(290);
				iv.setFitWidth(290);
				ap.getChildren().add(iv);

				root.setCenter(ap);
			}
		});

		menuOpen.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
//				ivNew.setImage(new Image("/icons/save.png"));
				AnchorPane ap = new AnchorPane();
				ap.setPrefSize(300, 300);
				ImageView iv = new ImageView();
				iv.setImage(new Image("/icons/save.png"));
				iv.setFitHeight(290);
				iv.setFitWidth(290);
				ap.getChildren().add(iv);

				root.setCenter(ap);
			}
		});

		menuClose.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Platform.exit();
			}
		});

	} // end of initialize()
}
