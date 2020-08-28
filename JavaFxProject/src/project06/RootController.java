package project06;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

public class RootController implements Initializable {

	@FXML
	Button btnChange;
	@FXML
	RadioButton rad1, rad2, rad3;
	@FXML
	Label answer1, answer2, answer3;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnChange.setOnAction(event -> changeRadioValue());
	}

	public void changeRadioValue() {

		int[] intAry = new int[3];

		for (int i = 0; i < 3;) {

			intAry[i] = (int) (Math.random() * 3);
			if (i == 1 && intAry[0] == intAry[1])
				continue;
			else if (i == 2)
				if (intAry[0] == intAry[1] || intAry[0] == intAry[2] || intAry[1] == intAry[2])
					continue;

			i++;

		}

		String[] strAry = new String[3];
		strAry[0] = answer1.getText();
		strAry[1] = answer2.getText();
		strAry[2] = answer3.getText();

		strAry[intAry[0]] = answer1.getText();
		strAry[intAry[1]] = answer2.getText();
		strAry[intAry[2]] = answer3.getText();

		System.out.println("answer is : " + strAry[intAry[0]]);

		rad1.setText(strAry[0]);
		rad2.setText(strAry[1]);
		rad3.setText(strAry[2]);
	}
}
