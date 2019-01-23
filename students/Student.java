package students;

import other.*;
import scenes.Tile;
import scenes.MainGameScreen;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Student extends Human{
//fields ==================================================================================================================================
	private int cost;
	private String classNameString;

	private Tile tile = null;
	private int id;
	public static int counter = 1;
//constructors ==================================================================================================================================
    public Student(){
        super(1, 0, 0, 0, null);
        this.cost = 0;
        this.classNameString = "Student";
		this.id = counter;
		counter++;
    }

	public Student(MainGameScreen game){
        super(game, 1, 0, 0, 0, null);
        this.cost = 0;
        this.classNameString = "Student";
		this.id = counter;
		counter++;
    }

    public Student(int health, int attackRange, int attackSpeed, int attackPower, String imgSrc, int cost, String classNameString){
        super(health, attackRange, attackSpeed, attackPower, imgSrc);
        this.cost = cost;
		this.classNameString = classNameString;
		this.id = counter;
		counter++;
    }

	public Student(MainGameScreen game, int health, int attackRange, int attackSpeed, int attackPower, String imgSrc, int cost, String classNameString){
        super(game, health, attackRange, attackSpeed, attackPower, imgSrc);
        this.cost = cost;
		this.classNameString = classNameString;
		this.id = counter;
		counter++;
    }

//public getters and setters ============================================================================================================================
  	public int getCost(){return this.cost;}
  	public String getClassNameString(){return this.classNameString;}
  	public Tile getTile(){return this.tile;}
  	public int getId(){return this.id;}
	public String getDefaultImgSrc(){return "ERROR";}

    public void setCost(int newCost){this.cost = newCost;}
	public void setTile(Tile newTile){this.tile = newTile;}

//public methods ==================================================================================================================================
	/**
	 * Removes itself off the screen and from any fields
	 * that stores an instance of this or one of this object's subclasses.
 	 *
 	 * @param Nothing.
 	 * @return Nothing.
 	 */
	@Override
   	public void die(){
		this.getGame().getInstantiatedStudents().remove(this);
		this.tile.setOccupyingStudent(null);
		this.tile.setStudentIsOccupying(false);
		this.tile.setFill(Color.GRAY);
	}

//private methods ==================================================================================================================================


//main method ==================================================================================================================================
	public static void main(String[] args){

	}
}
