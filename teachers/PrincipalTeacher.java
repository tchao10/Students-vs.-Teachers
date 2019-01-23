package teachers;

import scenes.MainGameScreen;

public class PrincipalTeacher extends Teacher{
//fields ==================================================================================================================================


//constructors ==================================================================================================================================
	public PrincipalTeacher(){
	  //health, attackRange, attackSpeed, attackPower, imgSrc, movementSpeed
	  super(255, 1, 30, 3, "/teachers/principalteacher.png", 3, -1);
	}

	public PrincipalTeacher(MainGameScreen game, int row){
	  //health, attackRange, attackSpeed, attackPower, imgSrc, movementSpeed
	  super(game, 255, 1, 30, 3, "/teachers/principalteacher.png", 3, row);
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
