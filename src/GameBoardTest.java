import static org.junit.Assert.assertFalse;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 * @author Craig Dazey and Nora Husani
 * @course CSE 211
 * @professor Dr. Kiper
 * @date 05/07/15
 * @description GameBoardTest is a JUnit Test Case to test the methods in the
 *              class GameBoard partitioned to check correct and incorrect
 *              states of the game board
 */
public class GameBoardTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	// gameBoardConstructorTest verifies that the constructor for gameBoard
	// correctly instantiates a gameBoard object
	public void gameBoardConstructorTest() {
		new GameBoard(
				"36&46&56&63&64&65&66&41&51&61&71&81&12&13&38&48&58&");
	}

	@Test
	// hitShipTest verifies that the hitShip method correctly modifies the GUI
	public void hitShipTest() {
		GameBoard board = new GameBoard(
				"36&46&56&63&64&65&66&41&51&61&71&81&12&13&38&48&58&");
		board.hitShip(1);
		assertFalse(board == null);
	}

	@Test
	// missedShipTest verifies that the missedShip method correctly modifies the
	// GUI
	public void missedShipTest() {
		GameBoard board = new GameBoard(
				"36&46&56&63&64&65&66&41&51&61&71&81&12&13&38&48&58&");
		board.missedShip(1);
		assertFalse(board == null);
	}

	@Test
	// iHitShipTest verifies that the iHitShip method correctly modifies the GUI
	public void iHitShipTest() {
		GameBoard board = new GameBoard(
				"36&46&56&63&64&65&66&41&51&61&71&81&12&13&38&48&58&");
		board.iHitShip(1);
		assertFalse(board == null);
	}

	@Test
	// iMissedShipTest verifies that the iMissedShip method correctly modifies
	// the GUI
	public void iMissedShipTest() {
		GameBoard board = new GameBoard(
				"36&46&56&63&64&65&66&41&51&61&71&81&12&13&38&48&58&");
		board.iMissedShip(1);
		assertFalse(board == null);
	}

}
