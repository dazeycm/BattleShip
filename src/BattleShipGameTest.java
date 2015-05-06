import static org.junit.Assert.assertEquals;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 * @author Craig Dazey and Nora Husani
 * @course CSE 211
 * @professor Dr. Kiper
 * @date 05/07/15
 * @description BattleShipGameTest is a JUnit Test Case to test the methods in
 *              the class BattleShipGame partitioned to check correct and
 *              incorrect states of the game
 */

public class BattleShipGameTest {

	final int PORT = 1993;
	static ServerThread player1;
	static ServerThread player2;
	ServerSocket ss = null;
	static BattleShipGame bsg;
	public Board player1Board;
	public Board player2Board;
	public static BattleShipServer bss;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bsg = new BattleShipGame(player1, player2, bss);
	}

	@Test
	// BattleShipGameConstructorTest verifies that when the BattleShipGame
	// constructor is called with two valid ServerThreads, that the
	// BattleShipGame
	// is correctly instantiated with the ServerThreads
	public void BattleShipGameConstructorTest() {
		assertEquals(bsg.player1, player1);
		assertEquals(bsg.player2, player2);
	}

	@Test
	// BattleShipGameConstructorInvalidTest verifies that when the
	// BattleShipGame
	// constructor is called with two of the same ServerThreads, that the
	// BattleShipGame
	// is correctly instantiated with the ServerThreads
	public void BattleShipGameConstructorInvalidTest() {
		assertEquals(bsg.player1, player1);
		assertEquals(bsg.player2, player1);
	}

	@Test
	// getShipButtonLocsEmptyTest verifies that when getShipButtonLocs()
	// is called on an empty playerboard that the method returns an empty string
	public void getShipButtonLocsEmptyTest() {
		String result = bsg.getShipButtonLocs("player1");
		assertEquals(result, "");

	}

	@Test
	// getShipButtonLocsEmptyTest verifies that when getShipButtonLocs()
	// is called that the method adds the correct locations to the board
	public void getShipButtonLocsTest() {
		player1Board = new Board();
		player2Board = new Board();
		List<Integer> intShipLocs = new ArrayList<Integer>();
		intShipLocs.add(33);
		intShipLocs.add(34);
		player1Board.addShip("PATROL_BOAT", intShipLocs);
		bsg.getShipButtonLocs("player1");
		assertEquals(2, intShipLocs.size());

	}

}
