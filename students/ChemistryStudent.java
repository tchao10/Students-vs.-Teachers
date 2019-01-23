package students;

import scenes.MainGameScreen;
import teachers.Teacher;

import java.util.ArrayList;

public class ChemistryStudent extends Student{
//fields ==================================================================================================================================
	private int frameOfAnimation = -1;
	private int framesSinceLastAnimationUpdate = 0;

	private String[] animationImgSrc = new String[]{"/students/chemistrystudent0.png", "/students/chemistrystudent1.png", "/students/chemistrystudent2.png", "/students/chemistrystudent3.png", "/students/chemistrystudent4.png", "/students/chemistrystudent4.png"};
//constructors ==================================================================================================================================
	public ChemistryStudent(){
	  //health, attackRange, attackSpeed, attackPower, cost
	  super(30, 150, 15, 20, "/students/chemistrystudent0.png", 300, "ChemistryStudent");
  	}

	public ChemistryStudent(MainGameScreen game){
	  //health, attackRange, attackSpeed, attackPower, cost
	  super(game, 30, 150, 15, 20, "/students/chemistrystudent0.png", 300, "ChemistryStudent");
  	}

//public getters and setters ============================================================================================================================
	public String getDefaultImgSrc(){return "/students/chemistrystudentdefault.png";}

//public methods ==================================================================================================================================
	/**
	 * Updates each frame of the explosion of the ChemistryStudent.
	 * Checks for the frame in which the explosion occurs and for when the entire animation is done.
	 *
	 * @param Nothing.
	 * @return int The current frame of the animation.
	 */
	public int updateAnimation(){
		this.framesSinceLastAnimationUpdate++;
		if (this.framesSinceLastAnimationUpdate >= this.getAttackSpeed()){
			this.frameOfAnimation++;
			this.getTile().setImage(this.animationImgSrc[frameOfAnimation]);
			switch (this.frameOfAnimation){
				case 2: //If the animation frame is the explosion
				this.explode();
				break;
				case 5: //If the animation frame is black
				this.die();
				break;
			}
			this.framesSinceLastAnimationUpdate = 0;
		}


		return this.frameOfAnimation;
	}

  	/**
	 * Makes ChemistryStudent explode, calculating which teachers
	 * in a 3x3 area around ChemistryStudent were hit by the explosion.
	 * Deals damage accordingly.
	 *
   	 * @param Nothing.
   	 * @return Nothing.
   	 */
	public void explode(){
		for (int row = this.getTile().getRow()-1; row <= this.getTile().getRow()+1; row++){
			if (row >= 0 && row <= 4){
				ArrayList<Teacher> teachersInThisRow = this.getGame().getCenterTeachersInEachRow()[row];
				for (int i=0; i<teachersInThisRow.size(); i++){
					Teacher currentTeacher = teachersInThisRow.get(i);
					int xCoordOfMiddleOfTeacher = (int)(currentTeacher.getSprite().getX() + currentTeacher.getSprite().getWidth()/2);
					int xCoordOfMiddleOfStudent = (int)(this.getTile().getX() + this.getTile().getWidth()/2);
					if (xCoordOfMiddleOfStudent - this.getAttackRange() < xCoordOfMiddleOfTeacher && xCoordOfMiddleOfStudent + this.getAttackRange() > xCoordOfMiddleOfTeacher){
						currentTeacher.takeDamage(this.getAttackPower());
					}
				}
			}
		}
	}


//private methods ==================================================================================================================================


//main method ==================================================================================================================================
	public static void main(String[] args){

	}
}
