import java.awt.Color;

import javax.swing.JButton;

/**
 * 
 * @author Craig Dazey and Nora Husani
 * @course CSE 211
 * @professor Dr. Kiper
 * @date 05/07/15
 * @description BSButton is a GUI interface for handeling the buttons in
 *              battleship
 *
 */
public class BSButton extends JButton {
	int butNum;
	int x;
	int y;
	boolean clicked;
	boolean clickable;

	private static final long serialVersionUID = 1090104771604915982L;

	/**
	 * 
	 * @param butNum
	 *            represents the button number of the ship
	 * @param x
	 *            represents the x coordinate
	 * @param y
	 *            represents the y coordinate
	 */
	public BSButton(int butNum, int x, int y) {
		super();
		this.setBackground(new Color(50, 200, 200));
		this.setOpaque(true);
		this.setBorderPainted(false);
		this.butNum = butNum;
		this.x = x;
		this.y = y;
		this.clicked = false;
		this.clickable = true;
	}

	// for formatting
	public String getXY() {
		return "(" + x + "," + y + ")";
	}

	// sets button to either clicked or not clicked
	public void clicked() {
		if (clickable)
			this.clicked = !this.clicked;
	}

	// returns true if button has been clicked, false otherwise
	public boolean hasBeenClicked() {
		return this.clicked;
	}

	// makes button unclickable
	public void makeUnclickable() {
		this.clickable = false;
	}

	// makes button clickable
	public boolean isClickable() {
		return this.clickable;
	}

	// for formatting
	public String toString() {
		return butNum + " " + x + " " + y;
	}
}
