import static org.junit.Assert.assertFalse;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 * @author Craig Dazey and Nora Husani
 * @course CSE 211
 * @professor Dr. Kiper
 * @date 05/07/15
 * @description BSAskShipsTest is a JUnit Test Case to test the methods in the
 *              class BSAskShips partitioned to check correct and incorrect
 *              states of the GUI
 */
public class BSAskShipsTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	// askShipConstructorTest verifies that the askShips constructor
	// correctly creates a GUI for receiving a player's ships
	public void askShipConstructorTest() {
		BSAskShips ask = new BSAskShips();
		assertFalse(ask.carrier == null);
		assertFalse(ask.battleship == null);
		assertFalse(ask.submarine == null);
		assertFalse(ask.cruiser == null);
		assertFalse(ask.patrol == null);
	}

}
