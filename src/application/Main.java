package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// Got information about how to get the selected tab from here:
//https://stackoverflow.com/questions/30276935/how-to-get-the-selected-tab-from-the-tabpane-with-javafx

// Got instructions on how to make menus clickable from here:
// https://stackoverflow.com/questions/10315774/javafx-2-0-activating-a-menu-like-a-menuitem

public class Main extends Application {
	private String title = "MiroBrowse";
	private Bookmarks bookmarks;
	private HistoryManager history;
	private Stage primaryStage;

	private TabPane tabPane;

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		bookmarks = new Bookmarks(this);
		history = new HistoryManager(this);

		tabPane = new TabPane();
		WebTab init = new WebTab(this);

		Label tabButton = new Label();
		tabButton.setGraphic(new ImageView(new Image("./application/Graphics/newTab.png", 20, 20, false, true)));
		tabButton.setOnMousePressed(new AddTab(this, tabPane));

		Menu addTab = new Menu();
		addTab.setGraphic(tabButton);

		tabPane.getTabs().addAll(init);

		MenuBar buttonHolder = new MenuBar();
		buttonHolder.getMenus().addAll(addTab, bookmarks, history);

		VBox topStack = new VBox();

		topStack.getChildren().addAll(buttonHolder, tabPane);

		primaryStage.setScene(new Scene(topStack));
		primaryStage.sizeToScene();
		primaryStage.setTitle(title);

		primaryStage.getIcons().add(new Image("./application/Graphics/Face.jpg"));

		// Dharina taught me about the existance of bind. I got the bones of the code
		// from:
		// https://stackoverflow.com/questions/27820793/find-the-title-of-current-webpage-open-in-webengine-javafx
		primaryStage.titleProperty().bind(((WebTab) tabPane.getSelectionModel().getSelectedItem()).getWebBody()
				.getWebView().getEngine().titleProperty());
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public WebBody getWebBody() {
		return ((WebTab) tabPane.getSelectionModel().getSelectedItem()).getWebBody();
	}

	public Bookmarks getBookmarks() {
		return bookmarks;
	}

	public TabPane getTabPane() {
		return tabPane;
	}

	public HistoryManager getHistory() {
		return history;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

}
