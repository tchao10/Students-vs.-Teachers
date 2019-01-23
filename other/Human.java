package other;

import scenes.MainGameScreen;

public abstract class Human{
//fields ==================================================================================================================================
	private MainGameScreen game;
	private int health;
	private int attackRange;
	private int attackSpeed;
	private int attackPower;
	private String imgSrc;

//constructors ==================================================================================================================================
	public Human(int health, int attackRange, int attackSpeed, int attackPower, String imgSrc){
	    this.health = health;
	    this.attackRange = attackRange;
	    this.attackSpeed = attackSpeed;
	    this.attackPower = attackPower;
		this.imgSrc = imgSrc;
	}

	public Human(MainGameScreen game, int health, int attackRange, int attackSpeed, int attackPower, String imgSrc){
		this.game = game;
		this.health = health;
	    this.attackRange = attackRange;
	    this.attackSpeed = attackSpeed;
	    this.attackPower = attackPower;
		this.imgSrc = imgSrc;
	}

//public getters and setters ============================================================================================================================
  	public MainGameScreen getGame(){return this.game;}
  	public int getHealth(){return this.health;}
  	public int getAttackRange(){return this.attackRange;}
  	public int getAttackSpeed(){return this.attackSpeed;}
  	public int getAttackPower(){return this.attackPower;}
  	public String getImgSrc(){return this.imgSrc;}

	public void setHealth(int newHealth){this.health = newHealth;}
  	public void setAttackRange(int newAttackRange){this.attackRange = newAttackRange;}
  	public void setAttackSpeed(int newAttackSpeed){this.attackSpeed = newAttackSpeed;}
  	public void setAttackPower(int newAttackPower){this.attackPower = newAttackPower;}
  	public void setImgSrc(String newImgSrc){this.imgSrc = newImgSrc;}

//public methods ==================================================================================================================================
  	/**
	 * Removes itself off the screen and from any fields
	 * that stores an instance of this or one of this object's subclasses.
	 *
	 * To be overridden.
	 *
     * @param Nothing.
     * @return Nothing.
     */
    public abstract void die();

	/**
	 * Subtracts the amount of damage specified by @param damage from this Human's health.
	 * Performs this.die() if the health is below 0.
	 *
     * @param int Damage that has been dealt.
     * @return Nothing.
     */
    public void takeDamage(int damage){
		this.health -= damage;
		if (this.health <= 0){
			this.die();
		}
    }

//private methods ==================================================================================================================================


//main method ==================================================================================================================================
	public static void main(String[] args){

	}
}
