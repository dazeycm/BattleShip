import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class BSAskShips extends JPanel {
	private static final long serialVersionUID = 1929426850051553458L;
	private JFrame frame;
	private ArrayList<BSButton> clickedButtons;
	JButton carrier = new JButton("Aircraft Carrier");
	JButton battleship = new JButton("BattleShip");
	JButton submarine = new JButton("Submarine");
	JButton cruiser = new JButton("Cruiser");
	JButton patrol = new JButton("Patrol Boat");
	JLabel errorText = new JLabel();
	@SuppressWarnings("unused")
	private BattleShipClient client;
	
	public BSAskShips(BattleShipClient client)	{
		this.client = client;
		this.setLayout(null);
		clickedButtons = new ArrayList<BSButton>();
		
		errorText.setBounds(20, 650, 600, 30);
		this.add(errorText);
		
		initGridButtons();
		intitShipButtons();
		initFrame();
	}
	
	public void initGridButtons()	{
		//Button information
		int id = 0;
		int x = 0;
		int y = 0;
		int width = 45;
		int height = 45;
				
		//Button placement
		int count = 0;
		int gap = 5;
		int dx = 10;
		int dy = 10;
		int starty = 10;
		
		gridButtonResponder br = new gridButtonResponder();
		
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
	}
	
	public void intitShipButtons()	{
		shipButtonResponder br = new shipButtonResponder();
		
		carrier.setFont(new Font("TimesRoman", Font.PLAIN, 12));
		battleship.setFont(new Font("TimesRoman", Font.PLAIN, 12));
		submarine.setFont(new Font("TimesRoman", Font.PLAIN, 12));
		cruiser.setFont(new Font("TimesRoman", Font.PLAIN, 12));
		patrol.setFont(new Font("TimesRoman", Font.PLAIN, 12));
		
		carrier.setBounds(185, 610, 145, 40);
		battleship.setBounds(310, 560, 145, 40);
		submarine.setBounds(60, 560, 145, 40);
		cruiser.setBounds(310, 510, 145, 40);
		patrol.setBounds(60, 510, 145, 40);
		
		carrier.addActionListener(br);
		battleship.addActionListener(br);
		submarine.addActionListener(br);
		cruiser.addActionListener(br);
		patrol.addActionListener(br);
		
		this.add(carrier);
		this.add(battleship);
		this.add(submarine);
		this.add(cruiser);
		this.add(patrol);
	}
	
	void initFrame()	{
		frame = new JFrame("Please place your ships!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.setBounds(0,0, 525, 700);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
	}
	
	public class gridButtonResponder implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			BSButton button = (BSButton) e.getSource();
			if(button.isClickable()){
				if(!button.hasBeenClicked())	{
					button.setBackground(Color.YELLOW);
					button.clicked();
					clickedButtons.add(button);
				}
				else	{
					button.setBackground(Color.BLUE);
					button.clicked();
					clickedButtons.remove(button);
				}
				repaint();
				System.out.println(clickedButtons);
			}
		}
	}
	
	public class shipButtonResponder implements ActionListener	{

		public void actionPerformed(ActionEvent e) {
			if(isValidShip(clickedButtons) && clickedButtons.size() > 1)	{
				if(e.getSource() == carrier)	{
					if(clickedButtons.size() != 5)
						errorText.setText("Expected 5 locations to be selected, but found " + clickedButtons.size());
					else	{
						for(BSButton button : clickedButtons)	{
							button.makeUnclickable();
						}
						client.sendMessage(Protocol.HOGWARTS + Protocol.AIRCRAFT_CARRIER + toPointFormat(clickedButtons));
					}
				}
				else if (e.getSource() == battleship)	{
					if(clickedButtons.size() != 4)
						errorText.setText("Expected 4 locations to be selected, but found " + clickedButtons.size());
					else	{
						for(BSButton button : clickedButtons)	{
							button.makeUnclickable();
						}
						client.sendMessage(Protocol.HOGWARTS + Protocol.BATTLESHIP + toPointFormat(clickedButtons));					}
				}
				else if (e.getSource() == submarine)	{
					if(clickedButtons.size() != 3)
						errorText.setText("Expected 3 locations to be selected, but found " + clickedButtons.size());
					else	{
						for(BSButton button : clickedButtons)	{
							button.makeUnclickable();
						}
						client.sendMessage(Protocol.HOGWARTS + Protocol.SUBMARINE + toPointFormat(clickedButtons));					}
				}
				else if (e.getSource() == cruiser)	{
					if(clickedButtons.size() != 3)
						errorText.setText("Expected 3 locations to be selected, but found " + clickedButtons.size());
					else	{
						for(BSButton button : clickedButtons)	{
							button.makeUnclickable();
						}
						client.sendMessage(Protocol.HOGWARTS + Protocol.CRUISER + toPointFormat(clickedButtons));					}
				}
				else if (e.getSource() == patrol)	{
					if(clickedButtons.size() != 2)
						errorText.setText("Expected 2 locations to be selected, but found " + clickedButtons.size());
					else	{
						for(BSButton button : clickedButtons)	{
							button.makeUnclickable();
						}
						client.sendMessage(Protocol.HOGWARTS + Protocol.PATROL_BOAT + toPointFormat(clickedButtons));					}
				}
				
				
				//send ship message here
				//client.sendMessage(message);
				clickedButtons.clear();
			}
			else	{
				errorText.setText("Invalid ship placement!");
			}
		}
		
		public boolean isValidShip(ArrayList<BSButton> buttons){
			boolean vertical = true;
			boolean horizontal = true;
			boolean allNextTo = true;
			for(int i = 0; i < clickedButtons.size() - 1 && vertical; i++)	{
				if(clickedButtons.get(i).x != clickedButtons.get(i + 1).x)
					vertical = false;
			}
			
			for(int i = 0; i < clickedButtons.size() - 1 && horizontal; i++)	{
				if(clickedButtons.get(i).y != clickedButtons.get(i + 1).y)
					horizontal = false;
			}
			
			if(vertical)	{
				for(int i = 0; i < clickedButtons.size() && allNextTo; i++)	{
					for(int j = 0; j < clickedButtons.size() && allNextTo; j++)	{
						allNextTo = Math.abs(clickedButtons.get(i).y - clickedButtons.get(j).y) <= clickedButtons.size() - 1;
					}
				}
			} else if (horizontal)	{
				for(int i = 0; i < clickedButtons.size() && allNextTo; i++)	{
					for(int j = 0; j < clickedButtons.size() && allNextTo; j++)	{
						allNextTo = Math.abs(clickedButtons.get(i).x - clickedButtons.get(j).x) <= clickedButtons.size() - 1;
					}
				}
			}
			return allNextTo && (vertical || horizontal);
		}
		
		public String toPointFormat(ArrayList<BSButton> buttons)	{
			StringBuilder sb = new StringBuilder();
			for(BSButton b : buttons)	{
				sb.append(b.getXY() + " ");
			}
			System.out.println(sb.toString());
			return sb.toString();
		}
	}
}
