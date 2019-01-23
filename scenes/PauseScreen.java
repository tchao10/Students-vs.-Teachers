package scenes;

import game.*;

import javafx.stage.Stage;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;

public class PauseScreen implements SceneCreator{
//fields ==================================================================================================================================
	private StudentsVsTeachers game;
	private Stage stage;

//constructors ==================================================================================================================================
	public PauseScreen(StudentsVsTeachers game, Stage stage){
		this.game = game;
		this.stage = stage;
	}

//public getters and setters ============================================================================================================================


//public methods ==================================================================================================================================
	/**
	 * Creates a Scene object for when the game is paused.
	 *
	 * @param Nothing.
	 * @return Scene Contains the pause menu.
	 */
	public Scene createScene(){
		BorderPane root = new BorderPane();

    	VBox menuVBox = new VBox(10.0);

			Text resumeText = new Text("Resume\n\n");
			resumeText.setFont(Font.font("Verdana", FontWeight.NORMAL, 48));
			resumeText.setFill(Color.WHITE);
			resumeText.setOnMouseClicked(new EventHandler<MouseEvent>(){
				@Override
				public void handle(MouseEvent mouseEvent){
			    	stage.setScene(game.getMainGameScreenScene());
					game.getMainGameScreen().getTimer().start();
				}
			});
			menuVBox.getChildren().add(resumeText);

			Text quitText = new Text("Quit");
			quitText.setFont(Font.font("Verdana", FontWeight.NORMAL, 48));
			quitText.setFill(Color.WHITE);
			quitText.setOnMouseClicked(new EventHandler<MouseEvent>(){
				@Override
		    	public void handle(MouseEvent mouseEvent){
						stage.setScene(game.getTitleScreenScene());
					}
				});
			menuVBox.getChildren().add(quitText);

		menuVBox.setAlignment(Pos.CENTER);
		root.setCenter(menuVBox);

 		return new Scene(root, 1250, 750, Color.BLACK);
 	}

//private methods ==================================================================================================================================


//main method ==================================================================================================================================
	public static void main(String[] args){

	}
}
