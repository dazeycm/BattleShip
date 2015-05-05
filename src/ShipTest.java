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
 * @description ShipTest is a JUnit Test Case to test the methods in the class
 *              Ship partitioned to check correct and incorrect states of the
 *              ship
 */
public class ShipTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void ShipConstructorTest() {
		List<Integer> intShipLocs = new ArrayList<Integer>();
		intShipLocs.add(33);
		intShipLocs.add(34);
		Ship s = new Ship(2, intShipLocs);
		assertEquals(2, s.getHealth());
	}

}
