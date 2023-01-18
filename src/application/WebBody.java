package application;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;

// Got the bones for this from the simple web browser code
public class WebBody extends VBox {

	private WebView webView;
	private URLField urlField;
	private Main m;

	public WebBody(URLField urlField, Main m) {
		// Manages visual representation of webpage.
		this.m = m;
		webView = new WebView();
		this.urlField = urlField;
		// Manages web pages non-visually.
		getWebView().getEngine().load("http://www.google.com");

		this.getChildren().addAll(urlField, getWebView());
		WebBody.setVgrow(getWebView(), Priority.ALWAYS);

		getWebView().getEngine().getLoadWorker().stateProperty().addListener(new URLUpdater(urlField, getWebView(), m));
	}

	// Loads a new page based on an input URL
	public void load(String url) {
		getWebView().getEngine().load(url);
	}

	// Loads the previously visited webpage.
	public void back(Button forward, Button back) {
		WebHistory history = getWebView().getEngine().getHistory();
		if (history.getCurrentIndex() == 0) {
			return;
		}
		forward.setGraphic(new ImageView(new Image(".\\application\\Graphics\\Forward Arrow.png", 15, 15, true, true)));
		getWebView().getEngine().getHistory().go(-1);
		if (history.getCurrentIndex() == 0) {
			back.setGraphic(new ImageView(new Image(".\\application\\Graphics\\Grey Back.png", 15, 15, true, true)));
		}
	}

	// Loads the next webpage (only works if you've previously used the back button)
	public void forward(Button forward, Button back) {
		WebHistory history = getWebView().getEngine().getHistory();
		if (history.getCurrentIndex() == history.getEntries().size() - 1) {
			return;
		} else {
			getWebView().getEngine().getHistory().go(+1);
			back.setGraphic(new ImageView(new Image(".\\application\\Graphics\\Back Arrow.png", 15, 15, true, true)));
		}
		if (history.getCurrentIndex() == history.getEntries().size() - 1) {
			forward.setGraphic(
					new ImageView(new Image(".\\application\\Graphics\\Grey Forward.png", 15, 15, true, true)));
		}
	}

	// Reloads webpage.
	public void reload() {
		getWebView().getEngine().reload();

	}

	public WebView getWebView() {
		return webView;
	}

}
