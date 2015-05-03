import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class BattleShipGame {

	public ServerThread player1;
	public ServerThread player2;
	
	public Board player1Board;
	public Board player2Board;
	
	public BattleShipGame(ServerThread player1, ServerThread player2) {
		this.player1 = player1;
		this.player2 = player2;
		
		player1Board = new Board();
		player2Board = new Board();
	}

	public void askForNames() {
		player1.sendMessage(Protocol.ALBUS);
		player2.sendMessage(Protocol.ALBUS);
		
		String name = player1.receiveMessage();
		name = name.substring(name.indexOf(" ") + 1);
		player1.name = name;
		
		name = player2.receiveMessage();
		name = name.substring(name.indexOf(" ") + 1);
		player2.name = name;
	}
	
	public void getShips()	{
		player1.sendMessage(Protocol.VOLDY);
		player2.sendMessage(Protocol.VOLDY);
		
		String ship;
		
		while(player1Board.ships.size() < 5 && player2Board.ships.size() < 5){
			ship = player1.receiveMessage();
			parseShip(ship, "player1");
			ship = player2.receiveMessage();
			parseShip(ship, "player2");
		}
	}
	
	public void playGame()	{
		Random rnJesus = new Random();
		boolean player1GoesFirst = true;
		int max = 10;
		int min = 0;
		int chance = rnJesus.nextInt(max - min + 1) + min;
		if(chance < 5)	{
			player1GoesFirst = false;
		}
		
		player1.sendMessage(Protocol.POTTER);
		player2.sendMessage(Protocol.POTTER);
	}
	
	public void parseShip(String ships, String player)	{
		List<String> locs = Arrays.asList(ships.split(" "));
		String shipName = locs.get(1);
		locs = locs.subList(2, locs.size());
		ArrayList<Point> shipLocs = new ArrayList<Point>();
		
		for(String point : locs)	{
			List<String> parts = Arrays.asList(point.split(","));
			Point p =  new Point(Integer.parseInt(parts.get(0).substring(1)), Integer.parseInt(parts.get(1).substring(0, 1)));
			shipLocs.add(p);
		}
				
		switch (locs.size())	{
		case 2:
			if(player.equals("player1"))	{
				player1Board.addShip(shipName, shipLocs);
			} 
			else	{
				player2Board.addShip(shipName, shipLocs);
			}
			System.out.println("made " + shipName);
			break;
		case 3:
			if(player.equals("player1"))	{
				player1Board.addShip(shipName, shipLocs);
			} 
			else	{
				player2Board.addShip(shipName, shipLocs);
			}
			System.out.println("made " + shipName);
			break;
		case 4:
			if(player.equals("player1"))	{
				player1Board.addShip(shipName, shipLocs);
			} 
			else	{
				player2Board.addShip(shipName, shipLocs);
			}
			System.out.println("made " + shipName);
			break;
		case 5:
			if(player.equals("player1"))	{
				player1Board.addShip(shipName, shipLocs);
			} 
			else	{
				player2Board.addShip(shipName, shipLocs);
			}
			System.out.println("made " + shipName);
			break;	
		}
	}

	public static void main(String[] args) {
		
	}
}
