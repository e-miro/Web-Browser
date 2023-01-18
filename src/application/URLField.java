package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class URLField extends HBox {

	private Button launch;
	private Button back;
	private Button forward;
	private Button reload;
	private TextField textField;
	private WebBody webBody;
	private WebMenu menu;
	private Main m;

	public URLField(Main m) {
		textField = new TextField();

		launch = new Button();
		back = new Button();
		forward = new Button();
		reload = new Button();

		this.m = m;

		this.getChildren().addAll(back, forward, reload, textField, launch);
		URLField.setHgrow(textField, Priority.ALWAYS);

		arrangeLaunch();
		arrangeText();
		arrangeBack();
		arrangeForward();
		arrangeReload();

	}

	// Sets functionality for the reload button. Reloads current page.
	private void arrangeReload() {
		reload.setGraphic(new ImageView(new Image(".\\application\\Graphics\\Refresh.png", 15, 15, true, true)));
		reload.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				webBody.reload();
			}

		});
	}

	// Sets functionality for the browser's forward button. Moves forwards through
	// history array.
	private void arrangeForward() {
		forward.setGraphic(new ImageView(new Image(".\\application\\Graphics\\Grey Forward.png", 15, 15, true, true)));
		forward.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				webBody.forward(forward, back);
			}

		});
	}

	// Sets functionality for the browser's back button. Moves backwards through
	// history array.
	private void arrangeBack() {
		back.setGraphic(new ImageView(new Image(".\\application\\Graphics\\Grey Back.png", 15, 15, true, true)));
		back.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				webBody.back(forward, back);
			}

		});
	}

	// This will make the web browser display the result of the new URL.
	private void arrangeLaunch() {
		launch.setGraphic(new ImageView(new Image(".\\application\\Graphics\\Launch.png", 15, 15, true, true)));
		launch.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				back.setGraphic(
						new ImageView(new Image(".\\application\\Graphics\\Back Arrow.png", 15, 15, true, true)));
				String url = textField.getText();

				// If the URL box is empty it will return to homepage.
				if (url == "") {
					webBody.load("https://www.google.com");
					return;
				}

				// We know it's a url if it has a . but no " ". With " " would be a google
				// search.
				if (url.contains(".") && !url.contains(" ")) {

					// This checks for necessary prefixes and adds them if they're missing.
					if (!url.contains("https://")) {
						if (!url.contains("www.")) {
							webBody.load("https://www.".concat(url));
							return;
						}
						webBody.load("https://".concat(url));
						return;
					}

					// This represents a url that has passed all the checks and can be loaded as is.
					webBody.load(url);
					return;

					// This is a url that in fact represents a search query. Will run search.
				} else {
					webBody.load("https://www.google.com/search?q=".concat(url));
				}
			}
		});
	}

	// This will make the textField fire the launch button when the user presses
	// enter.
	private void arrangeText() {
		textField.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent arg0) {
				if (arg0.getCode().equals(KeyCode.ENTER)) {
					launch.fire();
				}
			}

		});
	}

	// Passes through the webBody object so this class can access it. Also sets up
	// the dropdown menu box
	public void setWebBody(WebBody webBody) {
		this.webBody = webBody;
		menu = new WebMenu(webBody.getWebView(), m);
		this.getChildren().add(menu);
	}

	public WebMenu getMenu() {
		return menu;
	}

}
