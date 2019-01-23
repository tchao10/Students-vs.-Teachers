package teachers;

import scenes.MainGameScreen;

public class StandardTeacher extends Teacher{
//fields ==================================================================================================================================


//constructors ==================================================================================================================================
	public StandardTeacher(){
        //health, attackRange, attackSpeed, attackPower, imgSrc, movementSpeed
	    super(7, 1, 30, 1, "/teachers/standardteacher.png", 2, -1);
    }

	public StandardTeacher(MainGameScreen game, int row){
        //health, attackRange, attackSpeed, attackPower, imgSrc, movementSpeed
	    super(game, 7, 1, 30, 1, "/teachers/standardteacher.png", 2, row);
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
