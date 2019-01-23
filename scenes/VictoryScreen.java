package scenes;

import game.*;

import javafx.geometry.Pos;

import javafx.stage.Stage;

import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.input.MouseEvent;

//public class VictoryScreen{
public class VictoryScreen implements SceneCreator{
//fields ==================================================================================================================================
	private StudentsVsTeachers game;
	private Stage stage;

//constructors ==================================================================================================================================
	public VictoryScreen(StudentsVsTeachers game, Stage stage){
		this.game = game;
		this.stage = stage;
	}

//public getters and setters ============================================================================================================================


//public methods ==================================================================================================================================
	/**
 	 * Creates a Scene object that displays a message for when the game is won.
 	 *
 	 * @param Nothing.
 	 * @return Scene Contains the victory screen message.
 	 */
	public Scene createScene(){
		BorderPane root = new BorderPane();

    	VBox menuVBox = new VBox(10.0);

		Text victoryText = new Text("Congrats, you won! :D\n\n");
			victoryText.setFont(Font.font("Verdana", FontWeight.NORMAL, 48));
			victoryText.setFill(Color.WHITE);
		menuVBox.getChildren().add(victoryText);

		Text backText = new Text("Back");
			backText.setFont(Font.font("Verdana", FontWeight.NORMAL, 48));
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
