import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
	// BSButton button;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// BSButton button = new BSButton(1, 5, 5);
	}

	@Test
	// buttonConstructorTest verifies that the constructor for BSButton
	// correctly instantiates a BSButton based on the input parameters
	public void buttonConstructorTest() {
		BSButton button = new BSButton(1, 5, 5);
		assertEquals(1, button.butNum);
		assertEquals(5, button.x);
		assertEquals(5, button.y);
	}

	@Test
	// getXYTest verifies that the getXY methods returns the correct
	// string representation of the x and y coordinates
	public void getXYTest() {
		BSButton button = new BSButton(1, 5, 5);
		String result = button.getXY();
		assertEquals("(5,5)", result);
	}

	@Test
	// clickedTrueTest verifies that the clicked method correctly switches the
	// boolean to be true when clicked
	public void clickedTrueTest() {
		BSButton button = new BSButton(1, 5, 5);
		button.clicked();
		assertTrue(button.hasBeenClicked());
	}

	@Test
	// clickedFalseTest verifies that the clicked method correctly switches the
	// boolean to be false when unclicked
	public void clickedFalseTest() {
		BSButton button = new BSButton(1, 5, 5);
		button.clicked();
		button.clicked();
		assertFalse(button.hasBeenClicked());
	}

	@Test
	// hasBeenClickedTrueTest verifies that the hasBeenClicked method correctly
	// returns the
	// boolean to be true when clicked
	public void hasBeenClickedTrueTest() {
		BSButton button = new BSButton(1, 5, 5);
		button.clicked();
		assertTrue(button.hasBeenClicked());
	}

	@Test
	// hasBeenClickedFalseTest verifies that the hasBeenClicked method correctly
	// returns the
	// boolean to be false when unclicked
	public void hasBeenClickedFalseTest() {
		BSButton button = new BSButton(1, 5, 5);
		button.clicked();
		button.clicked();
		assertFalse(button.hasBeenClicked());
	}

	@Test
	// makeUnlickableTest verifies that the makeUnlickable correctly sets a
	// clickable
	// object to unclickable
	public void makeUnclickableTest() {
		BSButton button = new BSButton(1, 5, 5);
		button.makeUnclickable();
		assertFalse(button.isClickable());
	}

	@Test
	// makeUnlickableFromUnclickableTest verifies that the makeUnlickable
	// method correctly sets an already unclickable
	// object to unclickable
	public void makeUnclickableFromUnclickableTest() {
		BSButton button = new BSButton(1, 5, 5);
		button.makeUnclickable();
		button.makeUnclickable();
		assertFalse(button.isClickable());
	}

	@Test
	// isClickableTrueTest verifies that the isClickable method
	// returns true when a button is set to clickable
	public void isClickableTrueTest() {
		BSButton button = new BSButton(1, 5, 5);
		assertTrue(button.isClickable());
	}

	@Test
	// isClickableFalseTest verifies that the isClickable method
	// returns false when a button is set to unclickable
	public void isClickableFalseTest() {
		BSButton button = new BSButton(1, 5, 5);
		button.makeUnclickable();
		assertFalse(button.isClickable());
	}

	@Test
	public void toStringTest() {
		BSButton button = new BSButton(1, 5, 5);
		String result = button.toString();
		assertEquals("1 5 5", result);
	}

}
