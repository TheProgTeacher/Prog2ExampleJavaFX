/*
 * This example show how you can make a simple app with JavaFX.
 * It also show you how to create a menu with the help of an enum.
 * */
package application;


import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Orientation;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;

/*
 * The main class of the application.
 * It has to override the class 'Application' from the JavaFX library.
 * */
public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}
	//Constants
	final Insets defMargins = new Insets(10);
	
	//Class variables
	static Text txt;
	static TextField txtField;
	
	//In this method you define how your window is going to look
	@Override
	public void start(Stage stage) {
		try {
			//Initial setups
			BorderPane rootPane = new BorderPane();
			Scene scene = new Scene(rootPane, 400, 400);
			stage.setTitle("Window example");
			stage.setMaximized(true);
			stage.setScene(scene);
			
			//Setting up the main panes of the window
			FlowPane centerPane = new FlowPane();
			centerPane.setAlignment(Pos.CENTER);
			centerPane.setOrientation(Orientation.VERTICAL);
			rootPane.setCenter(centerPane);
			
			FlowPane bottomPane = new FlowPane();
			bottomPane.setAlignment(Pos.CENTER);
			rootPane.setBottom(bottomPane);
			
			//Setting up class variable components
			Menu.setState(Menu.MAIN);
			txt = new Text(Menu.getMenuText());
			txt.setFont(new Font(36));
			centerPane.getChildren().add(txt);
			
			txtField = new TextField();
			txtField.setMaxWidth(100);
			centerPane.getChildren().add(txtField);
			
			//Setting up local variable components
			Button btn1 = new Button("Action 1");
			btn1.setOnAction(new Btn1Action());
			bottomPane.getChildren().add(btn1);
			FlowPane.setMargin(btn1, defMargins);
			
			Button btn2 = new Button("Action 2");
			btn2.setOnAction(new Btn2Action());
			bottomPane.getChildren().add(btn2);
			FlowPane.setMargin(btn2, defMargins);
			
			Image sideImg = new Image("file:cat.jpg");
			ImageView img = new ImageView(sideImg);
			rootPane.setRight(img);
			
			stage.show();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}

//The event handler for the first button
class Btn1Action implements EventHandler<ActionEvent> {
	@Override
	public void handle(ActionEvent e) {
		switch(Menu.getState()) {
		case MAIN:
			Menu.setState(Menu.FIRST);
			break;
		case FIRST:
			Main.txtField.setText("FIZZ");
			break;
		case SECOND:
			Main.txtField.setText("BUZZ");
			break;
		}
		Main.txt.setText(Menu.getMenuText());
	}
}

//The event handler for the second button
class Btn2Action implements EventHandler<ActionEvent> {
	@Override
	public void handle(ActionEvent e) {
		switch(Menu.getState()) {
		case MAIN:
			Menu.setState(Menu.SECOND);
			break;
		case FIRST:
		case SECOND:
			Menu.setState(Menu.MAIN);
			break;
		}
		Main.txt.setText(Menu.getMenuText());
	}
}

//Enum for defining and selecting different menues
enum Menu {
	MAIN, FIRST, SECOND;
	
	private static Menu state;
	
	public static void setState(Menu m) {
		state = m;
	}
	
	public static Menu getState() {
		return state;
	}
	
	public static String getMenuText() {
		switch(state) {
		case MAIN:
			return "[1] First menu\n[2] Second menu";
		case FIRST:
			return "[1] Print FIZZ\n[2] Back";
		case SECOND:
			return "[1] Print BUZZ\n[2] Back";
		default:
			return "error";
		}
	}
}
