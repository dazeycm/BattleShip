import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * 
 * @author Craig Dazey and Nora Husani
 * @course CSE 211
 * @professor Dr. Kiper
 * @date 05/07/15
 * @description GameOver is a class used to handle ending a battle ship game and
 *              show statistics
 */
public class GameOver extends JPanel {
	private static final long serialVersionUID = -7678557956903171235L;
	private JFrame frame;
	private BattleShipClient bsc;

	private JLabel WoL;
	private JLabel name1;
	private JLabel name2;
	private JLabel shots1;
	private JLabel shots2;
	private JLabel hits1;
	private JLabel hits2;
	private JLabel accuracy1;
	private JLabel accuracy2;

	private JLabel playAgain;
	private JButton yes;
	private JButton no;

	String stats;

	/**
	 * Preconditions: stats and bsc cannot be null Postconditions: gameover GUI
	 * is set up Side Effects: calls parseStats, imitPlayAgain, and initFrame
	 * 
	 * @param stats
	 *            a string that represents the currents stats
	 * @param bsc
	 *            a BattleShipClient that represents the player
	 */
	public GameOver(String stats, BattleShipClient bsc) {
		// play sound effect
		MP3 mp3 = new MP3();
		mp3.play("sound/gameover.mp3");
		// when the computation is done, stop playing it
		try {
			mp3.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// play from the beginning
		mp3 = new MP3();
		mp3.play("sound/gameover.mp3");
		this.setLayout(null);
		this.stats = stats;
		this.setBackground(new Color(50, 200, 200));
		this.bsc = bsc;

		parseStats();
		initPlayAgain();
		initFrame();
	}

	/**
	 * Preconditions: none Postconditions: statParts is parsed and added to this
	 * Side Effects: a List<String> object is created
	 */
	public void parseStats() {
		List<String> statParts = Arrays.asList(stats.split(" "));
		initWoLLabel(statParts.get(1));
		initNames(statParts.get(3), statParts.get(8));
		initShots(statParts.get(4), statParts.get(9));
		initHits(statParts.get(6), statParts.get(11));
		initAccuracy(statParts.get(4), statParts.get(6), statParts.get(9),
				statParts.get(11));

		this.add(WoL);
		this.add(name1);
		this.add(name2);
		this.add(shots1);
		this.add(shots2);
		this.add(hits1);
		this.add(hits2);
		this.add(accuracy1);
		this.add(accuracy2);

	}

	/**
	 * Preconditions: wol is not null Postconditions:a new JLabel is formatted
	 * Side Effects: new JLabel object is created
	 * 
	 * @param wol
	 *            a string that represents either win or lose
	 */
	public void initWoLLabel(String wol) {
		WoL = new JLabel(wol, SwingConstants.CENTER);
		WoL.setBounds(0, 10, 400, 45);
		WoL.setFont(new Font("Impact", Font.PLAIN, 45));
		WoL.setForeground(new Color(84, 84, 84));
	}

	/**
	 * Preconditions: playerName1 and playerName2 are not null Postconditions: a
	 * new JLabel is formatted Side Effects: new JLabel object is created
	 * 
	 * @param playerName1
	 *            a string representing player1's name
	 * @param playerName2
	 *            a string representing player2's name
	 */
	public void initNames(String playerName1, String playerName2) {
		name1 = new JLabel(playerName1, SwingConstants.CENTER);
		name1.setBounds(0, 90, 200, 30);
		name1.setFont(new Font("Impact", Font.PLAIN, 35));
		name1.setForeground(new Color(84, 84, 84));

		name2 = new JLabel(playerName2, SwingConstants.CENTER);
		name2.setBounds(200, 90, 200, 30);
		name2.setFont(new Font("Impact", Font.PLAIN, 35));
		name2.setForeground(new Color(84, 84, 84));
	}

	/**
	 * Preconditions: player1shots and player2shots are not null Postconditions:
	 * a new JLabel is formatted Side Effects: new JLabel object is created
	 * 
	 * @param player1shots
	 *            a string representing player1's shots
	 * @param player2shots
	 *            a string representing player2's shots
	 */
	public void initShots(String player1Shots, String player2Shots) {
		shots1 = new JLabel("Shots: " + player1Shots, SwingConstants.CENTER);
		shots1.setBounds(0, 130, 200, 30);
		shots1.setFont(new Font("Impact", Font.PLAIN, 20));
		shots1.setForeground(new Color(84, 84, 84));

		shots2 = new JLabel("Shots: " + player2Shots, SwingConstants.CENTER);
		shots2.setBounds(200, 130, 200, 30);
		shots2.setFont(new Font("Impact", Font.PLAIN, 20));
		shots2.setForeground(new Color(84, 84, 84));
	}

	/**
	 * Preconditions: player1hits and player2hits are not null Postconditions: a
	 * new JLabel is formatted Side Effects: new JLabel object is created
	 * 
	 * @param player1hits
	 *            a string representing player1's hits
	 * @param player2hits
	 *            a string representing player2's hits
	 */
	public void initHits(String player1Hits, String player2Hits) {
		hits1 = new JLabel("Hits: " + player1Hits, SwingConstants.CENTER);
		hits1.setBounds(0, 160, 200, 30);
		hits1.setFont(new Font("Impact", Font.PLAIN, 20));
		hits1.setForeground(new Color(84, 84, 84));

		hits2 = new JLabel("Hits: " + player2Hits, SwingConstants.CENTER);
		hits2.setBounds(200, 160, 200, 30);
		hits2.setFont(new Font("Impact", Font.PLAIN, 20));
		hits2.setForeground(new Color(84, 84, 84));
	}

	/**
	 * Preconditions: parameters are not null Postconditions: formats the
	 * accuracy of the players Side Effects: new JLabels are created
	 * 
	 * @param shots1
	 *            a string representing player1's shots
	 * @param hits1
	 *            a string representing player1's hits
	 * @param shots2
	 *            a string representing player2's shots
	 * @param hits2
	 *            a string representing player2's hits
	 */
	public void initAccuracy(String shots1, String hits1, String shots2,
			String hits2) {
		double shots_1 = Integer.parseInt(shots1);
		double shots_2 = Integer.parseInt(shots2);

		double hits_1 = Integer.parseInt(hits1);
		double hits_2 = Integer.parseInt(hits2);

		double acc1 = hits_1 / shots_1;
		double acc2 = hits_2 / shots_2;

		accuracy1 = new JLabel(String.format("Accuracy: %.2f", acc1),
				SwingConstants.CENTER);
		accuracy1.setBounds(0, 190, 200, 30);
		accuracy1.setFont(new Font("Impact", Font.PLAIN, 20));
		accuracy1.setForeground(new Color(84, 84, 84));

		accuracy2 = new JLabel(String.format("Accuracy: %.2f", acc2),
				SwingConstants.CENTER);
		accuracy2.setBounds(200, 190, 200, 30);
		accuracy2.setFont(new Font("Impact", Font.PLAIN, 20));
		accuracy2.setForeground(new Color(84, 84, 84));
	}

	/**
	 * Preconditions: none Postconditions: plays another game of battleship Side
	 * Effects: creates a GUI for asking to play again
	 */
	public void initPlayAgain() {
		ButtonResponder br = new ButtonResponder();

		playAgain = new JLabel("Play again?", SwingConstants.CENTER);
		playAgain.setBounds(100, 275, 200, 30);
		playAgain.setFont(new Font("Impact", Font.PLAIN, 25));
		playAgain.setForeground(new Color(84, 84, 84));
		this.add(playAgain);

		yes = new JButton("Yes");
		yes.setBounds(100, 330, 70, 45);
		yes.setFont(new Font("Impact", Font.PLAIN, 20));
		yes.setBackground(new Color(221, 221, 221));
		yes.setOpaque(true);
		yes.setBorderPainted(true);
		yes.addActionListener(br);

		no = new JButton("No");
		no.setBounds(230, 330, 70, 45);
		no.setFont(new Font("Impact", Font.PLAIN, 20));
		no.setBackground(new Color(221, 221, 221));
		no.setOpaque(true);
		no.setBorderPainted(true);
		no.addActionListener(br);

		this.add(no);
		this.add(yes);
	}

	/**
	 * Preconditions: none Postconditions: makes the frame not visible Side
	 * Effects: calls setVisible and dispose
	 */
	void kill() {
		frame.setVisible(false);
		frame.dispose();
	}

	/**
	 * Preconditions: none Postconditions: creates a frame for displaying
	 * gameover Side Effects: creates a new JFrame object
	 */
	void initFrame() {
		frame = new JFrame("Game Over!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.setBounds(0, 0, 400, 450);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
	}

	public class ButtonResponder implements ActionListener {
		/**
		 * Preconditions: e is not NULL Postconditions: creates a new button for
		 * the button clicked Side Effects: getSource is called
		 */
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == yes)
				bsc.sendMessage(Protocol.YES);
			else
				bsc.sendMessage(Protocol.NO);
		}
	}
}
