package teachers;

import scenes.MainGameScreen;

public class ProfessorTeacher extends Teacher{
//fields ==================================================================================================================================


//constructors ==================================================================================================================================
	public ProfessorTeacher(){
	   //health, attackRange, attackSpeed, attackPower, imgSrc, movementSpeed
	   super(10, 1, 30, 1, "/teachers/professorteacher.png", 2, -1);
	}

	public ProfessorTeacher(MainGameScreen game, int row){
	   //health, attackRange, attackSpeed, attackPower, imgSrc, movementSpeed
	   super(game, 10, 1, 30, 1, "/teachers/professorteacher.png", 2, row);
	}

//public getters and setters ============================================================================================================================


//public methods ==================================================================================================================================
	/**
	 *
	 *
	 *
	 * @param
	 * @return
	 */



//private methods ==================================================================================================================================


//main method ==================================================================================================================================
	public static void main(String[] args){

	}
}
