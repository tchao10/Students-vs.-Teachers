package students;

import scenes.MainGameScreen;

public class FootballStudent extends Student{
//fields ==================================================================================================================================
    int initialHealth;

//constructors ==================================================================================================================================
    public FootballStudent(){
        //health, attackRange, attackSpeed, attackPower, cost
        super(20, 1, 1, 0, "/students/footballstudent0.png", 50, "FootballStudent");
        this.initialHealth = this.getHealth();
    }

    public FootballStudent(MainGameScreen game){
        //health, attackRange, attackSpeed, attackPower, cost
        super(game, 20, 1, 1, 0, "/students/footballstudent0.png", 50, "FootballStudent");
        this.initialHealth = this.getHealth();
    }

//public getters and setters ============================================================================================================================
    public String getDefaultImgSrc(){return "/students/footballstudentdefault.png";}

//public methods ==================================================================================================================================
	/**
	 * Checks the health of FootballStudent and when health drops below half,
     * the damaged football student image is used instead.
	 *
	 * @param Nothing.
	 * @return Nothing.
	 */
    public void checkHealth(){
        if (this.getHealth() < this.initialHealth/2){
            this.setImgSrc("/students/footballstudent1.png");
            this.getTile().setImage("/students/footballstudent1.png");
        }
    }


//private methods ==================================================================================================================================


//main method ==================================================================================================================================
	public static void main(String[] args){

	}
}
