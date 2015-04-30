import java.awt.Color;

import javafx.util.Pair;

import javax.swing.JButton;


public class BSButton extends JButton{
	int butNum;
	int x;
	int y;
	boolean clicked;
	
	private static final long serialVersionUID = 1090104771604915982L;

	public BSButton(int butNum, int x, int y)	{
		super();
		this.setBackground(Color.BLUE);
		this.butNum = butNum;
		this.x = x;
		this.y = y;
		this.clicked = false;
	}
	
	public Pair getXY()	{
		return new Pair<Integer, Integer>(this.x, this.y);
	}
	
	public void clicked()	{
		this.clicked = !this.clicked;
	}
	
	public boolean hasBeenClicked()	{
		return this.clicked;
	}
	
	public String toString()	{
		return butNum + " " + x + " " + y;
	}
	

}
