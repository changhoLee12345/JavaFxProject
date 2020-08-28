package com.ydm.test;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class BookController implements Initializable {
	@FXML
	TableView<Book> tableView;
	@FXML
	Button btnAdd, btnChart;

	ObservableList<Book> bookList;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		bookList = FXCollections.observableArrayList(new Book("code1", "book1", "author1", 1000, 10),
				new Book("code2", "book2", "author2", 2000, 20));

		TableColumn<Book, ?> tc = tableView.getColumns().get(0);
		tc.setCellValueFactory(new PropertyValueFactory("bookCode"));

		tc = tableView.getColumns().get(1);
		tc.setCellValueFactory(new PropertyValueFactory("bookName"));

		tc = tableView.getColumns().get(2);
		tc.setCellValueFactory(new PropertyValueFactory("bookAuthor"));

		tc = tableView.getColumns().get(3);
		tc.setCellValueFactory(new PropertyValueFactory("bookPrice"));

		tc = tableView.getColumns().get(4);
		tc.setCellValueFactory(new PropertyValueFactory("bookQty"));

		tableView.setItems(bookList);

		// btnAdd
		btnAdd.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Stage dialog = new Stage(StageStyle.UTILITY);
				dialog.initModality(Modality.WINDOW_MODAL);
				dialog.initOwner(btnAdd.getScene().getWindow());
				try {
					Parent cont = FXMLLoader.load(getClass().getResource("AddBook.fxml"));
					Scene scene = new Scene(cont);
					dialog.setScene(scene);
					dialog.show();

					Button btnSave = (Button) cont.lookup("#btnSave");
					btnSave.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							TextField tfBookCode = (TextField) cont.lookup("#tfBookCode");
							TextField tfBookName = (TextField) cont.lookup("#tfBookName");
							TextField tfBookAuthor = (TextField) cont.lookup("#tfBookAuthor");
							TextField tfBookPrice = (TextField) cont.lookup("#tfBookPrice");
							TextField tfBookQty = (TextField) cont.lookup("#tfBookQty");

							Book book = new Book(tfBookCode.getText(), tfBookName.getText(), tfBookAuthor.getText(),
									Integer.parseInt(tfBookPrice.getText()), Integer.parseInt(tfBookQty.getText()));
							bookList.add(book);

							dialog.close();
						}
					});

					Button btnCancel = (Button) cont.lookup("#btnCancel");
					btnCancel.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							dialog.close();
						}
					});

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		// btnChart
		btnChart.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Stage dialog = new Stage(StageStyle.UTILITY);
				dialog.initModality(Modality.WINDOW_MODAL);
				dialog.initOwner(btnAdd.getScene().getWindow());
				try {
					Parent cont = FXMLLoader.load(getClass().getResource("BookChart.fxml"));
					BarChart bookChart = (BarChart) cont.lookup("#bookChart");

					XYChart.Series<String, Integer> priceSeries = new XYChart.Series<>();
					ObservableList<XYChart.Data<String, Integer>> priceDatas = FXCollections.observableArrayList();

					for (int i = 0; i < bookList.size(); i++) {
						priceDatas.add(new XYChart.Data(bookList.get(i).getBookName(), bookList.get(i).getBookPrice()));
					}
					priceSeries.setData(priceDatas);
					priceSeries.setName("단가");

					XYChart.Series<String, Integer> qtySeries = new XYChart.Series<>();
					ObservableList<XYChart.Data<String, Integer>> qtyDatas = FXCollections.observableArrayList();
					for (int i = 0; i < bookList.size(); i++) {
						qtyDatas.add(new XYChart.Data(bookList.get(i).getBookName(), bookList.get(i).getBookQty()));
					}
					qtySeries.setData(qtyDatas);
					qtySeries.setName("판매량");

					bookChart.setData(FXCollections.observableArrayList(priceSeries, qtySeries));

					Scene scene = new Scene(cont);
					dialog.setScene(scene);
					dialog.show();
					
					Button btnCancel = (Button) cont.lookup("#btnCancel");
					btnCancel.setOnAction(e -> dialog.close());

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		// tableView
		tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() == 2) { //더블클릭.
					Stage dialog = new Stage(StageStyle.UTILITY);
					dialog.initModality(Modality.WINDOW_MODAL);
					dialog.initOwner(btnAdd.getScene().getWindow());
					try {
						Parent cont = FXMLLoader.load(getClass().getResource("UpdateBook.fxml"));

						Label bookCode = (Label) cont.lookup("#bookCode");
						bookCode.setText(tableView.getSelectionModel().selectedItemProperty().getValue().getBookCode());

						TextField bookName = (TextField) cont.lookup("#bookName");
						bookName.setText(tableView.getSelectionModel().selectedItemProperty().getValue().getBookName());

						TextField bookAuthor = (TextField) cont.lookup("#bookAuthor");
						bookAuthor.setText(
								tableView.getSelectionModel().selectedItemProperty().getValue().getBookAuthor());

						TextField bookPrice = (TextField) cont.lookup("#bookPrice");
						bookPrice.setText(String.valueOf(
								tableView.getSelectionModel().selectedItemProperty().getValue().getBookPrice()));

						TextField bookQty = (TextField) cont.lookup("#bookQty");
						bookQty.setText(String.valueOf(
								tableView.getSelectionModel().selectedItemProperty().getValue().getBookQty()));

						Button btnCancel = (Button) cont.lookup("#btnCancel");
						btnCancel.setOnAction(e -> dialog.close());

						Button btnUpdate = (Button) cont.lookup("#btnUpdate");
						btnUpdate.setOnAction(new EventHandler<ActionEvent>() {
							public void handle(ActionEvent event) {
								updateActionEvent(bookCode, bookPrice, bookQty);
								dialog.close();
							};
						});
						
						

						Scene scene = new Scene(cont);
						dialog.setScene(scene);
						dialog.show();

					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});

	}

	public void updateActionEvent(Label bookCode, TextField bookPrice, TextField bookQty) {

		for (int i = 0; i < bookList.size(); i++) {
			if (bookList.get(i).getBookCode().equals(bookCode.getText())) {
				bookList.get(i).setBookPrice(Integer.parseInt(bookPrice.getText()));
				bookList.get(i).setBookQty(Integer.parseInt(bookQty.getText()));
				break;
			}

		}
	}

}
