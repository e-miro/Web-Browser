package application;

import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebView;

public class WebMenu extends MenuButton {
	WebView webView;
	Main m;

	public WebMenu(WebView webView, Main m) {
		this.m = m;
		this.setGraphic(new ImageView(new Image(".\\application\\Graphics\\burgMenu.png", 15, 15, true, true)));
		MenuItem zoom = new MenuItem("Change Zoom");
		MenuItem print = new MenuItem("Print Page to PDF");

		zoom.setOnAction(new Zoom(webView));
		print.setOnAction(new Print(webView));

		this.getItems().addAll(zoom, print);
		System.out.println(this.getItems());
		this.webView = webView;

	}

}
