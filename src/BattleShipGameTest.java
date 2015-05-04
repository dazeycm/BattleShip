import static org.junit.Assert.assertEquals;

import java.net.ServerSocket;

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
	ServerThread player1;
	ServerThread player2;
	ServerSocket ss = null;
	BattleShipGame bsg;
	static BattleShipServer bss;
	static BattleShipClient bsc1;
	static BattleShipClient bsc2;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bss = new BattleShipServer();
		bss.getClients();
		// bsc1 = new BattleShipClient();
		// bsc2 = new BattleShipClient();
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
	public void askForNamesTest() {
		bsc1 = new BattleShipClient();
		// bsc2 = new BattleShipClient();
		bsg.askForNames();
	}

}
