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
 *              the class BattleShipGame
 */

public class BattleShipGameTest {

	final int PORT = 1993;
	static ServerThread player1;
	static ServerThread player2;
	ServerSocket ss = null;
	static BattleShipGame bsg;
	public Board player1Board;
	public Board player2Board;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bsg = new BattleShipGame(player1, player2);
	}

	@Test
	// BattleShipGameConstructorTest verifies that when the BattleShipGame
	// constructor is called with two valid ServerThreads, that the
	// BattleShipGame
	// is correctly instantiated with the ServerThreads
	public void BattleShipGameConstructorTest() {
		bsg = new BattleShipGame(player1, player2);
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
		bsg = new BattleShipGame(player1, player1);
		assertEquals(bsg.player1, player1);
		assertEquals(bsg.player2, player1);
	}

	@Test
	public void getShipButtonLocsTest() {
		List<Integer> intShipLocs = new ArrayList<Integer>();
		intShipLocs.add(33);
		intShipLocs.add(34);
		player1Board.addShip("PATROL_BOAT", intShipLocs);
		String result = bsg.getShipButtonLocs("player1");
		System.out.println(result);
	}

}
