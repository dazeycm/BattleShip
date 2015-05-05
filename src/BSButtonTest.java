import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 * @author Craig Dazey and Nora Husani
 * @course CSE 211
 * @professor Dr. Kiper
 * @date 05/07/15
 * @description BSAskButtonsTest is a JUnit Test Case to test the methods in the
 *              class BSAskButton partitioned to check correct and incorrect
 *              states of the buttons
 */
public class BSButtonTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void buttonConstructorTest() {
		BSButton button = new BSButton(1, 5, 5);
		assertEquals(1, button.butNum);
		assertEquals(5, button.x);
		assertEquals(5, button.y);
	}

}
