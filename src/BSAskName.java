import java.awt.event.*;

import javax.swing.*;

public class BSAskName extends JPanel	{

	private JFrame frame;
	private JTextField nameEntry;
	private JButton enterName;
	private JLabel enterNameText;
	public String name = null;
	private BattleShipClient client;
	
	public BSAskName(final BattleShipClient client)	{
		this.client = client;
		this.setLayout(null);
		
		nameEntry = new JTextField();
		nameEntry.setBounds(25, 60, 350, 30);
		this.add(nameEntry);
		
		enterName = new JButton("Submit");
		enterName.setBounds(280, 110, 90, 30);
		enterName.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            { 
            	name = nameEntry.getText();
                frame.setVisible(false);
                frame.dispose();
                client.sendMessage(Protocol.SNAPE + name);
            }
        });      
		this.add(enterName);
		
		enterNameText = new JLabel("Please enter your name:");
		enterNameText.setBounds(25, 40, 350, 20);
		this.add(enterNameText);
		
		initFrame();
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
}
