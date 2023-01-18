package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.web.WebView;

public class Zoom implements EventHandler<ActionEvent> {

	private WebView webView;

	// Sets up fields.
	public Zoom(WebView webView) {
		this.webView = webView;
	}

	// Always returns a double.
	private double getZoom() {
		TextInputDialog getNewZoom = new TextInputDialog("100%");

		getNewZoom.setHeaderText("Please Enter new zoom level");

		getNewZoom.showAndWait();
		if (getNewZoom.getResult() == null) {
			return -6969;
		}
		String result = getNewZoom.getResult();
		if (result.contains("%")) {
			System.out.println("Definitely contains a %");
			result = result.replace("%", "");
			System.out.println(result);
		}
		Double newZoom;
		try {
			newZoom = Double.parseDouble(result) / 100;
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR, "You may only enter numbers and percentage signs.");
			alert.showAndWait();
			if (alert.getResult() == ButtonType.OK) {
				return getZoom();
			} else {
				return -6969;
			}
		}
		return newZoom;

	}

	@Override
	public void handle(ActionEvent arg0) {
		// Makes web page zoom in.
		double newZoom = getZoom();
		if (newZoom == -6969) {
			return;
		}
		webView.setZoom(newZoom);
	}

}
