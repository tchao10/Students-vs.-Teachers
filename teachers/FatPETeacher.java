package teachers;

import scenes.MainGameScreen;

public class FatPETeacher extends Teacher{
//fields ==================================================================================================================================


//constructors ==================================================================================================================================
	public FatPETeacher(){
	  //health, attackRange, attackSpeed, attackPower, imgSrc, movementSpeed
	  super(30, 2, 45, 3, "/teachers/fatpeteacher.png", 4, -1);
  	}

	public FatPETeacher(MainGameScreen game, int row){
	  //health, attackRange, attackSpeed, attackPower, imgSrc, movementSpeed
	  super(game, 30, 2, 45, 3, "/teachers/fatpeteacher.png", 4, row);
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
