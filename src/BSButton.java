import java.awt.Color;

import javax.swing.JButton;

public class BSButton extends JButton {
	int butNum;
	int x;
	int y;
	boolean clicked;
	boolean clickable;

	private static final long serialVersionUID = 1090104771604915982L;

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

	public String getXY() {
		return "(" + x + "," + y + ")";
	}

	public void clicked() {
		if (clickable)
			this.clicked = !this.clicked;
	}

	public boolean hasBeenClicked() {
		return this.clicked;
	}

	public void makeUnclickable() {
		this.clickable = false;
	}

	public boolean isClickable() {
		return this.clickable;
	}

	public String toString() {
		return butNum + " " + x + " " + y;
	}
}
