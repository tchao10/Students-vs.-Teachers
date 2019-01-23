package scenes;

import other.*;
import students.*;
import teachers.*;

import javafx.event.EventHandler;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle{
//fields ==================================================================================================================================
	private MainGameScreen game;
	private int row;
	private int col;
  	private double width;
	private double height;

	private Student occupyingStudent = null;
	private boolean studentIsOccupying = false;
	private boolean expelHammerIsSelected = false;

//constructors ==================================================================================================================================
	public Tile(MainGameScreen game, int row, int col){
		super(100.0, 100.0);
		this.game = game;
		this.width = 100.0;
		this.height = 100.0;
		this.row = row;
		this.col = col;
	}

	public Tile(MainGameScreen game, int row, int col, Color color){
		super(100.0, 100.0, color);
		this.game = game;
		this.width = 100.0;
		this.height = 100.0;
		this.row = row;
	  	this.col = col;
	}

	public Tile(MainGameScreen game, int xCoord, int row, int col){
		super(xCoord, 0.0, 100.0, 100.0);
		this.game = game;
		this.width = 100.0;
		this.height = 100.0;
		this.row = row;
	  	this.col = col;
	}

	public Tile(MainGameScreen game, int xCoord, int row, int col, Color color){
		super(xCoord, 0.0, 100.0, 100.0);
		this.setFill(color);
		this.game = game;
		this.width = 100.0;
		this.height = 100.0;
		this.row = row;
	  	this.col = col;
	}

	public Tile(MainGameScreen game, int row, int col, Color color, double width, double height){
		super(width, height, color);
		this.game = game;
		this.row = row;
		this.col = col;
		this.width = width;
		this.height = height;
	}
//public getters and setters ============================================================================================================================
	public MainGameScreen getGame(){return this.game;}
	public int getRow(){return this.row;}
	public int getCol(){return this.col;}
	public Student getOccupyingStudent(){return this.occupyingStudent;}
	public boolean getStudentIsOccupying(){return this.studentIsOccupying;}
	//public ArrayList<Human> getAllHumansOnTile(){return this.allHumansOnTile;}
	public boolean getExpelHammerIsSelected(){return this.expelHammerIsSelected;}

	public void setRow(int newRow){this.row = newRow;}
	public void setCol(int newCol){this.col = newCol;}
	public void setOccupyingStudent(Student newOccupyingStudent){this.occupyingStudent = newOccupyingStudent;}
	public void setStudentIsOccupying(boolean newStudentIsOccupying){this.studentIsOccupying = newStudentIsOccupying;}
	public void setExpelHammerIsSelected(boolean newExpelHammerIsSelected){this.expelHammerIsSelected = newExpelHammerIsSelected;}

//public methods ==================================================================================================================================
	/**
	 * Sets the background image of this tile.
	 *
     * @param String The url source of the image.
     * @return Nothing.
     */
	public void setImage(String url){
		this.setFill(new ImagePattern(new Image(url)));
	}

	/**
	 * Sets up a left tile by applying a MouseEvent onto it.
	 * This MouseEvent changes the color of the selected Student.
	 *
     * @param Nothing.
     * @return Nothing.
     */
	public void setUpLeftTile(){
		Tile thisTile = this;
		this.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (thisTile.getStroke().equals(Color.BLACK)){
					thisTile.setStroke(Color.RED);
				} else {
					thisTile.setStroke(Color.BLACK);
				}
			}
		});
	}

	/**
	 * Sets up a center tile.
	 * Applies an onMouseEntered MouseEvent which changes the Tile's color to light gray or collects brain power from a StudyingStudent.
	 * Applies an onMouseExited MouseEvent which changes the Tile's color back to gray.
	 * Applies an onMousePressed MouseEvent which deletes the Student occupying the Tile as long as the expel hammer has been selected.
	 *
     * @param Nothing.
     * @return Nothing.
     */
	public void setUpCenterTile(){
		this.setOpacity(0.90);
		this.setStrokeWidth(2.0);
		this.setStroke(Color.BLACK);

		Tile thisTile = this;
		this.setOnMouseEntered(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (!thisTile.studentIsOccupying){
					thisTile.setFill(Color.LIGHTGRAY);
				} else {
					if (thisTile.getOccupyingStudent().getImgSrc().equals("/students/studyingstudent1.png")){
						StudyingStudent studyingStudent = (StudyingStudent)thisTile.getOccupyingStudent();
						thisTile.getGame().setBrainPower(thisTile.getGame().getBrainPower() + studyingStudent.getAttackPower());
						studyingStudent.collectedBrainPower();
					}
				}
			}
		});

		this.setOnMouseExited(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (!thisTile.studentIsOccupying){
					thisTile.setFill(Color.GRAY);
				}
			}
		});

		this.setOnMousePressed(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (thisTile.studentIsOccupying && thisTile.expelHammerIsSelected){
					thisTile.getGame().getInstantiatedStudents().remove(thisTile.getOccupyingStudent());
					thisTile.setOccupyingStudent(null);
					thisTile.setStudentIsOccupying(false);
					thisTile.setFill(Color.LIGHTGRAY);
				}
			}
		});
	}

//private methods ==================================================================================================================================


//main method ==================================================================================================================================
	public static void main(String[] args){

	}
}
