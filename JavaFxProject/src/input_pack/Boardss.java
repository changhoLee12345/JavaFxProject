package input_pack;

import javafx.beans.property.SimpleStringProperty;

public class Boardss {
	// title, password, publicity, exitDate, content;
	private SimpleStringProperty title;
	private SimpleStringProperty password;
	private SimpleStringProperty publicity;
	private SimpleStringProperty exitDate;
	private SimpleStringProperty content;

	public Boardss() {
		this.title = new SimpleStringProperty();
	}

	public Boardss(String title, String password, String publicity, String exitDate, String content) {
		this.title.set(title);
		this.password.set(password);
		this.publicity.set(publicity);
		this.exitDate.set(exitDate);
		this.content.set(content);
	}

	public String getTitle() {
		return title.get();
	}
	public void setTitle(String title) {
		this.title.set(title);
	}
	public SimpleStringProperty titleProperty() {
		return title;
	}

}
