package students;


import java.util.ArrayList;


import teachers.Teacher;
import scenes.MainGameScreen;

public class NaughtyStudent extends Student implements Ranged{
//fields ==================================================================================================================================
	int secondsSinceLastAttack = 0;

//constructors ==================================================================================================================================
	public NaughtyStudent(){
		//health, attackRange, attackSpeed, attackPower, cost
	    super(3, 1000, 2, 1, "/students/naughtystudent0.png", 100, "NaughtyStudent");
    }

	public NaughtyStudent(MainGameScreen game){
		//health, attackRange, attackSpeed, attackPower, cost
	    super(game, 3, 1000, 2, 1, "/students/naughtystudent0.png", 100, "NaughtyStudent");
    }

//public getters and setters ============================================================================================================================
	public String getDefaultImgSrc(){return "/students/naughtystudentdefault.png";}

//public methods ==================================================================================================================================
	/**
	 * Chooses the closest target and throws a Projectile
	 * if there is a target in front of NaughtyStudent.
	 * Updates NaughtyStudent's sprite to appear as if he is throwing the airplane.
	 *
	 * @param Nothing.
	 * @return Nothing.
	 */
	public void attack(){
		Teacher target = this.determineClosetTarget();
		new Projectile(this.getGame(), (int)this.getTile().getX(), this.getTile().getRow(), this.getAttackPower(), false).spawn();
		this.getTile().setImage("/students/naughtystudent1.png");
	}

	/**
 	 * Checks if NaughtyStudent's attacking cooldown is over based on its attack speed.
 	 * Updates NaughtyStudent's sprite accordingly.
 	 *
 	 * @param Nothing.
 	 * @return boolean True if the attacking cooldown is over and if there is a target in front.
 	 */
	public boolean checkAttack(){
		this.secondsSinceLastAttack++;
		if (this.secondsSinceLastAttack == 1){
			this.getTile().setImage("/students/naughtystudent0.png");
		}
		if (secondsSinceLastAttack >= this.getAttackSpeed() && this.determineClosetTarget() != null){
			this.secondsSinceLastAttack = 0;
			return true;
		}
		return false;
	}

	/**
 	 * Determines the closest target in front of NaughtyStudent.
 	 * Uses NaughtyStudent's x-coordinate and compares it with the x-coordinates of any Teachers in this row.
 	 *
 	 * @param Nothing.
 	 * @return Teacher The closest teacher in front of NaughtyStudent. 
 	 */
	public Teacher determineClosetTarget(){
		int xCoordOfClosestTeacherInFront = Integer.MAX_VALUE;
		Teacher closestTeacherInFront = null;
		ArrayList<Teacher> currentTeacherRow = this.getGame().getCenterTeachersInEachRow()[this.getTile().getRow()];
		for (int i=0; i<currentTeacherRow.size(); i++){
			Teacher currentTeacher = currentTeacherRow.get(i);
			int xCoordOfCurrentTeacher = (int)currentTeacher.getSprite().getX();

			if (this.getTile().getX() < xCoordOfCurrentTeacher && xCoordOfClosestTeacherInFront > xCoordOfCurrentTeacher && xCoordOfCurrentTeacher < 1000){
				xCoordOfClosestTeacherInFront = xCoordOfCurrentTeacher;
				closestTeacherInFront = currentTeacher;
			}
		}

		return closestTeacherInFront;
	}


//private methods ==================================================================================================================================


//main method ==================================================================================================================================
	public static void main(String[] args){

	}
}
