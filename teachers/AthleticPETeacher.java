package teachers;

import scenes.MainGameScreen;

public class AthleticPETeacher extends Teacher{
//fields ==================================================================================================================================


//constructors ==================================================================================================================================
	public AthleticPETeacher(){
	  //health, attackRange, attackSpeed, attackPower, imgSrc, movementSpeed
	  super(8, 1, 20, 1, "/teachers/athleticpeteacher.png", 1, -1);
	}

	public AthleticPETeacher(MainGameScreen game, int row){
	  //health, attackRange, attackSpeed, attackPower, imgSrc, movementSpeed
	  super(game, 8, 1, 20, 1, "/teachers/athleticpeteacher.png", 1, row);
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
