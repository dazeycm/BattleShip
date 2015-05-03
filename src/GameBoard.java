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
	
	public GameBoard (BattleShipClient client, String locations)	{
		this.client = client;
		this.setLayout(null);
		this.setBackground(new Color(84, 84, 84));
		
		myBoard = new ArrayList<BSButton>();
		myShots = new ArrayList<BSButton>();
		
		initMyGridButtons();
		initMyShotButtons();
		
		ArrayList<Point> locs = makePairs(locations);
		
		for(BSButton button : myBoard)	{
			if(locs.contains(new Point(button.x, button.y)))
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
	
	public ArrayList<Point> makePairs(String locations)	{
		ArrayList<Point> ret = new ArrayList<Point>();
		List<String> parts = Arrays.asList(locations.split("&"));
		for(String s : parts)	{
			List<String> nums = Arrays.asList(s.split(","));
			Point p = new Point(Integer.parseInt(nums.get(0)), Integer.parseInt(nums.get(1)));
			ret.add(p);
		}
		return ret;
	}
	
	public class gridButtonResponder implements ActionListener {

		public void actionPerformed(ActionEvent e) {
		}
	}
}
