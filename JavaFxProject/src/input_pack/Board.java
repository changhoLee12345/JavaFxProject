package input_pack;

public class Board {
	private String title;
	private String password;
	private String publicity;
	private String exitDate;
	private String content;
	public Board(String title, String password, String publicity, String exitDate, String content) {
		super();
		this.title = title;
		this.password = password;
		this.publicity = publicity;
		this.exitDate = exitDate;
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public String getPassword() {
		return password;
	}
	public String getPublicity() {
		return publicity;
	}
	public String getExitDate() {
		return exitDate;
	}
	public String getContent() {
		return content;
	}
}
