import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
 * @description ShipTest is a JUnit Test Case to test the methods in the class
 *              Ship partitioned to check correct and incorrect states of the
 *              ship
 */
public class ShipTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	// shipConstructorTest verifies that the constructor for a ship
	// correctly instantiates a ship object with the correct health and
	// locations
	public void ShipConstructorTest() {
		List<Integer> intShipLocs = new ArrayList<Integer>();
		intShipLocs.add(33);
		intShipLocs.add(34);
		Ship s = new Ship(2, intShipLocs);
		assertEquals(2, s.getHealth());
		assertEquals(2, intShipLocs.size());
	}

	@Test
	// shipHitTest verifies that the shipHit method correctly decrements
	// the health of a ship
	public void shipHitTest() {
		List<Integer> intShipLocs = new ArrayList<Integer>();
		intShipLocs.add(33);
		intShipLocs.add(34);
		Ship s = new Ship(2, intShipLocs);
		s.shipHit();
		assertEquals(1, s.getHealth());
	}

	@Test
	// shipHitTest verifies that the shipHit method sets health to -1
	// if a ship is hit after it is sunk
	public void shipHitInvalidTest() {
		List<Integer> intShipLocs = new ArrayList<Integer>();
		intShipLocs.add(33);
		intShipLocs.add(34);
		Ship s = new Ship(0, intShipLocs);
		s.shipHit();
		assertEquals(-1, s.getHealth());
	}

	@Test
	// isSunkTrueTest verifies that the isSunk method returns true
	// when a ship has been sunk
	public void isSunkTrueTest() {
		List<Integer> intShipLocs = new ArrayList<Integer>();
		intShipLocs.add(33);
		intShipLocs.add(34);
		Ship s = new Ship(2, intShipLocs);
		s.shipHit();
		s.shipHit();
		assertTrue(s.isSunk());
	}

	@Test
	// isSunkFalseTest verifies that the isSunk method returns false
	// when a ship has not been sunk
	public void isSunkFalseTest() {
		List<Integer> intShipLocs = new ArrayList<Integer>();
		intShipLocs.add(33);
		intShipLocs.add(34);
		Ship s = new Ship(2, intShipLocs);
		s.shipHit();
		assertFalse(s.isSunk());
	}

}
