package scenes;

import game.*;

import javafx.geometry.Pos;

import javafx.stage.Stage;

import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.input.MouseEvent;

public class CreditsScreen implements SceneCreator{
//fields ==================================================================================================================================
	private StudentsVsTeachers game;
	private Stage stage;

//constructors ==================================================================================================================================
	public CreditsScreen(StudentsVsTeachers game, Stage stage){
		this.game = game;
		this.stage = stage;
	}

//public getters and setters ============================================================================================================================


//public methods ==================================================================================================================================
	/**
	 * Creates a Scene object containing the credits for this game.
	 *
	 * @param Nothing.
	 * @return Scene Contains the credits for the game.
	 */
	public Scene createScene(){
		BorderPane root = new BorderPane();

		VBox menuVBox = new VBox(10.0);
			Text creditsText = new Text("Credits:\n\nTommy Chao\nMarco Chen\nStephen Yuan\nBill Luu\n\n\n");
			creditsText.setFont(Font.font("Verdana", FontWeight.NORMAL, 24));
			creditsText.setTextAlignment(TextAlignment.CENTER);
			creditsText.setFill(Color.WHITE);
		menuVBox.getChildren().add(creditsText);

		//TITLE TITLE TITLE TITLE TITLE TITLE TITLE TITLE TITLE TITLE TITLE TITLE TITLE TITLE TITLE TITLE
			Text backText = new Text("Back");
			backText.setFont(Font.font("Verdana", FontWeight.NORMAL, 48));
			backText.setTextAlignment(TextAlignment.CENTER);
			backText.setFill(Color.WHITE);
			backText.setOnMouseClicked(new EventHandler<MouseEvent>(){
				@Override
				public void handle(MouseEvent mouseEvent){
					stage.setScene(game.getTitleScreenScene());
				}
			});
		menuVBox.getChildren().add(backText);

		menuVBox.setAlignment(Pos.CENTER);
		root.setCenter(menuVBox);

		return new Scene(root, 1250, 750, Color.BLACK);
  }


//private methods ==================================================================================================================================


//main method ==================================================================================================================================
	public static void main(String[] args){

	}
}
