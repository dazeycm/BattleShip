import java.util.List;

/**
 * 
 * @author Craig Dazey and Nora Husani
 * @course CSE 211
 * @professor Dr. Kiper
 * @date 05/07/15
 * @description Ship is a class to represent a ship object in battleship
 *
 */
public class Ship {
	private int health;
	public List<Integer> locs;

	/**
	 * Preconditions: health and shipLocs are not NULL Postconditions: creates a
	 * ship for battleship Side Effects: sets this.health to health and
	 * this.locs to shipLocs
	 * 
	 * @param health
	 *            an int that represents the status of the ship
	 * @param shipLocs
	 *            a List that represents the coordinates of the ship
	 */
	public Ship(int health, List<Integer> shipLocs) {
		this.health = health;
		this.locs = shipLocs;
	}

	// decreases a ship's health by 1 when hit
	public void shipHit() {
		this.health -= 1;
	}

	// returns true if the ship is sunk and false otherwise
	public boolean isSunk() {
		return health <= 0;
	}
}
