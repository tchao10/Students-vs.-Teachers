/**
 * This interface is used in all of the Students which have a ranged attack, so NaughtyStudent and NaughtierStudent in this game.
 * This interface guarantees three methods: attack(), checkAttack(), and determineClosetTarget()
*/

package students;

import teachers.Teacher;

public interface Ranged {
	/**
	* Chooses the closest target and throws a Projectile
	* if there is a target in front of the implementing Object.
	* Updates the implementing Object's sprite to appear as if it has thrown a Projectile.
	*
	* @param Nothing.
	* @return Nothing.
	*/
	public void attack();

	/**
	* Checks if the implementing Object's attacking cooldown is over based on its attack speed.
	* Updates the implementing Object's sprite accordingly.
	*
	* @param Nothing.
	* @return boolean True if the attacking cooldown is over and if there is a target in front.
	*/
	public boolean checkAttack();

	/**
 	 * Determines the closest target in front of the implementing Object.
 	 * Uses the implementing Object's x-coordinate and compares it with the x-coordinates of any Teachers in this row.
 	 *
 	 * @param Nothing.
 	 * @return Teacher The closest teacher in front of the implementing Object.
 	 */
	public Teacher determineClosetTarget();
}
