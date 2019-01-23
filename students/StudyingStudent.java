package students;

import scenes.MainGameScreen;

public class StudyingStudent extends Student{
//fields ==================================================================================================================================
    private int secondsSinceLastBrainCollected = 0;
    //private boolean brainSpawned = false;

//constructors ==================================================================================================================================
	public StudyingStudent(){
	    //health, attackRange, attackSpeed, attackPower, cost
	    super(5, 0, 10, 25, "/students/studyingstudent0.png", 50, "StudyingStudent");
	}

    public StudyingStudent(MainGameScreen game){
	    //health, attackRange, attackSpeed, attackPower, cost
	    super(game, 5, 0, 10, 25, "/students/studyingstudent0.png", 50, "StudyingStudent");
	}

//public getters and setters ============================================================================================================================
    public String getDefaultImgSrc(){return "/students/studyingstudentdefault.png";}

//public methods ==================================================================================================================================
	/**
	 * Calculates how many seconds have passed since
	 * StudyingStudent has generated brain power.
     * Updates StudyingStudent's sprite accordingly.
	 *
	 * @param Nothing.
	 * @return Nothing.
	 */
    public void incrementSeconds(){
        if (this.secondsSinceLastBrainCollected < this.getAttackSpeed()){
            this.secondsSinceLastBrainCollected++;
            //System.out.println("seconds since last collected: "+this.secondsSinceLastBrainCollected);
            if (this.secondsSinceLastBrainCollected >= this.getAttackSpeed()){
                this.setImgSrc("/students/studyingstudent1.png");
                this.getTile().setImage("/students/studyingstudent1.png");
            }
        }
    }

    /**
	 * Resets StudyingStudent's seconds since it has last collected brain power.
	 * Update StudyingStudent's sprite accordingly.
	 *
	 * @param Nothing.
	 * @return Nothing.
	 */
    public void collectedBrainPower(){
        this.secondsSinceLastBrainCollected = 0;
        this.setImgSrc("/students/studyingstudent0.png");
        this.getTile().setImage("/students/studyingstudent0.png");
    }


//private methods ==================================================================================================================================


//main method ==================================================================================================================================
	public static void main(String[] args){

	}
}
