import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

/**
 * 
 * @author Craig Dazey and Nora Husani
 * @course CSE 211
 * @professor Dr. Kiper
 * @date 05/07/15
 * @description BattleShipGame is a class to handle the set up and logic of a
 *              game of battleship
 */
public class BattleShipGame {
	
	private BattleShipServer bss;

	public ServerThread player1;
	public ServerThread player2;

	public Board player1Board;
	public Board player2Board;
	
	public int player1Shots;
	public int player1Hits;
	public int player2Shots;
	public int player2Hits;

	public boolean gameOver = false;

	/**
	 * Preconditions: player1 and player2 are not NULL Postconditions:
	 * BattleShipGame object is instantiated Side Effects: this.player1 and
	 * this.player2 are set
	 * 
	 * @param player1
	 *            a ServerThread that is used for the first player
	 * @param player2
	 *            a ServerThread that is used for the second player
	 */
	public BattleShipGame(ServerThread player1, ServerThread player2, BattleShipServer bss) {
		this.player1 = player1;
		this.player2 = player2;

		this.bss = bss;
		
		player1Board = new Board();
		player2Board = new Board();
	}

	/**
	 * Preconditions: none Postconditions: sends protocol for asking for name
	 * and records the name Side Effects: sends ALBUS from the protocol as a
	 * message to ask for name
	 */
	public void askForNames() {
		player1.sendMessage(Protocol.ALBUS);
		player2.sendMessage(Protocol.ALBUS);

		String name = player1.receiveMessage();
		name = name.substring(name.indexOf(" ") + 1);
		player1.name = name;

		name = player2.receiveMessage();
		name = name.substring(name.indexOf(" ") + 1);
		player2.name = name;

		player1.sendMessage(Protocol.POLYJUICE + player2.name);
		player2.sendMessage(Protocol.POLYJUICE + player1.name);
	}

	/**
	 * Preconditions: askForNames has been called Postconditions: sends protocol
	 * for getting the ship placement Side Effects: sends message VOLDY from the
	 * protocol and sets the ships
	 */
	public void getShips() {
		player1.sendMessage(Protocol.VOLDY);
		player2.sendMessage(Protocol.VOLDY);

		String ship;

		while (player1Board.ships.size() < 5 && player2Board.ships.size() < 5) {
			ship = player1.receiveMessage();
			parseShip(ship, "player1");
			ship = player2.receiveMessage();
			parseShip(ship, "player2");
		}
	}

