package application;

import javafx.collections.ObservableList;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

//In hindsight this should have just been a menu. No need for it's own class.
public class Bookmarks extends Menu {
	private Main m;
	private ObservableList<MenuItem> items = this.getItems();

	public Bookmarks(Main m) {
		this.m = m;
		this.setText("Bookmarks");
		MenuItem addBookmark = new MenuItem("Bookmark This Page");
		addBookmark.setOnAction(new AddBookmark(m));
		SeparatorMenuItem separator = new SeparatorMenuItem();
		this.getItems().addAll(addBookmark, separator);
		System.out.println(this);
	}

}
