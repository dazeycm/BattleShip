import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class BSAskShips extends JPanel {
	JFrame frame;
	GridLayout layout = new GridLayout(11,11);
	private BattleShipClient client;
	
	public BSAskShips()	{
		this.client = client;
		this.setLayout(layout);
	}
	
	void initFrame()	{
		frame = new JFrame("Welcome to BattleShip!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.setBounds(0,0, 400, 200);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
	}

	public static void main(String[] args) {
		
	}

}
