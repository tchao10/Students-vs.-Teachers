package scenes;

import game.*;
import students.*;
import teachers.*;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Font;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class MainGameScreen implements SceneCreator{
//fields ==================================================================================================================================
	private StudentsVsTeachers game;
	private Stage stage;
	private int brainPower;

	private int currentWave = 0;
	private int elapsedSeconds = 0;
	private AnimationTimer timer = null;
	private int timeSinceLastSpawn = 5;
	private int previouslySpawnedRow = -1;
	private boolean expelHammerIsSelected = false;
	private int secondsToSetToVictoryScreen = 999;
	private ArrayList<Student> instantiatedStudents = new ArrayList<Student>();
	private ArrayList<Teacher> instantiatedTeachers = new ArrayList<Teacher>();
	private ArrayList<Projectile> instantiatedProjectiles = new ArrayList<Projectile>();
	private Student[] studentObjectsList = new Student[]{new StudyingStudent(), new NaughtyStudent(), new NaughtierStudent(), new FootballStudent(), new ChemistryStudent()};
	private String[] studentList = new String[]{"StudyingStudent", "NaughtyStudent", "NaughtierStudent", "FootballStudent", "ChemistryStudent"};
	private Teacher[] teacherObjectsList = new Teacher[]{new StandardTeacher(), new ProfessorTeacher(), new AthleticPETeacher(), new FatPETeacher(), new PrincipalTeacher()};
	private String[] teacherList = new String[]{"StandardTeacher", "ProfessorTeacher", "AthleticPETeacher", "FatPETeacher", "PrincipalTeacher"};
	private Pane[] centerRows = new Pane[5];
	private ArrayList<Teacher>[] centerTeachersInEachRow = new ArrayList[]{new ArrayList<Teacher>(), new ArrayList<Teacher>(), new ArrayList<Teacher>(), new ArrayList<Teacher>(), new ArrayList<Teacher>()};
	private Tile[][] centerTiles = new Tile[5][9];
	private StackPane brainPowerBox = new StackPane();
	private VBox dialogueBox = new VBox();
	private Tile[] leftTiles = new Tile[5];
	private Tile[] rightTiles = new Tile[5];

//constructors ==================================================================================================================================
	public MainGameScreen(StudentsVsTeachers game, Stage stage){
		this.game = game;
		this.stage = stage;
		this.brainPower = 250;
	}

//public getters and setters ============================================================================================================================
	public Stage getStage(){return this.stage;}
	public StudentsVsTeachers getGame(){return this.game;}
	public int getBrainPower(){return this.brainPower;}
	public int getElapsedSeconds(){return this.elapsedSeconds;}
	public AnimationTimer getTimer(){return this.timer;}
	public boolean getExpelHammerIsSelected(){return this.expelHammerIsSelected;}
	public ArrayList<Student> getInstantiatedStudents(){return this.instantiatedStudents;}
	public ArrayList<Teacher> getInstantiatedTeachers(){return this.instantiatedTeachers;}
	public ArrayList<Projectile> getInstantiatedProjectiles(){return this.instantiatedProjectiles;}
	public VBox getDialogueBox(){return this.dialogueBox;}

	public Student[] getStudentObjectsList(){return this.studentObjectsList;}
	public String[] getStudentList(){return this.studentList;}
	public Pane[] getCenterRows(){return this.centerRows;}
	public ArrayList<Teacher>[] getCenterTeachersInEachRow(){return this.centerTeachersInEachRow;}
	public Tile[][] getCenterTiles(){return this.centerTiles;}
	public Tile[] getLeftTiles(){return this.leftTiles;}
	public Tile[] getRightTiles(){return this.rightTiles;}

	public void setExpelHammerIsSelected(boolean newExpelHammerIsSelected){this.expelHammerIsSelected = newExpelHammerIsSelected;}

//public methods ==================================================================================================================================
	/**
	 * Creates a Scene object containing all of the visual elements of the game.
	 * Includes all Tile objects and GUI display.
	 *
	 * @param Nothing.
	 * @return Scene Contains the entire game screen.
	 */
	public Scene createScene(){
		BorderPane root = new BorderPane();
		root.setStyle("-fx-background-image: url(\"/scenes/birdseyeview.png\")");

    	root.setCenter(this.createCenterTiles());
		root.setTop(this.createTopTiles());
		//root.setBottom(this.createBottomTiles());
    	root.setLeft(this.createLeftTiles());
    	//root.setRight(this.createRightTiles());

		Scene mainGameScreenScene = addMouseEvents(this, new Scene(root, 1260, 760));

		return mainGameScreenScene;
 	}

	/**
	 * Creates a VBox containing five Panes which represent each row of the field.
	 * Each Pane contains a TilePane which holds the nine Tiles per row.
	 * The Panes will also display the teachers onto the screen.
	 *
	 * @param Nothing.
	 * @return VBox Contains 5 Panes and each Pane contains a TilePane.
	 */
	public VBox createCenterTiles(){
		VBox centerTiles = new VBox(50);
		centerTiles.setSpacing(45);
		centerTiles.setMaxWidth(950.0);
		centerTiles.setMaxHeight(475.0);
			int centerTilesRowCount = 0;
			int centerTilesColCount = 0;
 			for (int r=0; r<5; r++){
				Pane row = new Pane();
				int startingXCoord = 0;
				for (int c=0; c<9; c++){
					Tile tile = new Tile(this, startingXCoord, r, c, Color.GRAY);
					this.centerTiles[r][c] = tile;
					tile.setUpCenterTile();
					row.getChildren().add(tile);
					startingXCoord += 105;
				}
				this.centerRows[r] = row;
				centerTiles.getChildren().add(row);
    		}
		return centerTiles;
	}

	/**
	 * Creates an HBox which contain all of the
	 * GUI-like elements at the top of the screen.
	 *
	 * @param Nothing.
	 * @return HBox Contains the top elements.
	 */
	public HBox createTopTiles(){
		HBox topTiles = new HBox(50.0);
		topTiles.setPadding(new Insets(6.5, 0.0, 0.0, 0.0));
		topTiles.setAlignment(Pos.CENTER);
			//Brain Power
			StackPane brainPower = this.brainPowerBox;
			brainPower.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-background-color: #D3D3D3;");
			HBox brainPowerInfo = new HBox(5.0);
				brainPowerInfo.setPadding(new Insets(6.0, 6.0, 6.0, 6.0));
				brainPowerInfo.getChildren().add(new ImageView(new Image("/students/brain.png")));
				Text brainPowerInfoCount = new Text(""+this.brainPower);
				brainPowerInfoCount.setFont(new Font(64));
				brainPowerInfo.getChildren().add(brainPowerInfoCount);
			brainPower.getChildren().add(brainPowerInfo);
			this.brainPowerBox = brainPower;
			topTiles.getChildren().add(brainPower);

			//Expel Hammer
			Tile expelHammer = new Tile(this, 1, 0, Color.LIMEGREEN);
			expelHammer.setStrokeWidth(2.0);
			expelHammer.setStroke(Color.BLACK);
			expelHammer.setImage("/scenes/expelhammer.png");
			expelHammer.setOnMouseClicked(new EventHandler<MouseEvent>(){
				@Override
		    	public void handle(MouseEvent mouseEvent){
						setExpelHammerIsSelected(true);
						getGame().getMainGameScreenScene().setCursor(new ImageCursor(new Image("/scenes/expelhammercursor.png")));
						for (int centRow=0; centRow<getCenterTiles().length; centRow++){
							for (int centCol=0; centCol<getCenterTiles()[centRow].length; centCol++){
								Tile currentCenterTile = getCenterTiles()[centRow][centCol];
								currentCenterTile.setExpelHammerIsSelected(true);
							}
						}
					}
				});
			topTiles.getChildren().add(expelHammer);

			//Dialogue
			VBox dialogueBox = this.dialogueBox;
			dialogueBox.setMinWidth(600.0);
			dialogueBox.setMaxWidth(600.0);
			dialogueBox.setMinHeight(100.0);
			dialogueBox.setMaxHeight(100.0);
			dialogueBox.setAlignment(Pos.CENTER);
			dialogueBox.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-background-color: #D3D3D3;");
			dialogueBox.setPadding(new Insets(10.0, 10.0, 10.0, 10.0));
			Text dialogueBoxText = new Text("");
				dialogueBoxText.setStyle("-fx-fill: crimson; -fx-stroke: black; -fx-stroke-width: 0.4; -fx-font-weight: bold;");
				dialogueBoxText.setFont(new Font(28));
				dialogueBoxText.setTextAlignment(TextAlignment.CENTER);
			dialogueBox.getChildren().add(dialogueBoxText);
			this.dialogueBox = dialogueBox;
			topTiles.getChildren().add(dialogueBox);

			//Pause Button
			Tile pauseButton = new Tile(this, 4, 0, Color.LIMEGREEN);
			pauseButton.setStrokeWidth(2.0);
			pauseButton.setStroke(Color.BLACK);
			pauseButton.setImage("/scenes/pausebutton.png");
			pauseButton.setOnMouseClicked(new EventHandler<MouseEvent>(){
				@Override
		    	public void handle(MouseEvent mouseEvent){
						getTimer().stop();
						stage.setScene(game.getPauseScreenScene());
					}
				});
			topTiles.getChildren().add(pauseButton);
		return topTiles;
	}

	/**
	 * Creates a VBox containing five Tiles which store each type of Student.
	 * This VBox is placed on the left side of this BorderPane.
	 *
	 * @param Nothing.
	 * @return VBox Containing the price tags for each Student.
	 */
	public VBox createLeftTiles(){
		VBox leftTiles = new VBox(3.0);
 		leftTiles.setPadding(new Insets(0.0, 0.0, 0.0, 70.0));
		leftTiles.setAlignment(Pos.CENTER);
		for (int i=0; i<5; i++){
			Tile tile = new Tile(this, i, 0, Color.ORANGE);
			this.leftTiles[i] = tile;
			tile.setUpLeftTile();
			tile.setImage(this.studentObjectsList[i].getDefaultImgSrc());
			tile.setStrokeWidth(4.0);
			tile.setStroke(Color.BLACK);
        	leftTiles.getChildren().add(tile);
		}

		return leftTiles;
	}

	/**
	 * Adds MouseEvents to @param scene.
	 * Detects which Tile the expel hammer is applied to.
	 * Checks for which Student tag is selected and places that Student onto the selected Tile if there is sufficient brain power.
	 * Updates the dialogue text box accordingly if an attempt to buy a Student that costs more brain power than is currently available.
	 *
	 * @param MainGameScreen Allows access to MainGameScreen's setters and getters.
	 * @param Scene The Scene which will have MouseEvents applied to.
	 * @return Scene Same as @param scene but with MouseEvents.
	 */
	public Scene addMouseEvents(MainGameScreen game, Scene scene){
		scene.addEventFilter(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent mouseEvent){
				if (getExpelHammerIsSelected()){
					getGame().getMainGameScreenScene().setCursor(null);
					setExpelHammerIsSelected(false);
					for (int centRow=0; centRow<getCenterTiles().length; centRow++){
						for (int centCol=0; centCol<getCenterTiles()[centRow].length; centCol++){
							Tile currentCenterTile = getCenterTiles()[centRow][centCol];
							currentCenterTile.setExpelHammerIsSelected(false);
						}
					}
				}

				int indexOfSelectedStudent = -1;
				String stringOfSelectedStudent = null;
				int priceOfSelectedStudent = Integer.MAX_VALUE;

				for (int k=0; k<getLeftTiles().length; k++){
					Tile currentTile = getLeftTiles()[k];
					if (currentTile.getStroke().equals(Color.RED)){
						indexOfSelectedStudent = k;
						stringOfSelectedStudent = getStudentObjectsList()[indexOfSelectedStudent].getClassNameString();
						priceOfSelectedStudent = getStudentObjectsList()[indexOfSelectedStudent].getCost();
					}
					currentTile.setStroke(Color.BLACK);
				}

				if (indexOfSelectedStudent != -1){
					for (int centRow=0; centRow<getCenterTiles().length; centRow++){
						for (int centCol=0; centCol<getCenterTiles()[centRow].length; centCol++){
							Tile currentCenterTile = getCenterTiles()[centRow][centCol];
							if (currentCenterTile.getFill() == Color.LIGHTGRAY && !currentCenterTile.getStudentIsOccupying()){
								if (priceOfSelectedStudent <= getBrainPower()){
									setBrainPower(getBrainPower() - priceOfSelectedStudent);
									currentCenterTile.setStudentIsOccupying(true);
									switch (stringOfSelectedStudent){
										case "StudyingStudent":
											StudyingStudent newStudyingStudent = new StudyingStudent(game);
											newStudyingStudent.setTile(currentCenterTile);
											currentCenterTile.setImage(newStudyingStudent.getImgSrc());
											currentCenterTile.setOccupyingStudent(newStudyingStudent);
											getInstantiatedStudents().add(newStudyingStudent);
											break;
										case "NaughtyStudent":
											NaughtyStudent newNaughtyStudent = new NaughtyStudent(game);
											newNaughtyStudent.setTile(currentCenterTile);
											currentCenterTile.setImage(newNaughtyStudent.getImgSrc());
											currentCenterTile.setOccupyingStudent(newNaughtyStudent);
											getInstantiatedStudents().add(newNaughtyStudent);
											break;
										case "NaughtierStudent":
											NaughtierStudent newNaughtierStudent = new NaughtierStudent(game);
											newNaughtierStudent.setTile(currentCenterTile);
											currentCenterTile.setImage(newNaughtierStudent.getImgSrc());
											currentCenterTile.setOccupyingStudent(newNaughtierStudent);
											getInstantiatedStudents().add(newNaughtierStudent);
											break;
										case "FootballStudent":
											FootballStudent newFootballStudent = new FootballStudent(game);
											newFootballStudent.setTile(currentCenterTile);
											currentCenterTile.setImage(newFootballStudent.getImgSrc());
											currentCenterTile.setOccupyingStudent(newFootballStudent);
											getInstantiatedStudents().add(newFootballStudent);
											break;
										case "ChemistryStudent":
											ChemistryStudent newChemistryStudent = new ChemistryStudent(game);
											newChemistryStudent.setTile(currentCenterTile);
											currentCenterTile.setImage(newChemistryStudent.getImgSrc());
											currentCenterTile.setOccupyingStudent(newChemistryStudent);
											getInstantiatedStudents().add(newChemistryStudent);
											break;
									}
								} else {
									changeDialogueText("Insufficient funds!");
								}
							}
						}
					}
				} else if (((Text)(getDialogueBox().getChildren().get(0))).getText().equals("Insufficient funds!")){
					changeDialogueText("");
				}
			}
		});

		return scene;
	}

	/**
	 * Starts the game by applying an animationTimer.
	 * The game runs off of a 60 frame cycle, doing various commands each cycle such as performing attacks and movements.
	 * Provides all of the animation/processing in the game for which time is used.
	 *
	 * @param Nothing.
	 * @return Nothing.
 	 */
	public void start(){
		MainGameScreen screen = this;

		this.timer = new AnimationTimer(){
			int framesPerSecondCounter = 0;

			@Override
			public void handle(long now){
				framesPerSecondCounter++;
				if (framesPerSecondCounter >= 60){
					screen.elapsedSeconds++;
					framesPerSecondCounter = 0;

					screen.updateText(screen.elapsedSeconds);

					System.out.println(screen.elapsedSeconds);
					if (screen.elapsedSeconds > 15 && screen.elapsedSeconds <= 60){
						screen.spawnWave1Teacher();
					} else if (screen.elapsedSeconds > 60 && screen.elapsedSeconds <= 180){
						screen.spawnWave2Teacher();
					} else if (screen.elapsedSeconds > 180 && screen.elapsedSeconds <= 240){
						screen.spawnWave3Teacher(screen.elapsedSeconds);
					}

					for (Student student : getInstantiatedStudents()){
						switch (student.getClassNameString()){
							case "StudyingStudent":
								StudyingStudent studyingStudent = (StudyingStudent)student;
								studyingStudent.incrementSeconds();
								break;
							case "NaughtyStudent":
								NaughtyStudent naughtyStudent = (NaughtyStudent)student;
								if (naughtyStudent.checkAttack()){
									naughtyStudent.attack();
								}
								break;
							case "NaughtierStudent":
								NaughtierStudent naughtierStudent = (NaughtierStudent)student;
								if (naughtierStudent.checkAttack()){
									naughtierStudent.attack();
								}
								break;
							case "FootballStudent":
								FootballStudent footballStudent = (FootballStudent)student;
								footballStudent.checkHealth();
								break;
						}
					}
				}

				for (int projCount = 0; projCount < getInstantiatedProjectiles().size(); projCount++){
					Projectile currProjectile = getInstantiatedProjectiles().get(projCount);
					currProjectile.move();
					Teacher hitTeacher = currProjectile.checkHit();
					if (hitTeacher != null){
						hitTeacher.takeDamage(currProjectile.getDamage());
						currProjectile.despawn();
						projCount--;
					}
				}

				for (int stuCount = 0; stuCount < getInstantiatedStudents().size(); stuCount++){
					if (getInstantiatedStudents().get(stuCount).getClassNameString().equals("ChemistryStudent")){
						ChemistryStudent chemistryStudent = (ChemistryStudent)getInstantiatedStudents().get(stuCount);
						if (chemistryStudent.updateAnimation() == 5){
							stuCount--;
						}
					}
				}

				for (Teacher teacher : getInstantiatedTeachers()){
					Student studentInRange = teacher.getClosestStudentInRange();
					if (studentInRange != null){
						if(teacher.incrementAttackCooldown()){
							teacher.attack(studentInRange);
						}
					}

					if (teacher.getClosestStudentInRange() == null){
						teacher.walk();
					}

					if (teacher.getSprite().getX() + teacher.getSprite().getWidth() < 0){
						getTimer().stop();
						screen.stage.setScene(screen.game.getDefeatScreenScene());
					}
				}

				if (elapsedSeconds > 240 && screen.instantiatedTeachers.size() == 0){
					if (screen.secondsToSetToVictoryScreen == 999){
						screen.secondsToSetToVictoryScreen = screen.getElapsedSeconds() + 3;
					}
					if (screen.getElapsedSeconds() == screen.secondsToSetToVictoryScreen){
						getTimer().stop();
						screen.stage.setScene(screen.game.getVictoryScreenScene());
					}
				}
			}
		};

		timer.start();
	}

	/**
	 * Updates the brain power count at the top of the screen.
	 *
	 * @param int The new amount of brain power.
	 * @return Nothing.
	 */
	public void setBrainPower(int newBrainPower){
		this.brainPower = newBrainPower;
		this.brainPowerBox.getChildren().remove(0, 1);

		HBox newBrainPowerInfo = new HBox(5.0);
			newBrainPowerInfo.setPadding(new Insets(6.0, 6.0, 6.0, 6.0));
			newBrainPowerInfo.getChildren().add(new ImageView(new Image("/students/brain.png")));
			Text newBrainPowerInfoCount = new Text(""+this.brainPower);
			newBrainPowerInfoCount.setFont(new Font(64));
			newBrainPowerInfo.getChildren().add(newBrainPowerInfoCount);

		this.brainPowerBox.getChildren().add(newBrainPowerInfo);
	}

	/**
	 * Determines if there should be a new wave warning message to be displayed based on @param secondsElapsed.
	 *
	 * @param int Number of seconds that has elapsed; used in order to sync whenever the game updates to the next wave.
	 * @return Nothing.
	 */
	public void updateText(int secondsElapsed){
		String newText = null;
		switch (secondsElapsed){
			case 1:
				newText = "Place your students! Wave 1 will start soon.";
				break;
			case 10:
				newText = "";
				break;
			case 15:
				newText = "Wave 1 has started!";
				break;
			case 20:
				newText = "";
				break;
			case 60:
				newText = "Wave 2 has started!";
				break;
			case 65:
				newText = "";
				break;
			case 180:
				newText = "The final wave has started!";
				break;
			case 185:
				newText = "";
				break;
		}
		if (newText != null){
			this.changeDialogueText(newText);
		}
	}

	/**
	 * Changes the text of the dialogue box based on @param newText.
	 *
	 * @param String The new text to be applied to the dialogue box.
	 * @return Nothing.
	 */
	public void changeDialogueText(String newText){
		this.dialogueBox.getChildren().remove(0, 1);

		Text dialogueBoxText = new Text(newText);
			dialogueBoxText.setStyle("-fx-fill: crimson; -fx-stroke: black; -fx-stroke-width: 0.4; -fx-font-weight: bold;");
			dialogueBoxText.setFont(new Font(28));
			dialogueBoxText.setTextAlignment(TextAlignment.CENTER);

		this.dialogueBox.getChildren().add(dialogueBoxText);
	}

	/**
	 * Spawns a small amount of teachers.
	 * Has a 20% chance of spawning a teacher.
	 *
	 * @param Nothing.
	 * @return Nothing.
	 */
	public void spawnWave1Teacher(){
		this.timeSinceLastSpawn++;
		if (this.timeSinceLastSpawn >= 10){
			if ((int)(Math.random()*5) == 0){
				int randomRowSpawnNum = chooseSpawnRow();
				while (randomRowSpawnNum == this.previouslySpawnedRow){
					randomRowSpawnNum = chooseSpawnRow();
				}
				int teacherTypeRoll = (int)(Math.random()*8);
				if (teacherTypeRoll < 2){
					spawnTeacher("ProfessorTeacher", randomRowSpawnNum);
				} else {
					spawnTeacher("StandardTeacher", randomRowSpawnNum);
				}
				this.previouslySpawnedRow = randomRowSpawnNum;
				this.timeSinceLastSpawn = 0;
			}
		}
	}

	/**
	 * Spawns a medium amount of teachers.
	 * Has a 50% chance of spawning a teacher.
	 *
	 * @param Nothing.
	 * @return Nothing.
	 */
	public void spawnWave2Teacher(){
		this.timeSinceLastSpawn++;
		if (this.timeSinceLastSpawn >= 5){
			if ((int)(Math.random()*2) == 0){
				int randomRowSpawnNum = chooseSpawnRow();
				while (randomRowSpawnNum == this.previouslySpawnedRow){
					randomRowSpawnNum = chooseSpawnRow();
				}
				int teacherTypeRoll = (int)(Math.random()*10);
				if (teacherTypeRoll < 1){
					spawnTeacher("AthleticPETeacher", randomRowSpawnNum);
				} else if (teacherTypeRoll < 2) {
					spawnTeacher("FatPETeacher", randomRowSpawnNum);
				} else if (teacherTypeRoll < 5) {
					spawnTeacher("ProfessorTeacher", randomRowSpawnNum);
				} else {
					spawnTeacher("StandardTeacher", randomRowSpawnNum);
				}
				this.previouslySpawnedRow = randomRowSpawnNum;
				this.timeSinceLastSpawn = 0;
			}
		}
	}

	/**
	 * Spawns a large amount of teachers + the principal
	 * Has a 100% chance of spawning a (non-principal) teacher.
	 *
	 * @param Nothing.
	 * @return Nothing.
	 */
	public void spawnWave3Teacher(int elapsedSeconds){
		this.timeSinceLastSpawn++;
		
		int randomRowSpawnNum = chooseSpawnRow();
		while (randomRowSpawnNum == this.previouslySpawnedRow){
			randomRowSpawnNum = chooseSpawnRow();
		}
		
		if (elapsedSeconds == 200){
			spawnTeacher("PrincipalTeacher", randomRowSpawnNum);
		}
		
		if (this.timeSinceLastSpawn >= 3){
			if ((int)(Math.random()*1) == 0){
				randomRowSpawnNum = chooseSpawnRow();
				while (randomRowSpawnNum == this.previouslySpawnedRow){
					randomRowSpawnNum = chooseSpawnRow();
				}
				int teacherTypeRoll = (int)(Math.random()*20);
				if (teacherTypeRoll < 3){
					spawnTeacher("AthleticPETeacher", randomRowSpawnNum);
				} else if (teacherTypeRoll < 6) {
					spawnTeacher("FatPETeacher", randomRowSpawnNum);
				} else if (teacherTypeRoll < 10) {
					spawnTeacher("ProfessorTeacher", randomRowSpawnNum);
				} else {
					spawnTeacher("StandardTeacher", randomRowSpawnNum);
				}
				this.previouslySpawnedRow = randomRowSpawnNum;
				this.timeSinceLastSpawn = 0;
			}
		}
	}

	/**
	 * Chooses a random row to spawn a teacher.
	 *
	 * @param Nothing.
	 * @return int This number is a random int between 0 and 4, inclusive.
	 */
	public int chooseSpawnRow(){
    	return (int)(Math.random()*5);
	}

	/**
	 * Adds a Teacher of the given type into the game.
	 * Displays the Teacher's sprite onto the screen.
	 *
	 * @param String Indicates which type of teacher is to be spawned.
	 * @param int The row that the Teacher is to be spawned into.
	 * @return Nothing.
	 */
	public void spawnTeacher(String type, int row){
		switch (type){
			case "StandardTeacher":
				StandardTeacher newStandardTeacher = new StandardTeacher(this, row);
				this.centerTeachersInEachRow[row].add(newStandardTeacher);
				this.centerRows[row].getChildren().add(newStandardTeacher.getSprite());
				this.instantiatedTeachers.add(newStandardTeacher);
				break;
			case "ProfessorTeacher":
				ProfessorTeacher newProfessorTeacher = new ProfessorTeacher(this, row);
				this.centerTeachersInEachRow[row].add(newProfessorTeacher);
				this.centerRows[row].getChildren().add(newProfessorTeacher.getSprite());
				this.instantiatedTeachers.add(newProfessorTeacher);
				break;
			case "AthleticPETeacher":
				AthleticPETeacher newAthleticPETeacher = new AthleticPETeacher(this, row);
				this.centerTeachersInEachRow[row].add(newAthleticPETeacher);
				this.centerRows[row].getChildren().add(newAthleticPETeacher.getSprite());
				this.instantiatedTeachers.add(newAthleticPETeacher);
				break;
			case "FatPETeacher":
				FatPETeacher newFatPETeacher = new FatPETeacher(this, row);
				this.centerTeachersInEachRow[row].add(newFatPETeacher);
				this.centerRows[row].getChildren().add(newFatPETeacher.getSprite());
				this.instantiatedTeachers.add(newFatPETeacher);
				break;
			case "PrincipalTeacher":
				PrincipalTeacher newPrincipalTeacher = new PrincipalTeacher(this, row);
				this.centerTeachersInEachRow[row].add(newPrincipalTeacher);
				this.centerRows[row].getChildren().add(newPrincipalTeacher.getSprite());
				this.instantiatedTeachers.add(newPrincipalTeacher);
				break;
		}
	}

//private methods ==================================================================================================================================


//main method ==================================================================================================================================
	public static void main(String[] args){

	}
}
