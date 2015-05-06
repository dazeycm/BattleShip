import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * @author Craig Dazey and Nora Husani
 * @course CSE 211
 * @professor Dr. Kiper
 * @date 05/07/15
 * @description BattleShipServer is a class to handle all of the server side
 *              code of battleships's networking components
 */
public class BattleShipServer {

	static final int PORT = 1993;

	ServerThread player1;
	ServerThread player2;
	ServerSocket ss = null;
	BattleShipGame bsg;
	boolean playAnother = true;

	public BattleShipServer() {
		getClients();
		do{
			bsg = new BattleShipGame(player1, player2, this);
			bsg.askForNames();
			bsg.getShips();
			bsg.playGame();
		} while(playAnother);
		System.exit(0);
	}
	
	/**
	 * Preconditions: none Postconditions: creates the clients and all the
	 * networking jazz Side Effects: creates ServerSocket and ServerThread
	 * objects as well as a new BattleShipGame
	 */
	public void getClients() {
		this.ss = null;

		try {
			ss = new ServerSocket(PORT);
		} catch (IOException e) {
			System.out.println("Couldn't establish new serverSocket");
			e.printStackTrace();
		}

		Socket client = null;
		try {
			client = ss.accept();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.player1 = new ServerThread(client);

		Socket client2 = null;
		try {
			client2 = ss.accept();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.player2 = new ServerThread(client2);

		player1.start();
		player2.start();
	}

	/**
	 * Preconditions: none Postconditions: creates a BattleShipServer Side
	 * Effects: creates a new BattleShipServer object and instantiates it
	 */
	public static void main(String[] args) {
		BattleShipServer bss = new BattleShipServer();
	}
}

/**
 * 
 * Use a thread to send and receive messages and more fancy networking stuff
 */
class ServerThread extends Thread {

	Socket client;
	String name;
	BufferedReader input;
	PrintWriter output;

	/**
	 * Preconditions: client is not NULL Postconditions: creates a ServerThread
	 * Side Effects: sets this.client to client and calls initIO
	 * 
	 * @param client
	 *            a Socket for the player
	 */
	public ServerThread(Socket client) {
		this.client = client;
		initIO();
	}

	public void run() {
		while (true) {
		}
	}

	/**
	 * Preconditions: none Postconditions: creates input and output streams Side
	 * Effects: creates a new BufferedREader and PrintWriter
	 */
	private void initIO() {
		try {
			input = new BufferedReader(new InputStreamReader(
					(client.getInputStream())));
			output = new PrintWriter(client.getOutputStream());
		} catch (IOException e) {
			System.out
					.println("Error in retrieving input and output streams for threads");
			e.printStackTrace();
		}
	}

	/**
	 * Preconditions: message is not NULL Postconditions: sends a message to the
	 * client Side Effects: calls println and flush
	 * 
	 * @param message
	 *            a string that represents the message according to protocol
	 */
	public void sendMessage(String message) {
		output.println(message);
		output.flush();
	}

	/**
	 * Preconditions: none Postconditions: returns a string that represents the
	 * message Side Effects: calles input.readLine
	 * 
	 * @return a String that represents the message according to protocol
	 */
	public String receiveMessage() {
		try {
			return this.input.readLine();
		} catch (IOException e) {
			System.out.println("Error when client tried to read message");
			e.printStackTrace();
		}

		return null;
	}
}
