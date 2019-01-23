package scenes;

import game.*;

import javafx.stage.Stage;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class InstructionsScreen implements SceneCreator{
//fields ==================================================================================================================================
	private StudentsVsTeachers game;
	private Stage stage;

//constructors ==================================================================================================================================
	public InstructionsScreen(StudentsVsTeachers game, Stage stage){
		this.game = game;
		this.stage = stage;
	}

//public getters and setters ============================================================================================================================


//public methods ==================================================================================================================================
	/**
	 * Creates a Scene object that contains the lore and
	 * basic instructions of the game.
	 *
     * @param Nothing.
     * @return Scene Contains the game's instructions.
     */
	public Scene createScene(){
 		BorderPane root = new BorderPane();

		VBox menuVBox = new VBox(10.0);
			Text instructionsText = new Text("The students have had enough and are rebelling against their teachers!\nDo you think you can help them stop the teachers?\n\n\n\nThis game is played almost exactly like Plants vs. Zombies,\nand consists of three waves of teachers. Defeat all three waves and you win!");
			instructionsText.setFont(Font.font("Verdana", FontWeight.NORMAL, 24));
			instructionsText.setFill(Color.WHITE);
			instructionsText.setTextAlignment(TextAlignment.CENTER);
		menuVBox.getChildren().add(instructionsText);

		//TITLE TITLE TITLE TITLE TITLE TITLE TITLE TITLE TITLE TITLE TITLE TITLE TITLE TITLE TITLE TITLE
			Text titleText = new Text("Back");
			titleText.setFont(Font.font("Verdana", FontWeight.NORMAL, 48));
			titleText.setFill(Color.WHITE);
			titleText.setTextAlignment(TextAlignment.CENTER);
			titleText.setOnMouseClicked(new EventHandler<MouseEvent>(){
				@Override
				public void handle(MouseEvent mouseEvent){
					stage.setScene(game.getTitleScreenScene());
				}
			});
		menuVBox.getChildren().add(titleText);

		menuVBox.setAlignment(Pos.CENTER);
		root.setCenter(menuVBox);

 		return new Scene(root, 1250, 750, Color.BLACK);
 	}

//private methods ==================================================================================================================================


//main method ==================================================================================================================================
	public static void main(String[] args){

	}
}
