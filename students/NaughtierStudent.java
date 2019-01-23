package students;

import java.util.ArrayList;

import scenes.MainGameScreen;
import teachers.Teacher;

public class NaughtierStudent extends Student implements Ranged{
//fields ==================================================================================================================================
	int secondsSinceLastAttack = 0;

//constructors ==================================================================================================================================
	public NaughtierStudent(MainGameScreen game){
	    //health, attackRange, attackSpeed, attackPower, cost
	    super(game, 4, 1000, 3, 3, "/students/naughtierstudent0.png", 250, "NaughtierStudent");
    }

	public NaughtierStudent(){
		//health, attackRange, attackSpeed, attackPower, cost
		super(4, 1000, 3, 3, "/students/naughtierstudent0.png", 250, "NaughtierStudent");
	}

//public getters and setters ============================================================================================================================
	public String getDefaultImgSrc(){return "/students/naughtierstudentdefault.png";}

//public methods ==================================================================================================================================
	/**
 	 * Determines the closest target in front of NaughtierStudent.
 	 * Uses NaughtierStudent's x-coordinate and compares it with the x-coordinates of any Teachers in this row.
 	 *
 	 * @param Nothing.
 	 * @return Teacher The closest teacher in front of NaughtierStudent.
 	 */
	public Teacher determineClosetTarget(){
 		int xCoordOfClosestTeacherInFront = Integer.MAX_VALUE;
 		Teacher closestTeacherInFront = null;
 		ArrayList<Teacher> currentTeacherRow = this.getGame().getCenterTeachersInEachRow()[this.getTile().getRow()];
 		for (int i=0; i<currentTeacherRow.size(); i++){
 			Teacher currentTeacher = currentTeacherRow.get(i);
 			int xCoordOfCurrentTeacher = (int)currentTeacher.getSprite().getX();

 			if (this.getTile().getX() + this.getTile().getWidth()/4 < xCoordOfCurrentTeacher && xCoordOfClosestTeacherInFront > xCoordOfCurrentTeacher && xCoordOfCurrentTeacher < 1000){
 				xCoordOfClosestTeacherInFront = xCoordOfCurrentTeacher;
 				closestTeacherInFront = currentTeacher;
 			}
 		}

 		return closestTeacherInFront;
 	}


	/**
 	 * Checks if NaughtierStudent's attacking cooldown is over based on its attack speed.
 	 * Updates NaughtierStudent's sprite accordingly.
 	 *
 	 * @param Nothing.
 	 * @return boolean True if the attacking cooldown is over and if there is a target in front.
 	 */
	public boolean checkAttack(){
 		this.secondsSinceLastAttack++;
 		if (this.secondsSinceLastAttack == 1){
 			this.getTile().setImage("/students/naughtierstudent0.png");
 		}
 		if (secondsSinceLastAttack >= this.getAttackSpeed() && this.determineClosetTarget() != null){
 			this.secondsSinceLastAttack = 0;
 			return true;
 		}
 		return false;
 	}

	/**
	 * Chooses the closest target and throws a Projectile
	 * if there is a target in front of NaughtierStudent.
	 * Updates NaughtierStudent's sprite to appear as if he is throwing the book.
	 *
	 * @param Nothing.
	 * @return Nothing.
	 */
	public void attack(){
 		Teacher target = this.determineClosetTarget();
 		new Projectile(this.getGame(), (int)this.getTile().getX(), this.getTile().getRow(), this.getAttackPower(), true).spawn();
 		this.getTile().setImage("/students/naughtierstudent1.png");
 	}


//private methods ==================================================================================================================================


//main method ==================================================================================================================================
	public static void main(String[] args){

	}
}
