import static org.junit.Assert.assertEquals;

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
 * @description BoardTest is a JUnit Test Case to test the methods in the class
 *              Board partitioned to check correct and incorrect states of the
 *              board
 */

public class BoardTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	// boardConstructorTest verifies
	public void boardConstructorTest() {
		Board b = new Board();
		assertEquals(0, b.ships.size());
	}

	@Test
	// addPatrolBoatTest verifies that a patrol boat and its locations
	// are correctly added to a player board when the method is called
	public void addPatrolBoatTest() {
		Board player1Board = new Board();
		List<Integer> intShipLocs = new ArrayList<Integer>();
		intShipLocs.add(33);
		intShipLocs.add(34);
		player1Board.addShip("PATROL_BOAT", intShipLocs);
		assertEquals(true, player1Board.ships.containsKey("PATROL_BOAT"));
		int loc1 = player1Board.ships.get("PATROL_BOAT").locs.get(0);
		assertEquals(33, loc1);
		int loc2 = player1Board.ships.get("PATROL_BOAT").locs.get(1);
		assertEquals(34, loc2);

	}

	@Test
	// addCruiserTest verifies that a cruiser and its locations
	// are correctly added to a player board when the method is called
	public void addCruiserTest() {
		Board player1Board = new Board();
		List<Integer> intShipLocs = new ArrayList<Integer>();
		intShipLocs.add(33);
		intShipLocs.add(34);
		intShipLocs.add(35);
		player1Board.addShip("CRUISER", intShipLocs);
		assertEquals(true, player1Board.ships.containsKey("CRUISER"));
		int loc1 = player1Board.ships.get("CRUISER").locs.get(0);
		assertEquals(33, loc1);
		int loc2 = player1Board.ships.get("CRUISER").locs.get(1);
		assertEquals(34, loc2);
		int loc3 = player1Board.ships.get("CRUISER").locs.get(2);
		assertEquals(35, loc3);
	}

	@Test
	// addSubmarineTest verifies that a submarine and its locations
	// are correctly added to a player board when the method is called
	public void addSubmarineTest() {
		Board player1Board = new Board();
		List<Integer> intShipLocs = new ArrayList<Integer>();
		intShipLocs.add(33);
		intShipLocs.add(34);
		intShipLocs.add(35);
		player1Board.addShip("SUBMARINE", intShipLocs);
		assertEquals(true, player1Board.ships.containsKey("SUBMARINE"));
		int loc1 = player1Board.ships.get("SUBMARINE").locs.get(0);
		assertEquals(33, loc1);
		int loc2 = player1Board.ships.get("SUBMARINE").locs.get(1);
		assertEquals(34, loc2);
		int loc3 = player1Board.ships.get("SUBMARINE").locs.get(2);
		assertEquals(35, loc3);
	}

	@Test
	// addBattleShipTest verifies that a battleship and its locations
	// are correctly added to a player board when the method is called
	public void addBattleShipTest() {
		Board player1Board = new Board();
		List<Integer> intShipLocs = new ArrayList<Integer>();
		intShipLocs.add(33);
		intShipLocs.add(34);
		intShipLocs.add(35);
		intShipLocs.add(36);
		player1Board.addShip("BATTLESHIP", intShipLocs);
		assertEquals(true, player1Board.ships.containsKey("BATTLESHIP"));
		int loc1 = player1Board.ships.get("BATTLESHIP").locs.get(0);
		assertEquals(33, loc1);
		int loc2 = player1Board.ships.get("BATTLESHIP").locs.get(1);
		assertEquals(34, loc2);
		int loc3 = player1Board.ships.get("BATTLESHIP").locs.get(2);
		assertEquals(35, loc3);
		int loc4 = player1Board.ships.get("BATTLESHIP").locs.get(3);
		assertEquals(36, loc4);
	}

	@Test
	// addAircraftCarrierTest verifies that an aircraft carrier and its
	// locations
	// are correctly added to a player board when the method is called
	public void addAircraftCarrierTest() {
		Board player1Board = new Board();
		List<Integer> intShipLocs = new ArrayList<Integer>();
		intShipLocs.add(33);
		intShipLocs.add(34);
		intShipLocs.add(35);
		intShipLocs.add(36);
		intShipLocs.add(37);
		player1Board.addShip("AIRCRAFT_CARRIER", intShipLocs);
		assertEquals(true, player1Board.ships.containsKey("AIRCRAFT_CARRIER"));
		int loc1 = player1Board.ships.get("AIRCRAFT_CARRIER").locs.get(0);
		assertEquals(33, loc1);
		int loc2 = player1Board.ships.get("AIRCRAFT_CARRIER").locs.get(1);
		assertEquals(34, loc2);
		int loc3 = player1Board.ships.get("AIRCRAFT_CARRIER").locs.get(2);
		assertEquals(35, loc3);
		int loc4 = player1Board.ships.get("AIRCRAFT_CARRIER").locs.get(3);
		assertEquals(36, loc4);
		int loc5 = player1Board.ships.get("AIRCRAFT_CARRIER").locs.get(4);
		assertEquals(37, loc5);
	}

	@Test
	// numShipsRemainingTest verifies that the numShipsRemaining method
	// returns the correct number of ships that are left on the board
	public void numShipsRemainingTest() {
		Board player1Board = new Board();
		List<Integer> intShipLocs = new ArrayList<Integer>();
		intShipLocs.add(33);
		intShipLocs.add(34);
		player1Board.addShip("PATROL_BOAT", intShipLocs);
		assertEquals(1, player1Board.numShipsReamining());
	}

}
