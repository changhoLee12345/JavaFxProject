package button_pack;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ButtonController implements Initializable {
	@FXML
	CheckBox chk1;
	@FXML
	CheckBox chk2;
	@FXML
	ImageView imageView;

	@FXML
	ToggleGroup group;
	@FXML
	RadioButton rad1;
	@FXML
	RadioButton rad2;
	@FXML
	RadioButton rad3;
	@FXML
	ImageView imageView2;
	@FXML
	Button exitBtn;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			@Override
			public void changed(ObservableValue<? extends Toggle> arg0, Toggle oldVal, Toggle newVal) {
				System.out.println(newVal.getUserData().toString()); // BubbleChar, AreaChart,...
				imageView2.setImage(new Image("/images/" + newVal.getUserData().toString() + ".png"));
			}
		});

		chk2.setOnAction((e) -> handleCheckAction(e));

		exitBtn.setOnAction((e) -> Platform.exit());

	}

	public void handleCheckAction(ActionEvent event) {
		String imageName = "";
		if (chk1.isSelected() && chk2.isSelected()) {
			imageName = "geek-glasses-hair.gif";
		} else if (chk2.isSelected()) {
			imageName = "geek-hair.gif";
		} else if (chk1.isSelected()) {
			imageName = "geek-glasses.gif";
		} else {
			imageName = "geek.gif";
		}
		imageView.setImage(new Image("/images/" + imageName));
	}

}
