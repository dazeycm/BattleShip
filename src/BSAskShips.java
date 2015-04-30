import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class BSAskShips extends JPanel {
	JFrame frame;
	private BattleShipClient client;
	
	public BSAskShips(BattleShipClient client)	{
		this.client = client;
		this.setLayout(null);
		
		//Button information
		int id = 0;
		int x = 0;
		int y = 0;
		int width = 55;
		int height = 55;
		
		//Button placement
		int count = 0;
		int gap = 5;
		int dx = 10;
		int dy = 10;
		int starty = 10;
		
		ButtonResponder br = new ButtonResponder();
		
		for(int i = 0; i < 100; i++)	{
			if(i == 0)	{
				count++;
			}
			else if(count < 10)	{
				dy += width + gap;
				count++;
				y++;
			} else	{
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
			id++;
		}
		
		initFrame();
	}
	
	void initFrame()	{
		frame = new JFrame("Please place your ships!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.setBounds(0,0, 625, 800);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
	}

	public static void main(String[] args) {
		
	}
	
	public class ButtonResponder implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			BSButton button = (BSButton) e.getSource();
			System.out.println(button.toString());
			button.setBackground(Color.YELLOW);
			repaint();
		}
	}

}
