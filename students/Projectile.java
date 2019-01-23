package students;

import teachers.*;
import scenes.MainGameScreen;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Projectile extends Rectangle {
//fields ==================================================================================================================================
	private MainGameScreen game;
	private int initialXCoord;
	private int row;
	private int damage;
	private boolean isBook;

	private int speed;
	private int currentXCoord;
	private String imgSrc;
	private int framesSinceLastMovement;
	//private int id;
	//public static int idCounter = 1;
//constructors ==================================================================================================================================
	public Projectile(MainGameScreen game, int initialXCoord, int row, int damage, boolean isBook){
		super(initialXCoord+25.0, 25.0, 50, 25);
		this.game = game;
		this.initialXCoord = initialXCoord;
		this.currentXCoord = initialXCoord;
		this.row = row;
		this.damage = damage;
		this.isBook = isBook;
		if (isBook){
			this.speed = 1;
			this.imgSrc = "/students/book.png";
			this.setFill(new ImagePattern(new Image(this.imgSrc)));
		} else {
			this.speed = 1;
			this.imgSrc = "/students/airplane.png";
			this.setFill(new ImagePattern(new Image(this.imgSrc)));
		}
	}

//public getters and setters ============================================================================================================================
	public int getCurrentXCoord(){return this.currentXCoord;}
	public int getRow(){return this.row;}
	public int getDamage(){return this.damage;}
	public int getSpeed(){return this.speed;}
	//public int getCurrentId(){return this.id;}

//public methods ==================================================================================================================================
	/**
	 * Adds this object into the game's list of existing Projectiles.
	 * Applies the sprite into the game screen.
	 *
	 * @param Nothing.
	 * @return Nothing.
	 */
	public void spawn(){
		this.game.getCenterRows()[this.getRow()].getChildren().add(this);
		this.game.getInstantiatedProjectiles().add(this);
	}

	/**
	 * Moves the Projectile five pixels, with its
	 * frequency of doing so based on its speed.
	 * Checks and deletes this Projectile if it flies off of the screen.
	 *
	 * @param Nothing.
	 * @return Nothing.
	 */
	public void move(){
		this.framesSinceLastMovement++;
		if (this.framesSinceLastMovement >= this.speed){
			this.setX(this.getX() + 5);
			this.currentXCoord += 5;
			this.framesSinceLastMovement = 0;
		}
		if (this.currentXCoord > 1000){
			this.despawn();
		}
	}

	/**
 	 * Checks if this Projectile is currently "touching" a Teacher by comparing
 	 * the Projectile's x-coordinate with every Teacher in the projectile's row.
 	 *
 	 * @param Nothing.
 	 * @return Teacher The Teacher that is "touched" by this Projectile.
 	 */
	public Teacher checkHit(){
		int xCoordOfTip = (int)((this.currentXCoord) + this.getWidth());
		ArrayList<Teacher> teachersInThisRow = this.game.getCenterTeachersInEachRow()[this.getRow()];
		for (int i=0; i<teachersInThisRow.size(); i++){
			Rectangle currentTeacherSprite = teachersInThisRow.get(i).getSprite();
			if (xCoordOfTip > currentTeacherSprite.getX() && xCoordOfTip < currentTeacherSprite.getX() + currentTeacherSprite.getWidth()){
				return teachersInThisRow.get(i);
			}
		}
		return null;
	}

	/**
	 * Applies damage to @param teacher.
	 * Despawns itself after dealing damage.
	 *
	 * @param Teacher The teacher that is taking the damage.
	 * @return Nothing.
	 */
	public void damageTarget(Teacher teacher){
		teacher.takeDamage(this.damage);
		this.despawn();
	}

	/**
	 * Removes itself from any fields that store an instance of Projectile.
	 * Removes itself off of the screen.
	 *
	 * @param Nothing.
	 * @return Nothing.
	 */
	public void despawn(){
		this.game.getCenterRows()[this.getRow()].getChildren().remove(this);
		this.game.getInstantiatedProjectiles().remove(this);
	}

//private methods ==================================================================================================================================


//main method ==================================================================================================================================
	public static void main(String[] args){

	}
}
