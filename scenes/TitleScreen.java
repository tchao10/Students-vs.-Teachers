package scenes;

import game.*;

import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.geometry.Pos;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;

public class TitleScreen implements SceneCreator{
//fields ==================================================================================================================================
	private StudentsVsTeachers game;
	private Stage stage;

//constructors ==================================================================================================================================
	public TitleScreen(StudentsVsTeachers game, Stage stage){
		this.game = game;
		this.stage = stage;
	}

//public getters and setters ============================================================================================================================


//public methods ==================================================================================================================================
	/**
	 * Creates a Scene object containing text which allows for the ability to switch between the game screen, instructions, and credits.
	 * Clicking the "Start" text starts the MainGameScreen's animationTimer.
	 *
	 * @param Nothing.
	 * @return Scene Contains "Start", "Instructions", and "Credits" buttons
	 */
	public Scene createScene(){
		BorderPane root = new BorderPane();

		VBox titleVBox = new VBox();
		titleVBox.setPadding(new Insets(50.0, 0.0, 0.0, 0.0));
			Text titleText = new Text("Students vs Teachers");
			titleText.setFont(Font.font("Verdana", FontWeight.NORMAL, 96));
			//titleText.setTextAlignment(TextAlignment.CENTER);
			titleText.setFill(Color.WHITE);
		titleVBox.getChildren().add(titleText);
		titleVBox.setSpacing(50);
		titleVBox.setAlignment(Pos.CENTER);
		root.setTop(titleVBox);

		VBox menuVBox = new VBox(10.0);
			//START START START START START START START START START START START START START START START START START
			Text startText = new Text("Start");
			startText.setFont(Font.font("Verdana", FontWeight.NORMAL, 48));
			startText.setFill(Color.WHITE);
			startText.setOnMouseClicked(new EventHandler<MouseEvent>(){
          		@Override
          		public void handle(MouseEvent mouseEvent){
					game.resetMainGameScreen(stage);
					stage.setScene(game.getMainGameScreenScene());
					game.getMainGameScreen().start();
          		}
      		});
		menuVBox.getChildren().add(startText);

			//INSTRUCTIONS INSTRUCTIONS INSTRUCTIONS INSTRUCTIONS INSTRUCTIONS INSTRUCTIONS INSTRUCTIONS INSTRUCTIONS
			Text instructionsText = new Text("Instructions");
			instructionsText.setFont(Font.font("Verdana", FontWeight.NORMAL, 48));
			instructionsText.setFill(Color.WHITE);
			instructionsText.setOnMouseClicked(new EventHandler<MouseEvent>(){
				@Override
				public void handle(MouseEvent mouseEvent){
					stage.setScene(game.getInstructionsScreenScene());
				}
			});
		menuVBox.getChildren().add(instructionsText);

			//CREDITS CREDITS CREDITS CREDITS CREDITS CREDITS CREDITS CREDITS CREDITS CREDITS CREDITS CREDITS CREDITS
			Text creditsText = new Text("Credits");
			creditsText.setFont(Font.font("Verdana", FontWeight.NORMAL, 48));
			creditsText.setFill(Color.WHITE);
			creditsText.setOnMouseClicked(new EventHandler<MouseEvent>(){
				@Override
				public void handle(MouseEvent mouseEvent){
					stage.setScene(game.getCreditsScreenScene());
				}
			});
		menuVBox.getChildren().add(creditsText);
		menuVBox.setAlignment(Pos.CENTER);
		root.setCenter(menuVBox);

	 	return new Scene(root, 1250, 750, Color.BLACK);
	}


//private methods ==================================================================================================================================


//main method ==================================================================================================================================
	public static void main(String[] args){

	}
}
