package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;

public class Bookmark extends MenuItem {

	private String title;
	private String location;
	private Main m;

	public Bookmark(String title, String location, Main m) {
		// TODO Auto-generated constructor stub
		this.title = title;
		this.location = location;
		this.m = m;
		setText(title);
		this.setOnAction(new BookmarkHandler(location, m));
	}

}

class BookmarkHandler implements EventHandler<ActionEvent> {

	private String location;
	private Main m;

	public BookmarkHandler(String location, Main m) {
		this.m = m;
		this.location = location;
	}

	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		m.getWebBody().getWebView().getEngine().load(location);
	}

}