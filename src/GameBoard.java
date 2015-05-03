import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;


public class GameBoard extends JPanel{
	private static final long serialVersionUID = 1002898930501111631L;
	private JFrame frame;
	private ArrayList<BSButton> myBoard;
	private ArrayList<BSButton> myShots;
	private BattleShipClient client;
	private JLabel errorText;
	
	public GameBoard (BattleShipClient client, String locations)	{
		this.client = client;
		this.setLayout(null);
		this.setBackground(new Color(84, 84, 84));
		
		errorText = new JLabel();
		errorText.setBounds(10, 600, 400, 30);
		this.add(errorText);
		
		myBoard = new ArrayList<BSButton>();
		myShots = new ArrayList<BSButton>();
		
		initMyGridButtons();
		initMyShotButtons();
		
		List<String> locs = Arrays.asList(locations.split("&"));
		ArrayList<Integer> intLocs = new ArrayList<Integer>();
		for(String s : locs)
			intLocs.add(Integer.parseInt(s));
		
		for(BSButton button : myBoard)	{
			if(intLocs.contains(button.butNum))
				button.setBackground(new Color(221, 221, 221));
		}
		
		initFrame();
	}
	
	public void initMyGridButtons() {
		// Button information
		int id = 0;
		int x = 0;
		int y = 0;
		int width = 45;
		int height = 45;

		// Button placement
		int count = 0;
		int gap = 5;
		int dx = 10;
		int dy = 10;
		int starty = 10;

		for (int i = 0; i < 100; i++) {
			if (i == 0) {
				count++;
			} else if (count < 10) {
				dy += width + gap;
				count++;
				y++;
			} else {
				count = 1;
				dx += width + gap;
				x++;
				dy = starty;
				y = 0;
			}

			BSButton button = new BSButton(id, x, y);
			button.setBounds(dx, dy, width, height);
			this.add(button);
			myBoard.add(button);
			id++;
		}
	}
	
	public void initMyShotButtons()	{
		// Button information
		int id = 0;
		int x = 0;
		int y = 0;
		int width = 45;
		int height = 45;

		// Button placement
		int count = 0;
		int gap = 5;
		int dx = 590;
		int dy = 10;
		int starty = 10;

		gridButtonResponder br = new gridButtonResponder();

		for (int i = 0; i < 100; i++) {
			if (i == 0) {
				count++;
			} else if (count < 10) {
				dy += width + gap;
				count++;
				y++;
			} else {
				count = 1;
				dx += width + gap;
				x++;
				dy = starty;
				y = 0;
			}
			
			BSButton button = new BSButton(id, x, y);
			button.setBounds(dx, dy, width, height);
			button.addActionListener(br);
			this.add(button);
			myShots.add(button);
			id++;
		}
	}
	
	void initFrame() {
		frame = new JFrame("Best of luck!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.setBounds(0, 0, 1100, 700);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
	}
	
	public void hitShip(int butNum)	{
		for(BSButton button : myBoard){
			if(button.butNum == butNum)	{
				button.setBackground(Color.RED);
				button.setOpaque(true);
				button.setBorderPainted(false);
				repaint();
			}
		}
	}
	
	public void missedShip(int butNum)	{
		for(BSButton button : myBoard){
			if(button.butNum == butNum)	{
				button.setBackground(Color.YELLOW);
				button.setOpaque(true);
				button.setBorderPainted(false);
				repaint();
			}
		}
	}
	
	public void iHitShip(int butNum)	{
		for(BSButton button : myShots){
			if(button.butNum == butNum)	{
				button.setBackground(Color.RED);
				button.setOpaque(true);
				button.setBorderPainted(false);
				repaint();
			}
		}
	}
	
	public void iMissedShip(int butNum)	{
		for(BSButton button : myShots){
			if(button.butNum == butNum)	{
				button.setBackground(Color.YELLOW);
				button.setOpaque(true);
				button.setBorderPainted(false);
				repaint();
			}
		}
	}
	
	public void setErrorText(String message)	{
		this.errorText.setText(message);
	}
	
	public class gridButtonResponder implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			BSButton button = (BSButton) e.getSource();
			if (button.isClickable()) {
				if (!button.hasBeenClicked()) {
					button.setBackground(new Color(221, 221, 221));
					button.setOpaque(true);
					button.setBorderPainted(false);
					button.clicked();
					button.makeUnclickable();
					repaint();
					client.sendMessage(Protocol.CURSE + button.butNum);
				}
			}
		}
	}
}
