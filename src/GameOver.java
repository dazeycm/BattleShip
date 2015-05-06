import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GameOver extends JPanel {
	private JFrame frame;
	private JLabel WoL;
	
	
	
	
	void initFrame() {
		frame = new JFrame("Game Over!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.setBounds(0, 0, 525, 700);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
	}
	
	public static void main(String[] args) {
		
	}

}
