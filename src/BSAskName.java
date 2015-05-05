import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 * @author Craig Dazey and Nora Husani
 * @course CSE 211
 * @professor Dr. Kiper
 * @date 05/07/15
 * @description BSAskName is a GUI interface that prompts each player to enter
 *              their name in order to begin a game of battle ship
 *
 */
public class BSAskName extends JPanel {
	private static final long serialVersionUID = 2575364361360064192L;
	private JFrame frame;
	private JTextField nameEntry;
	private JButton enterName;
	private JLabel enterNameText;
	@SuppressWarnings("unused")
	private BattleShipClient client;

	/**
	 * 
	 * Preconditions: client is not NULL Postconditions: new BSAskName GUI is
	 * created Side Effects: this.client is set to client and GUI is set up
	 * 
	 * @param client
	 *            a BattleShipClient object that represents one of the players
	 *            in the game
	 */
	public BSAskName(final BattleShipClient client) {
		this.client = client;
		this.setLayout(null);
		this.setBackground(new Color(50, 200, 200));
		nameEntry = new JTextField();
		nameEntry.setBounds(25, 60, 350, 30);
		this.add(nameEntry);

		setEnterName(new JButton("Submit"));
		getEnterName().setFont(new Font("Impact", Font.PLAIN, 17));
		getEnterName().setBackground(new Color(221, 221, 221));
		getEnterName().setOpaque(true);
		getEnterName().setBorderPainted(false);
		getEnterName().setBounds(280, 110, 90, 30);
		getEnterName().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				client.name = nameEntry.getText();
				client.sendMessage(Protocol.SNAPE + client.name);
				frame.setVisible(false);
				frame.dispose();
			}
		});
		this.add(getEnterName());

		enterNameText = new JLabel("Please enter your name:");
		enterNameText.setFont(new Font("Impact", Font.PLAIN, 17));
		enterNameText.setBounds(25, 40, 350, 20);
		this.add(enterNameText);

		initFrame();
	}

	/**
	 * Preconditions: None Postconditions: Creates welcome to battleship frame
	 * Side Effects: Creates a new JFrame object
	 */
	void initFrame() {
		frame = new JFrame("Welcome to BattleShip!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.setBounds(0, 0, 400, 200);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
	}

	/**
	 * BSAskName is an alternate constructor used only for testing purposes to
	 * avoid messy client/server setup
	 */
	public BSAskName() {
		this.setLayout(null);
		this.setBackground(new Color(50, 200, 200));
		nameEntry = new JTextField();
		nameEntry.setBounds(25, 60, 350, 30);
		this.add(nameEntry);

		setEnterName(new JButton("Submit"));
		getEnterName().setFont(new Font("Impact", Font.PLAIN, 17));
		getEnterName().setBackground(new Color(221, 221, 221));
		getEnterName().setOpaque(true);
		getEnterName().setBorderPainted(false);
		getEnterName().setBounds(280, 110, 90, 30);
		this.add(getEnterName());

		enterNameText = new JLabel("Please enter your name:");
		enterNameText.setFont(new Font("Impact", Font.PLAIN, 17));
		enterNameText.setBounds(25, 40, 350, 20);
		this.add(enterNameText);

		initFrame();
	}

	/**
	 * getter for enterName created solely for testing purposes
	 * 
	 * @return a JButton for the name GUI
	 */
	public JButton getEnterName() {
		return enterName;
	}

	/**
	 * setter for enterName created solely for testing purposes
	 * 
	 * @param enterName
	 *            a JButton for the name GUI
	 */
	public void setEnterName(JButton enterName) {
		this.enterName = enterName;
	}
}
