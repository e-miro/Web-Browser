package application;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;

// Got the code structure for this from 
// http://blogs.kiyut.com/tonny/2013/07/30/javafx-webview-addhyperlinklistener/

public class URLUpdater implements ChangeListener {
	URLField urlField;
	WebView webView;
	Main m;

	public URLUpdater(URLField urlField, WebView webView, Main m) {
		this.urlField = urlField;
		this.webView = webView;
		this.m = m;
	}

	public void changed(ObservableValue ov, Object oldState, Object newState) {
		// TODO Auto-generated method stub
		if (newState == Worker.State.SUCCEEDED) {
			((TextField) urlField.getChildren().get(3)).setText(webView.getEngine().getLocation());
			m.getHistory().getItems().add(0,
					new Bookmark(webView.getEngine().getTitle(), webView.getEngine().getLocation(), m));
			m.getPrimaryStage().titleProperty().unbind();
			m.getPrimaryStage().setTitle(webView.getEngine().getTitle());
		}
	}

}
