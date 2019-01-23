package scenes;

import game.*;

import javafx.geometry.Pos;

import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class DefeatScreen implements SceneCreator{
//fields ==================================================================================================================================
	private StudentsVsTeachers game;
	private Stage stage;

//constructors ==================================================================================================================================
	public DefeatScreen(StudentsVsTeachers game, Stage stage){
		this.game = game;
		this.stage = stage;
	}

//public getters and setters ============================================================================================================================


//public methods ==================================================================================================================================
	/**
	 * Creates a Scene object that displays a message for when the game is lost.
	 *
	 * @param Nothing.
	 * @return Scene Contains the defeat screen message.
	 */
	public Scene createScene(){
		BorderPane root = new BorderPane();

    	VBox menuVBox = new VBox(10.0);

		Text defeatText = new Text("A teacher made it past your students.\nYou lost... :(\n\n\nTry again?\n\n");
			defeatText.setFont(Font.font("Verdana", FontWeight.NORMAL, 48));
			defeatText.setTextAlignment(TextAlignment.CENTER);
			defeatText.setFill(Color.WHITE);
		menuVBox.getChildren().add(defeatText);

		Text backText = new Text("Back");
			backText.setFont(Font.font("Verdana", FontWeight.NORMAL, 48));
			defeatText.setTextAlignment(TextAlignment.CENTER);
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
