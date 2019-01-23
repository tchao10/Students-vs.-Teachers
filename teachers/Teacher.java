package teachers;

import other.*;
import students.Student;
import scenes.Tile;
import scenes.MainGameScreen;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public abstract class Teacher extends Human{
//fields ==================================================================================================================================
	private int movementSpeed;
	private int row;

	private int id;
	public static int counter = 1;
	private int framesSinceLastMovement = 0;
	private int framesSinceLastAttack = 0;
	private Rectangle sprite = new Rectangle(950.0, 0.0, 100.0, 100.0);
//constructors ==================================================================================================================================
	public Teacher(){
        //health, attackRange, attackSpeed, attackPower, imgSrc
        super(1, 0, 1, 0, null);
        this.movementSpeed = 0;
		this.row = -1;
		this.sprite.setFill(new ImagePattern(new Image(this.getImgSrc())));
	}

	public Teacher(MainGameScreen game, int row){
        //health, attackRange, attackSpeed, attackPower, imgSrc
        super(game, 1, 0, 1, 0, null);
        this.movementSpeed = 0;
		this.row = row;
		this.sprite.setFill(new ImagePattern(new Image(this.getImgSrc())));
	}

	public Teacher(int health, int attackRange, int attackSpeed, int attackPower, String imgSrc, int movementSpeed, int row){
        super(health, attackRange, attackSpeed, attackPower, imgSrc);
        this.movementSpeed = movementSpeed;
        this.row = row;
		this.sprite.setFill(new ImagePattern(new Image(this.getImgSrc())));
	}

	public Teacher(MainGameScreen game, int health, int attackRange, int attackSpeed, int attackPower, String imgSrc, int movementSpeed, int row){
        super(game, health, attackRange, attackSpeed, attackPower, imgSrc);
        this.movementSpeed = movementSpeed;
		this.row = row;
		this.sprite.setFill(new ImagePattern(new Image(this.getImgSrc())));
	}

//public getters and setters ============================================================================================================================
  	public int getMovementSpeed(){return this.movementSpeed;}
  	public int getRow(){return this.row;}
	public int getId(){return this.id;}
	public Rectangle getSprite(){return this.sprite;}

  	public void setMovementSpeed(int newMovementSpeed){this.movementSpeed = newMovementSpeed;}

//public methods ==================================================================================================================================
	/**
	 * Makes the teacher walk towards the left one pixel based on its movementSpeed.
	 *
     * @param Nothing.
     * @return Nothing
     */
    public void walk(){
		this.framesSinceLastMovement++;
		if (this.framesSinceLastMovement >= this.movementSpeed){
			this.sprite.setX(this.sprite.getX() - 1);
			this.framesSinceLastMovement = 0;
		}
    }

	/**
	 * Performs an attack onto @param studentAttacked
	 * based on this object's attackDamage.
	 *
     * @param Student The student that is being attacked.
     * @return Nothing.
     */
    public void attack(Student studentAttacked){
		studentAttacked.takeDamage(this.getAttackPower());
	}

	/**
	 * Increments how many frames this object has last attacked.
	 * Returns a boolean based on whether or not this object can attack again.
	 *
	 * @param Nothing.
	 * @return boolean True if the number of frames has been reset to 0 (and so this ohject can attack again).
	 */
	public boolean incrementAttackCooldown(){
		this.framesSinceLastAttack++;
		if (this.framesSinceLastAttack >= this.getAttackSpeed()){
			this.framesSinceLastAttack = 0;
			return true;
		}
		return false;
	}

	/**
	 * Calculates the closest student that this object can attack.
	 * Uses the x-coordinates of every Student in this object's row and compares it to this object's sprite's x-coordinate.
	 *
	 * @param Nothing.
	 * @return Student The closest Student to this object.
	 */
	public Student getClosestStudentInRange(){
		ArrayList<Student> allStudents = this.getGame().getInstantiatedStudents();
		for (int i=0; i<allStudents.size(); i++){
			Student currentStudent = allStudents.get(i);
			if (currentStudent.getTile().getRow() == this.row){
				if (this.getSprite().getX() - this.getAttackRange() > currentStudent.getTile().getX() &&
			 		this.getSprite().getX() - this.getAttackRange() < currentStudent.getTile().getX() + currentStudent.getTile().getWidth()){
						return currentStudent;
				}
			}
		}
		return null;
	}

	/**
	* Removes itself off the screen and from any fields
	* that stores an instance of this or one of this object's subclasses.
 	 *
 	 * @param
 	 * @return
 	 */
	public void die(){
		this.getGame().getInstantiatedTeachers().remove(this);
		this.getGame().getCenterRows()[this.row].getChildren().remove(this.getSprite());
		this.getGame().getCenterTeachersInEachRow()[this.row].remove(this);
	}
//private methods ==================================================================================================================================


//main method ==================================================================================================================================
	public static void main(String[] args){

	}
}
