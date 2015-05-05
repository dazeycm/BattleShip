import static org.junit.Assert.assertFalse;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 * @author Craig Dazey and Nora Husani
 * @course CSE 211
 * @professor Dr. Kiper
 * @date 05/07/15
 * @description BSAskNameTest is a JUnit Test Case to test the methods in the
 *              class BSAskName partitioned to check correct and incorrect
 *              states of the GUI
 */
public class BSAskNameTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	// askNameConstructorTest verifies that the askName constructor
	// correctly creates a GUI for receiving a player's name
	public void askNameConstructorTest() {
		BSAskName ask = new BSAskName();
		assertFalse(ask.getEnterName() == null);
	}

}
