import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author Craig Dazey and Nora Husani
 * @course CSE 211
 * @professor Dr. Kiper
 * @date 05/07/15
 * @description GameBoard is a GUI interface for creating the GameBoard for
 *              battleship
 */
public class GameBoard extends JPanel {
	private static final long serialVersionUID = 1002898930501111631L;
	private JFrame frame;
	private ArrayList<BSButton> myBoard;
	private ArrayList<BSButton> myShots;
	private BattleShipClient client;
	private JLabel errorText;

	/**
	 * Preconditions: client and locations are not NULL Postconditions: creates
	 * a gameBoard object for the battleship game Side Effects: sets
	 * this.client, calls setLayout, setBackground
	 * 
	 * @param client
	 *            a BattleShipClient object that represents the player
	 * @param locations
	 *            a String that represents the locations
	 */
	public GameBoard(BattleShipClient client, String locations) {
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
		for (String s : locs)
			intLocs.add(Integer.parseInt(s));

		for (BSButton button : myBoard) {
			if (intLocs.contains(button.butNum))
				button.setBackground(new Color(221, 221, 221));
		}

		initFrame();
	}

	/**
	 * Stores information about the buttons on the battleship grid
	 * Preconditions: none Postconditions: modifies the grid buttons Side
	 * Effects: creates a new BSButton object
	 */
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

	/**
	 * Preconditions: none Postconditions: modifies the buttons shot at Side
	 * Effects: creates a new gridButtonResponder object
	 */
	public void initMyShotButtons() {
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

	/**
	 * Preconditions: none Postconditions: creates a game frame for the player
	 * Side Effects: creates a new JFrame object and modifies it
	 */
	void initFrame() {
		frame = new JFrame("Best of luck! " + client.name);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.setBounds(0, 0, 1100, 700);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
	}

	/**
	 * Preconditions: called when a ship has been hit by the enemy, butNum is
	 * not NULL Postconditions: changes the color of the button to match being
	 * hit Side Effects: calls setBackground, setOpaque, etc.
	 * 
	 * @param butNum
	 *            an int that represents the button on the game grid
	 */
	public void hitShip(int butNum) {
		for (BSButton button : myBoard) {
			if (button.butNum == butNum) {
				button.setBackground(Color.RED);
				button.setOpaque(true);
				button.setBorderPainted(false);
				repaint();
			}
		}
	}

	/**
	 * Preconditions: called when a ship has been missed by the enemy, butNum is
	 * not NULL Postconditions: changes the color of the button to match being
	 * missed Side Effects: calls setBackground, setOpaque, etc.
	 * 
	 * @param butNum
	 *            an int that represents the button on the game grid
	 */
	public void missedShip(int butNum) {
		for (BSButton button : myBoard) {
			if (button.butNum == butNum) {
				button.setBackground(Color.YELLOW);
				button.setOpaque(true);
				button.setBorderPainted(false);
				repaint();
			}
		}
	}

	/**
	 * Preconditions: called when a player hits a ship, butNum is not NULL
	 * Postconditions: changes the color of the button to match being hit Side
	 * Effects: calls setBackground, setOpaque, etc.
	 * 
	 * @param butNum
	 *            an int that represents the button on the game grid
	 */
	public void iHitShip(int butNum) {
		for (BSButton button : myShots) {
			if (button.butNum == butNum) {
				button.setBackground(Color.RED);
				button.setOpaque(true);
				button.setBorderPainted(false);
				repaint();
			}
		}
	}

	/**
	 * Preconditions: called when a player misses a ship, butNum is not NULL
	 * Postconditions: changes the color of the button to match being missed
	 * Side Effects: calls setBackground, setOpaque, etc.
	 * 
	 * @param butNum
	 *            an int that represents the button on the game grid
	 */
	public void iMissedShip(int butNum) {
		for (BSButton button : myShots) {
			if (button.butNum == butNum) {
				button.setBackground(Color.YELLOW);
				button.setOpaque(true);
				button.setBorderPainted(false);
				repaint();
			}
		}
	}

	// for formatting errors
	public void setErrorText(String message) {
		this.errorText.setText(message);
	}

	/**
	 * 
	 * Implement ActionListener to allow buttons on grid to do things
	 *
	 */
	public class gridButtonResponder implements ActionListener {

		/**
		 * Preconditions: e is not NULL Postconditions: button color is changed
		 * according to hit/miss Side Effects: calls setBackground, setOpaque,
		 * etc.
		 */
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
