import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/**
 * 
 * @author Craig Dazey and Nora Husani
 * @course CSE 211
 * @professor Dr. Kiper
 * @date 05/07/15
 * @description Board is a class consisting of a HashMap that represents the
 *              ships on a board
 */
public class Board {
	HashMap<String, Ship> ships;

	/**
	 * Preconditions: none Postconditions: creates the board Side Effects:
	 * creates a new HashMap object
	 */
	public Board() {
		ships = new HashMap<String, Ship>();
	}

	/**
	 * Preconditions: type and intShipsLocs are not NULL Postconditions: adds
	 * ships to the board Side Effects: creates a new ship object and puts it in
	 * the HashMap
	 * 
	 * @param type
	 *            a string that represents the ship type
	 * @param intShipLocs
	 *            a List that represents the coordinates of the ship
	 */
	public void addShip(String type, List<Integer> intShipLocs) {
		Ship ship = new Ship(intShipLocs.size(), intShipLocs);
		ships.put(type, ship);
	}

	/**
	 * Preconditions: none Postconditions: returns how many ships are remaining
	 * on the board Side Effects: modifies numShipsLeft
	 * 
	 * @return an int that represents the number of ships remaining
	 */
	public int numShipsReamining() {
		int numShipsLeft = 0;
		for (Entry<String, Ship> entry : ships.entrySet()) {
			if (!entry.getValue().isSunk()) {
				numShipsLeft++;
			}
		}
		return numShipsLeft;
	}
}
