package tableview;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

public class TableViewController implements Initializable {

	@FXML
	TableView<User> tableView;

	@FXML
	Button btnSave;

	ObservableList<User> userList;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		btnSave.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				for (User user : userList) {
					System.out.println(user.getFirstName() + "," + user.getLastName() + "," + user.getEmail());
				}
			}
		});

		tableView.setEditable(true);

		userList = FXCollections.observableArrayList();
		userList.add(new User("id1", "firstName1", "lastName1", "email1", "Men", "2010/01/01"));
		userList.add(new User("id2", "firstName2", "lastName2", "email2", "Men", "2015/01/01"));
		userList.add(new User("id3", "firstName3", "lastName3", "email3", "Women", "2020/01/01"));

		TableColumn<User, String> tcId = new TableColumn<>("Id");
		tcId.setCellValueFactory(new PropertyValueFactory("id"));

		TableColumn<User, String> tcFName = new TableColumn<>("FName");
		tcFName.setCellValueFactory(new PropertyValueFactory("firstName"));
		tcFName.setCellFactory(new Callback<TableColumn<User, String>, TableCell<User, String>>() {
			@Override
			public TableCell<User, String> call(TableColumn<User, String> param) {
				return new EditingCell();
			}
		});
		tcFName.setOnEditCommit(new EventHandler<CellEditEvent<User, String>>() {
			@Override
			public void handle(CellEditEvent<User, String> event) {
				((User) event.getTableView().getItems().get(event.getTablePosition().getRow()))
						.setFirstName(event.getNewValue());
			}
		});

		TableColumn<User, String> tcLName = new TableColumn<>("LName");
		tcLName.setCellValueFactory(new PropertyValueFactory("lastName"));
		tcLName.setCellFactory(new Callback<TableColumn<User, String>, TableCell<User, String>>() {
			@Override
			public TableCell<User, String> call(TableColumn<User, String> param) {
				return new EditingCell();
			}
		});
		tcLName.setOnEditCommit(new EventHandler<CellEditEvent<User, String>>() {
			@Override
			public void handle(CellEditEvent<User, String> event) {
				User user = event.getTableView().getItems().get(event.getTablePosition().getRow());
				user.setLastName(event.getNewValue());
			}
		});

		TableColumn<User, String> tcEmail = new TableColumn<>("Email");
		tcEmail.setCellValueFactory(new PropertyValueFactory("email"));
		tcEmail.setCellFactory(new Callback<TableColumn<User, String>, TableCell<User, String>>() {
			@Override
			public TableCell<User, String> call(TableColumn<User, String> param) {
				return new EditingCell();
			}
		});
		tcEmail.setOnEditCommit(new EventHandler<CellEditEvent<User, String>>() {
			@Override
			public void handle(CellEditEvent<User, String> event) {
				User user = event.getTableView().getItems().get(event.getTablePosition().getRow());
				user.setLastName(event.getNewValue());
			}
		});

		TableColumn<User, String> tcGender = new TableColumn<>("Gender");
		tcGender.setCellValueFactory(new PropertyValueFactory("gender"));

		TableColumn<User, String> tcBirth = new TableColumn<>("BirthDate");
		tcBirth.setCellValueFactory(new PropertyValueFactory("birthDate"));

		tableView.getColumns().addAll(tcId, tcFName, tcLName, tcEmail, tcGender, tcBirth);

		tableView.setItems(userList);

		tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				tableViewDoubleClickEvent(event);
			}
		});
	} // end of initialize

	public void tableViewDoubleClickEvent(MouseEvent event) {

		if (event.getClickCount() == 2) {
			try {
				Stage stage = new Stage(StageStyle.UTILITY);
				stage.initModality(Modality.WINDOW_MODAL);
				stage.initOwner(btnSave.getScene().getWindow());
				stage.setResizable(false);

				Parent parent = FXMLLoader.load(getClass().getResource("DetailViewControl.fxml"));
				TextField firstName = (TextField) parent.lookup("#firstName");
				TextField lastName = (TextField) parent.lookup("#lastName");
				TextField email = (TextField) parent.lookup("#email");
				ComboBox<String> gender = (ComboBox) parent.lookup("#gender");
				DatePicker birthDate = (DatePicker) parent.lookup("#birthDate");
				RadioButton men = (RadioButton) parent.lookup("#men");
				RadioButton women = (RadioButton) parent.lookup("#women");

				firstName.setText(tableView.getSelectionModel().selectedItemProperty().getValue().getFirstName());
				lastName.setText(tableView.getSelectionModel().selectedItemProperty().getValue().getLastName());
				email.setText(tableView.getSelectionModel().selectedItemProperty().getValue().getEmail());
				gender.setValue(tableView.getSelectionModel().selectedItemProperty().getValue().getGender());

				if (tableView.getSelectionModel().selectedItemProperty().getValue().getGender().equals("Men")) {
					men.setSelected(true);
					women.setSelected(false);
				} else if (tableView.getSelectionModel().selectedItemProperty().getValue().getGender()
						.equals("Women")) {
					men.setSelected(false);
					women.setSelected(true);
				}
				birthDate.setValue(
						LocalDate.parse(tableView.getSelectionModel().selectedItemProperty().getValue().getBirthDate(),
								DateTimeFormatter.ofPattern("yyyy/MM/dd")));

				Scene scene = new Scene(parent);
				stage.setScene(scene);
				stage.show();

				Button btnOk = (Button) parent.lookup("#btnOk");
				Button btnCancel = (Button) parent.lookup("#btnCancel");

				btnOk.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {

					}
				});

				btnCancel.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						stage.close();
					}
				});

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	class EditingCell extends TableCell<User, String> {

		private TextField textField;

		public EditingCell() {
			super();
		}

		@Override
		public void startEdit() {
			System.out.println("startEdit()");
			if (!isEmpty()) {
				super.startEdit();
				createTextField();
				setText(null);
				setGraphic(textField);
				textField.selectAll();
			}
		}

		@Override
		public void cancelEdit() {
			System.out.println("cancelEdit()");
			super.cancelEdit();

			setText(getItem());
			setGraphic(null);
		}

		@Override
		protected void updateItem(String item, boolean empty) {
			System.out.println("updateItem() => " + item);
			super.updateItem(item, empty);
			if (empty) {
				setText(null);
				setGraphic(null);
			} else {
				if (isEditing()) {
					if (textField != null) {
						textField.setText(getString());
					}
					setText(null);
					setGraphic(textField);
				} else {
					setText(getString());
					setGraphic(null);
				}
			}
		}

		private void createTextField() {
			System.out.println("createTextField()");
			textField = new TextField(getString());
			textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
			textField.focusedProperty().addListener(new ChangeListener<Boolean>() {
				@Override
				public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
					if (!newValue) {
						commitEdit(textField.getText());
					}
				}
			});
		}

		private String getString() {
			return getItem() == null ? "" : getItem().toString();
		}
	}
}
