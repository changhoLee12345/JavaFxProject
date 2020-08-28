package com.ydm.test;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Book {
	private SimpleStringProperty bookCode;
	private SimpleStringProperty bookName;
	private SimpleStringProperty bookAuthor;
	private SimpleIntegerProperty bookPrice;
	private SimpleIntegerProperty bookQty;

	public Book(String bookCode, String bookName, String bookAuthor, int bookPrice, int bookQty) {
		this.bookCode = new SimpleStringProperty(bookCode);
		this.bookName = new SimpleStringProperty(bookName);
		this.bookAuthor = new SimpleStringProperty(bookAuthor);
		this.bookPrice = new SimpleIntegerProperty(bookPrice);
		this.bookQty = new SimpleIntegerProperty(bookQty);
	}

	// bookCode
	public String getBookCode() {
		return this.bookCode.get();
	}

	public void setBookCode(String bookCode) {
		this.bookCode.set(bookCode);
	}

	public SimpleStringProperty bookCodeProperty() {
		return this.bookCode;
	}

	// bookName
	public String getBookName() {
		return this.bookName.get();
	}

	public void setBookName(String bookName) {
		this.bookName.set(bookName);
	}

	public SimpleStringProperty bookNameProperty() {
		return this.bookName;
	}

	// bookAuthor
	public String getBookAuthor() {
		return this.bookAuthor.get();
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor.set(bookAuthor);
	}

	public SimpleStringProperty bookAuthorProperty() {
		return this.bookAuthor;
	}

	// bookPrice
	public int getBookPrice() {
		return this.bookPrice.get();
	}

	public void setBookPrice(int bookPrice) {
		this.bookPrice.set(bookPrice);
	}

	public SimpleIntegerProperty bookPriceProperty() {
		return this.bookPrice;
	}

	// bookQty
	public int getBookQty() {
		return this.bookQty.get();
	}

	public void setBookQty(int bookQty) {
		this.bookQty.set(bookQty);
	}

	public SimpleIntegerProperty bookQtyProperty() {
		return this.bookQty;
	}
}