	/**
	 * Preconditions: askForNames and getShips have both been called
	 * Postconditions: beings a loop to play battleship Side Effects: sends and
	 * receives messages according to protocol
	 */
	public void playGame() {
		Random rnJesus = new Random();
		boolean player1GoesFirst = true;
		int max = 10;
		int min = 0;
		int chance = rnJesus.nextInt(max - min + 1) + min;
		if (chance < 5) {
			player1GoesFirst = false;
		}

		player1.sendMessage(Protocol.POTTER + getShipButtonLocs("player1"));
		player2.sendMessage(Protocol.POTTER + getShipButtonLocs("player2"));

		boolean sentMessage = false;

		beforewhile:
		while (!gameOver) {
			if (player1GoesFirst) {
				String loc = player1.receiveMessage();
				int butNum = Integer
						.parseInt(loc.substring(loc.indexOf(" ") + 1));

				for (Entry<String, Ship> entry : player2Board.ships.entrySet()) {
					if (entry.getValue().locs.contains(butNum)) {
						entry.getValue().shipHit();
						if (entry.getValue().isSunk()) {
							if(player2Board.numShipsReamining() == 0)	{
								player1Shots++;
								player1Hits++;
								player1.sendMessage(Protocol.HOOCH 
										+ Protocol.WINNER 
										+ Protocol.SHOTS_P1 
										+ player1.name + " "
										+ player1Shots + " "
										+ Protocol.ACCURACY
										+ player1Hits + " "
										+ Protocol.SHOTS_P2
										+ player2.name + " "
									    + player2Shots + " "
									    + Protocol.ACCURACY
									    + player2Hits);
								player2.sendMessage(Protocol.HOOCH 
										+ Protocol.LOSER 
										+ Protocol.SHOTS_P1 
										+ player1.name + " "
										+ player1Shots + " "
										+ Protocol.ACCURACY
										+ player1Hits + " "
										+ Protocol.SHOTS_P2
										+ player2.name + " "
									    + player2Shots + " "
									    + Protocol.ACCURACY
									    + player2Hits);
								gameOver = true;
								break beforewhile;
							}
							// play sound effect
							MP3 mp3 = new MP3();
							mp3.play("sound/sunk.mp3");
							// when the computation is done, stop playing it
							try {
								mp3.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							// play from the beginning
							mp3 = new MP3();
							mp3.play("sound/sunk.mp3");
							player2.sendMessage(Protocol.AUROR
									+ Protocol.CRUCIO + butNum + " "
									+ Protocol.AVADAKEDAVRA + entry.getKey());
							player1.sendMessage(Protocol.RON + Protocol.CRUCIO
									+ butNum + " " + Protocol.AVADAKEDAVRA
									+ entry.getKey());
							player1Shots++;
							player1Hits++;
							sentMessage = true;
							break;
						} else {
							player2.sendMessage(Protocol.AUROR
									+ Protocol.CRUCIO + butNum);
							player1.sendMessage(Protocol.RON + Protocol.CRUCIO
									+ butNum);
							player1Shots++;
							player1Hits++;
							sentMessage = true;
							break;
						}
					}
				}
				if (!sentMessage) {
					player2.sendMessage(Protocol.AUROR + Protocol.STUPEFY
							+ butNum);
					player1.sendMessage(Protocol.RON + Protocol.STUPEFY
							+ butNum);
					player1Shots++;
				}
				
				loc = player2.receiveMessage();
				butNum = Integer.parseInt(loc.substring(loc.indexOf(" ") + 1));
				sentMessage = false;

				for (Entry<String, Ship> entry : player1Board.ships.entrySet()) {
					if (entry.getValue().locs.contains(butNum)) {
						entry.getValue().shipHit();
						if (entry.getValue().isSunk()) {
							if(player1Board.numShipsReamining() == 0)	{
								player2Shots++;
								player2Hits++;
								player1.sendMessage(Protocol.HOOCH 
										+ Protocol.LOSER 
										+ Protocol.SHOTS_P1 
										+ player1.name + " "
										+ player1Shots + " "
										+ Protocol.ACCURACY
										+ player1Hits + " "
										+ Protocol.SHOTS_P2
										+ player2.name + " "
									    + player2Shots + " "
									    + Protocol.ACCURACY
									    + player2Hits);
								player2.sendMessage(Protocol.HOOCH 
										+ Protocol.WINNER 
										+ Protocol.SHOTS_P1 
										+ player1.name + " "
										+ player1Shots + " "
										+ Protocol.ACCURACY
										+ player1Hits + " "
										+ Protocol.SHOTS_P2
										+ player2.name + " "
									    + player2Shots + " "
									    + Protocol.ACCURACY
									    + player2Hits);
								gameOver = true;
								break beforewhile;
							}
							// play sound effect
							MP3 mp3 = new MP3();
							mp3.play("sound/sunk.mp3");
							// when the computation is done, stop playing it
							try {
								mp3.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							// play from the beginning
							mp3 = new MP3();
							mp3.play("sound/sunk.mp3");
							player1.sendMessage(Protocol.AUROR
									+ Protocol.CRUCIO + butNum + " "
									+ Protocol.AVADAKEDAVRA + entry.getKey());
							player2.sendMessage(Protocol.RON + Protocol.CRUCIO
									+ butNum + " " + Protocol.AVADAKEDAVRA
									+ entry.getKey());
							player2Shots++;
							player2Hits++;
							sentMessage = true;
							break;
						} else {
							player1.sendMessage(Protocol.AUROR
									+ Protocol.CRUCIO + butNum);
							player2.sendMessage(Protocol.RON + Protocol.CRUCIO
									+ butNum);
							player2Shots++;
							player2Hits++;
							sentMessage = true;
							break;
						}
					}
				}
				if (!sentMessage) {
					player1.sendMessage(Protocol.AUROR + Protocol.STUPEFY
							+ butNum);
					player2.sendMessage(Protocol.RON + Protocol.STUPEFY
							+ butNum);
					player2Shots++;
				}
				sentMessage = false;
			} else {
				String loc = player2.receiveMessage();
				int butNum = Integer
						.parseInt(loc.substring(loc.indexOf(" ") + 1));
				sentMessage = false;

				for (Entry<String, Ship> entry : player1Board.ships.entrySet()) {
					if (entry.getValue().locs.contains(butNum)) {
						entry.getValue().shipHit();
						if (entry.getValue().isSunk()) {
							if(player1Board.numShipsReamining() == 0)	{
								player2Shots++;
								player2Hits++;
								player1.sendMessage(Protocol.HOOCH 
										+ Protocol.LOSER 
										+ Protocol.SHOTS_P1 
										+ player1.name + " "
										+ player1Shots + " "
										+ Protocol.ACCURACY
										+ player1Hits + " "
										+ Protocol.SHOTS_P2
										+ player2.name + " "
									    + player2Shots + " "
									    + Protocol.ACCURACY
									    + player2Hits);
								player2.sendMessage(Protocol.HOOCH 
										+ Protocol.WINNER 
										+ Protocol.SHOTS_P1 
										+ player1.name + " "
										+ player1Shots + " "
										+ Protocol.ACCURACY
										+ player1Hits + " "
										+ Protocol.SHOTS_P2
										+ player2.name + " "
									    + player2Shots + " "
									    + Protocol.ACCURACY
									    + player2Hits);
								gameOver = true;
								break beforewhile;
							}
							// play sound effect
							MP3 mp3 = new MP3();
							mp3.play("sound/sunk.mp3");
							// when the computation is done, stop playing it
							try {
								mp3.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							// play from the beginning
							mp3 = new MP3();
							mp3.play("sound/sunk.mp3");
							player1.sendMessage(Protocol.AUROR
									+ Protocol.CRUCIO + butNum + " "
									+ Protocol.AVADAKEDAVRA + entry.getKey());
							player2.sendMessage(Protocol.RON + Protocol.CRUCIO
									+ butNum + " " + Protocol.AVADAKEDAVRA
									+ entry.getKey());
							player2Shots++;
							player2Hits++;
							sentMessage = true;
							break;
						} else {
							player1.sendMessage(Protocol.AUROR
									+ Protocol.CRUCIO + butNum);
							player2.sendMessage(Protocol.RON + Protocol.CRUCIO
									+ butNum);
							player2Shots++;
							player2Hits++;
							sentMessage = true;
							break;
						}
					}
				}
				if (!sentMessage) {
					player1.sendMessage(Protocol.AUROR + Protocol.STUPEFY
							+ butNum);
					player2.sendMessage(Protocol.RON + Protocol.STUPEFY
							+ butNum);
					player2Shots++;
				}

				loc = player1.receiveMessage();
				butNum = Integer.parseInt(loc.substring(loc.indexOf(" ") + 1));
				sentMessage = false;

				for (Entry<String, Ship> entry : player2Board.ships.entrySet()) {
					if (entry.getValue().locs.contains(butNum)) {
						entry.getValue().shipHit();
						if (entry.getValue().isSunk()) {
							if(player2Board.numShipsReamining() == 0)	{
								player1Shots++;
								player1Hits++;
								player1.sendMessage(Protocol.HOOCH 
										+ Protocol.WINNER 
										+ Protocol.SHOTS_P1 
										+ player1.name + " "
										+ player1Shots + " "
										+ Protocol.ACCURACY
										+ player1Hits + " "
										+ Protocol.SHOTS_P2
										+ player2.name + " "
									    + player2Shots + " "
									    + Protocol.ACCURACY
									    + player2Hits);
								player2.sendMessage(Protocol.HOOCH 
										+ Protocol.LOSER 
										+ Protocol.SHOTS_P1 
										+ player1.name + " "
										+ player1Shots + " "
										+ Protocol.ACCURACY
										+ player1Hits + " "
										+ Protocol.SHOTS_P2
										+ player2.name + " "
									    + player2Shots + " "
									    + Protocol.ACCURACY
									    + player2Hits);
								gameOver = true;
								break beforewhile;
							}
							// play sound effect
							MP3 mp3 = new MP3();
							mp3.play("sound/sunk.mp3");
							// when the computation is done, stop playing it
							try {
								mp3.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							// play from the beginning
							mp3 = new MP3();
							mp3.play("sound/sunk.mp3");
							player2.sendMessage(Protocol.AUROR
									+ Protocol.CRUCIO + butNum + " "
									+ Protocol.AVADAKEDAVRA + entry.getKey());
							player1.sendMessage(Protocol.RON + Protocol.CRUCIO
									+ butNum + " " + Protocol.AVADAKEDAVRA
									+ entry.getKey());
							player1Shots++;
							player1Hits++;
							sentMessage = true;
							break;
						} else {
							player2.sendMessage(Protocol.AUROR
									+ Protocol.CRUCIO + butNum);
							player1.sendMessage(Protocol.RON + Protocol.CRUCIO
									+ butNum);
							player1Shots++;
							player1Hits++;
							sentMessage = true;
							break;
						}
					}
				}
				if (!sentMessage) {
					player2.sendMessage(Protocol.AUROR + Protocol.STUPEFY
							+ butNum);
					player1.sendMessage(Protocol.RON + Protocol.STUPEFY
							+ butNum);
					player1Shots++;
				}
				sentMessage = false;
			}
		}
		
		String player1Message;
		String player2Message;
		do{
			player1Message = player1.receiveMessage();
		} while (!player1Message.equals(Protocol.YES) && !player1Message.equals(Protocol.NO));
		
		do{
			player2Message = player2.receiveMessage();
		} while (!player2Message.equals(Protocol.YES) && !player2Message.equals(Protocol.NO));
		
		System.out.println(player1Message);
		System.out.println(player2Message);
		
		if(player1Message.equals(Protocol.YES) && player2Message.equals(Protocol.YES))	{
			System.out.println("playing another");
		} else {
			bss.playAnother = false;
			System.exit(0);
		}
	}

	/**
	 * Preconditions: player is not NULL Postconditions: returns a String
	 * representing the location of the ship Side Effects: creates an
	 * ArrayList<Integer> and modifies it
	 * 
	 * @param player
	 *            a string that represents which player the ship is from
	 * @return a string representing the location of the ship
	 */
	public String getShipButtonLocs(String player) {
		ArrayList<Integer> locs = new ArrayList<Integer>();
		if (player.equals("player1")) {
			for (Entry<String, Ship> entry : player1Board.ships.entrySet()) {
				for (Integer p : entry.getValue().locs)
					locs.add(p);
			}
		} else {
			for (Entry<String, Ship> entry : player2Board.ships.entrySet()) {
				for (Integer p : entry.getValue().locs)
					locs.add(p);
			}
		}

		StringBuilder sb = new StringBuilder();

		for (Integer p : locs) {
			sb.append(p + "&");
		}
		return sb.toString();
	}

	/**
	 * Preconditions: ships and player are not NULL Postconditions: parses the
	 * ship information Side Effects: creates List<String> and List<Integer>
	 * objects
	 * 
	 * @param ships
	 *            represents the ship type in question
	 * @param player
	 *            represents which player the ships belong to
	 */
	public void parseShip(String ships, String player) {
		List<String> parts = Arrays.asList(ships.split(" "));
		String shipName = parts.get(1);
		String locations = parts.get(2);
		List<String> shipLocs = Arrays.asList(locations.split(","));
		List<Integer> intShipLocs = new ArrayList<Integer>();
		for (String s : shipLocs)
			intShipLocs.add(Integer.parseInt(s));

		switch (intShipLocs.size()) {
		case 2:
			if (player.equals("player1")) {
				player1Board.addShip(shipName, intShipLocs);
			} else {
				player2Board.addShip(shipName, intShipLocs);
			}
			break;
		case 3:
			if (player.equals("player1")) {
				player1Board.addShip(shipName, intShipLocs);
			} else {
				player2Board.addShip(shipName, intShipLocs);
			}
			break;
		case 4:
			if (player.equals("player1")) {
				player1Board.addShip(shipName, intShipLocs);
			} else {
				player2Board.addShip(shipName, intShipLocs);
			}
			break;
		case 5:
			if (player.equals("player1")) {
				player1Board.addShip(shipName, intShipLocs);
			} else {
				player2Board.addShip(shipName, intShipLocs);
			}
			break;
		}
	}
}
