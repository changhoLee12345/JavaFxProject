package stages_pack;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StageController implements Initializable {
	@FXML
	TableView<Student> tableView;
	@FXML
	Button btnAdd, btnChart;

	ObservableList<Student> scores;

	Stage primaryStage;

	void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		scores = FXCollections.observableArrayList();

		btnAdd.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				buttonAddAction(arg0);
			}
		});
		btnChart.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				buttonChartAction(arg0);
			}
		});

		TableColumn<Student, ?> tcName = tableView.getColumns().get(0);
		tcName.setCellValueFactory(new PropertyValueFactory("name"));

		TableColumn<Student, ?> tcKorean = tableView.getColumns().get(1);
		tcKorean.setCellValueFactory(new PropertyValueFactory("korean"));

		TableColumn<Student, ?> tcMath = tableView.getColumns().get(2);
		tcMath.setCellValueFactory(new PropertyValueFactory("math"));

		TableColumn<Student, ?> tcEnglish = tableView.getColumns().get(3);
		tcEnglish.setCellValueFactory(new PropertyValueFactory("english"));

		tableView.setItems(scores);
	}

	public void buttonAddAction(ActionEvent ae) {
		Stage addStage = new Stage(StageStyle.UTILITY);
		addStage.initModality(Modality.WINDOW_MODAL);
		addStage.initOwner(btnAdd.getScene().getWindow());

		try {
			Parent parent = FXMLLoader.load(getClass().getResource("AddForm.fxml"));
			Scene scene = new Scene(parent);
			addStage.setScene(scene);
			addStage.setResizable(false);
			addStage.show();

			Button btnFormAdd = (Button) parent.lookup("#btnFormAdd");
			btnFormAdd.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					TextField txtName = (TextField) parent.lookup("#txtName");
					TextField txtKorean = (TextField) parent.lookup("#txtKorean");
					TextField txtMath = (TextField) parent.lookup("#txtMath");
					TextField txtEnglish = (TextField) parent.lookup("#txtEnglish");
					Student student = new Student(txtName.getText(), Integer.parseInt(txtKorean.getText()),
							Integer.parseInt(txtMath.getText()), Integer.parseInt(txtEnglish.getText()));
					scores.add(student);
					addStage.close();
				}
			});

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void buttonChartAction(ActionEvent ae) {
		Stage chartStage = new Stage(StageStyle.UTILITY);
		chartStage.initModality(Modality.WINDOW_MODAL);
//		chartStage.initOwner(btnAdd.getScene().getWindow());
		chartStage.initOwner(primaryStage);

		try {
			Parent parent = FXMLLoader.load(getClass().getResource("ScoreChart.fxml"));
			BarChart barChart = (BarChart) parent.lookup("#barChart");

			XYChart.Series<String, Integer> seriesKorean = 
					new XYChart.Series<String, Integer>();
			ObservableList<XYChart.Data<String, Integer>> datasKorean = 
					FXCollections.observableArrayList();
			for (int i = 0; i < scores.size(); i++) {
				datasKorean.add(new XYChart.Data(
						scores.get(i).getName(), scores.get(i).getKorean())
				);
				// "이름", 국어점수
			}
			seriesKorean.setData(datasKorean);
			seriesKorean.setName("국어");
			
			XYChart.Series<String, Integer> seriesMath = 
					new XYChart.Series<String, Integer>();
			ObservableList<XYChart.Data<String, Integer>> datasMath = 
					FXCollections.observableArrayList();
			for (int i = 0; i < scores.size(); i++) {
				datasMath.add(new XYChart.Data(
						scores.get(i).getName(), scores.get(i).getMath())
				);
				// "이름", 수학점수
			}
			seriesMath.setData(datasMath);
			seriesMath.setName("수학");
			
			XYChart.Series<String, Integer> seriesEnglish = 
					new XYChart.Series<String, Integer>();
			ObservableList<XYChart.Data<String, Integer>> datasEnglish = 
					FXCollections.observableArrayList();
			for (int i = 0; i < scores.size(); i++) {
				datasEnglish.add(new XYChart.Data(
						scores.get(i).getName(), scores.get(i).getEnglish())
				);
				// "이름", 수학점수
			}
			seriesEnglish.setData(datasEnglish);
			seriesEnglish.setName("영어");

			barChart.setData(FXCollections.observableArrayList(seriesKorean,seriesMath,seriesEnglish));

			Scene scene = new Scene(parent);
			chartStage.setScene(scene);
			chartStage.show();
			chartStage.setResizable(false);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
