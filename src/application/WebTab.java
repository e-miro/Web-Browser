package application;

import javafx.scene.control.Tab;

// Could set up an action listener for when this tab becomes the selected one. Have it set Main's title field.
public class WebTab extends Tab {
	private WebBody webBody;
	private URLField urlField;

	public WebTab(Main m) {
		urlField = new URLField(m);
		webBody = new WebBody(urlField, m);
		urlField.setWebBody(webBody);

		this.setContent(webBody);
		this.textProperty().bind(webBody.getWebView().getEngine().titleProperty());
	}

	public WebBody getWebBody() {
		return webBody;
	}

	public URLField getURLField() {
		return urlField;
	}

}
