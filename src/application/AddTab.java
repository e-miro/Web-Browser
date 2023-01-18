package application;

import javafx.event.EventHandler;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;

//Got instructions on how to make menus clickable from here:
//https://stackoverflow.com/questions/10315774/javafx-2-0-activating-a-menu-like-a-menuitem

public class AddTab implements EventHandler<MouseEvent> {

	private Main m;
	private TabPane tabPane;

	public AddTab(Main m, TabPane tabPane) {
		this.m = m;
		this.tabPane = tabPane;
	}

//	@Override
//	public void handle(ActionEvent arg0) {
//		// TODO Auto-generated method stub
//		// System.out.println("In");
//		tabPane.getTabs().add(new WebTab(m));
//	}

	@Override
	public void handle(MouseEvent arg0) {
		// TODO Auto-generated method stub
		tabPane.getTabs().add(new WebTab(m));
		System.out.println("Hello");
	}
}
