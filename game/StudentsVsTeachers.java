//Authors:
//Tommy Chao, Stephen Yuan, Marco Chen, Bill Luu
//Period 2, AP Computer Science, MKHS

//In the src folder, do the following to compile/run:
//"javac game\StudentsVsTeachers.java"
//"java game.StudentsVsTeachers"

package game;

import other.*;
import scenes.*;
import students.*;
import teachers.*;

import javafx.application.Application;

import javafx.scene.Scene;

import javafx.stage.Stage;

import java.util.Scanner;

public class StudentsVsTeachers extends Application{
//fields ==================================================================================================================================
	private TitleScreen titleScreen;
	private InstructionsScreen instructionsScreen;
	private CreditsScreen creditsScreen;
	private MainGameScreen mainGameScreen;
	private PauseScreen pauseScreen;
	private VictoryScreen victoryScreen;
	private DefeatScreen defeatScreen;

	private Scene titleScreenScene;
	private Scene instructionsScreenScene;
	private Scene creditsScreenScene;
	private Scene mainGameScreenScene;
	private Scene pauseScreenScene;
	private Scene victoryScreenScene;
	private Scene defeatScreenScene;

//constructors ================================================================================================================================
	public StudentsVsTeachers(){}

	public StudentsVsTeachers(Stage stage){
		this.titleScreen = new TitleScreen(this, stage);
		this.instructionsScreen = new InstructionsScreen(this, stage);
		this.creditsScreen = new CreditsScreen(this, stage);
		this.mainGameScreen = new MainGameScreen(this, stage);
		this.pauseScreen = new PauseScreen(this, stage);
		this.victoryScreen = new VictoryScreen(this, stage);
		this.defeatScreen = new DefeatScreen(this, stage);

		this.titleScreenScene = titleScreen.createScene();
		this.instructionsScreenScene = instructionsScreen.createScene();
		this.creditsScreenScene = creditsScreen.createScene();
		this.mainGameScreenScene = mainGameScreen.createScene();
		this.pauseScreenScene = pauseScreen.createScene();
		this.victoryScreenScene = victoryScreen.createScene();
		this.defeatScreenScene = defeatScreen.createScene();
	}

//public getters and setters ========================================================================================================================
	public TitleScreen getTitleScreen(){return this.titleScreen;}
	public InstructionsScreen getInstructionsScreen(){return this.instructionsScreen;}
	public CreditsScreen getCreditsScreen(){return this.creditsScreen;}
	public MainGameScreen getMainGameScreen(){return this.mainGameScreen;}
	public PauseScreen getPauseScreen(){return this.pauseScreen;}
	public VictoryScreen getVictoryScreen(){return this.victoryScreen;}
	public DefeatScreen getDefeatScreen(){return this.defeatScreen;}

	public Scene getTitleScreenScene(){return this.titleScreenScene;}
	public Scene getInstructionsScreenScene(){return this.instructionsScreenScene;}
	public Scene getCreditsScreenScene(){return this.creditsScreenScene;}
	public Scene getMainGameScreenScene(){return this.mainGameScreenScene;}
	public Scene getPauseScreenScene(){return this.pauseScreenScene;}
	public Scene getVictoryScreenScene(){return this.victoryScreenScene;}
	public Scene getDefeatScreenScene(){return this.defeatScreenScene;}

//public methods ================================================================================================================================
	/**
 	 * This resets the mainGameScreen so that the game is replayable
	 * without relaunching from the console.
 	 *
 	 * @param Stage This program's JavaFX stage.
 	 * @return Nothing.
 	 */
	public void resetMainGameScreen(Stage stage){
		this.mainGameScreen = new MainGameScreen(this, stage);
		this.mainGameScreenScene = mainGameScreen.createScene();
	}

	/**
 	 * This is the start method for this JavaFX Application.
	 * This sets up and displays the title screen scene.
 	 *
 	 * @param Stage This program's JavaFX stage.
 	 * @return Nothing.
 	 */
	@Override
	public void start(Stage stage){
		stage.setTitle("Students vs. Teachers");
		stage.setResizable(false);

		StudentsVsTeachers game = new StudentsVsTeachers(stage);


		stage.setScene(game.titleScreenScene);


		//Used for debugging

		/*
		Scanner kboard = new Scanner(System.in);
		System.out.print("What scene do you want to see?\n\n1 = title screen\n2 = instructions\n3 = credits screen\n4 = main game screen\n5 = pause menu\n6 = victory screen\n7 = defeat screen\n\n");
		String sceneNum = kboard.nextLine();

		switch (sceneNum){
			case "1": stage.setScene(game.titleScreenScene);
				break;
			case "2": stage.setScene(game.instructionsScreenScene);
				break;
			case "3": stage.setScene(game.creditsScreenScene);
				break;
			case "4": stage.setScene(game.mainGameScreenScene);
				break;
			case "5": stage.setScene(game.pauseScreenScene);
				break;
			case "6": stage.setScene(game.victoryScreenScene);
				break;
			default:  stage.setScene(game.defeatScreenScene);
				break;
		}
		*/

		stage.show();
	}

//main method ==================================================================================================================================
	public static void main(String[] args){
		launch(args);
	}
}
