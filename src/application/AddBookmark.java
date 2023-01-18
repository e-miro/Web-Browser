package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputDialog;
import javafx.scene.web.WebEngine;

public class AddBookmark implements EventHandler<ActionEvent> {
	private Main m;

	public AddBookmark(Main m) {
		this.m = m;
	}

	@Override
	public void handle(ActionEvent arg0) {
		// Adds a new bookmark to the bookmarks list. This could be hectic.
		WebEngine engine = m.getWebBody().getWebView().getEngine();
		TextInputDialog askTitle = new TextInputDialog("Bookmark Name");
		askTitle.showAndWait();
		if (askTitle.getResult().equals(null)) {
			return;
		}
		String title = askTitle.getResult();
		Bookmark bookmark = new Bookmark(title, engine.getLocation(), m);

		Bookmarks bookmarks = m.getBookmarks();
		bookmarks.getItems().add(bookmark);

	}

}
