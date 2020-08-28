package view_pack;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ViewController implements Initializable {
	@FXML
	ListView<String> listView;
	@FXML TableView<Phone> tableView;
	@FXML ImageView imageView;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// 초기화 지정.
		ObservableList<String> list = FXCollections.observableArrayList(
				"GalaxyS1", "GalaxyS2", "GalaxyS3", "GalaxyS4");
		list.add("GalaxyS5");
		list.add("GalaxyS6");
		list.add("GalaxyS7");
		listView.setItems(list);
		
		listView.getSelectionModel().selectedIndexProperty()
		.addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number oldVal, Number newVal) {
				tableView.getSelectionModel().select(newVal.intValue()-1);
				tableView.scrollTo(newVal.intValue()-1);
			}
		});
		
		tableView.setItems(FXCollections.observableArrayList(
				new Phone("GalaxyS1", "phone01.png"),
				new Phone("GalaxyS2", "phone02.png"),
				new Phone("GalaxyS3", "phone03.png"),
				new Phone("GalaxyS4", "phone04.png"),
				new Phone("GalaxyS5", "phone05.png"),
				new Phone("GalaxyS6", "phone06.png"),
				new Phone("GalaxyS7", "phone07.png")
				));
		TableColumn<Phone, ?> tcSmartPhone = tableView.getColumns().get(0);
		tcSmartPhone.setCellValueFactory(new PropertyValueFactory("smartPhone"));
		
//		tableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory("smartPhone"));
		
		TableColumn<Phone, ?> tcImage = tableView.getColumns().get(1);
		tcImage.setCellValueFactory(new PropertyValueFactory("image"));
		
		tableView.getSelectionModel().selectedItemProperty()
		         .addListener(new ChangeListener<Phone>() {

			@Override
			public void changed(ObservableValue<? extends Phone> arg0, Phone oldVal, Phone newVal) {
				imageView.setImage(new Image("/images/"+ newVal.getImage()));
			}
			
		});
		imageView.setImage(new Image("/images/phone01.png"));

	}

	public void handleBtnRegAction() {

	}

	public void handleBtnCancelAction() {

	}

}
