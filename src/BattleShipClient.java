import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author Craig Dazey and Nora Husani
 * @course CSE 211
 * @professor Dr. Kiper
 * @date 05/07/15
 * @description BattleShipClient is a class to handle all of the client side
 *              code of battleships's networking components
 */
public class BattleShipClient {
	final int PORT = 1993;

	private Socket socket;
	public BufferedReader input;
	public PrintWriter output;
	public Scanner kb;
	public String name;
	public String opponentName;

	/**
	 * Preconditions: none Postconditions: creates a BattleShipClient Side
	 * Effects: Sets the address and host name, throws errors when necessary
	 */
	public BattleShipClient() {
		InetAddress address;
		String hostName = null;

		try {
			address = InetAddress.getLocalHost();
			hostName = address.getHostName();

			try {
				this.socket = new Socket(hostName, PORT);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (UnknownHostException e) {
			System.out.println("Error getting localhost");
			e.printStackTrace();
		}

		try {
			input = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			output = new PrintWriter(socket.getOutputStream());
			kb = new Scanner(System.in);
		} catch (IOException e) {
			System.out
					.println("Error in retrieving input and output streams for threads");
			e.printStackTrace();
		}
	}

	/**
	 * Preconditions: input is not NULL Postconditions: returns a string
	 * representation of the next line
	 * 
	 * @return a String representation of the next line
	 */
	public String readLine() {
		try {
			return this.input.readLine();
		} catch (IOException e) {
			System.out.println("Error when client tried to read message");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Preconditions: message is not NULL Postconditions: send message to server
	 * Side Effects: calls println and flush
	 * 
	 * @param message
	 *            a String that represents the message based on the grammar
	 */
	public void sendMessage(String message) {
		output.println(message);
		output.flush();
	}

	/**
	 * Preconditions: none Postconditions: calls methods based on the message
	 * type according to the grammar Side Effects: creates new BSAskName,
	 * BSAskShips, and GameBoard objects
	 * 
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		BattleShipClient bsc = new BattleShipClient();
		BSAskName askName = null;
		GameBoard gb = null;
		BSAskShips getShips = null;
		while (true) {
			String message = bsc.readLine();
			if (message.contains(Protocol.ALBUS)) {
				askName = new BSAskName(bsc);
			} else if (message.contains(Protocol.VOLDY)) {
				getShips = new BSAskShips(bsc);
			} else if (message.contains(Protocol.POTTER)) {
				getShips.kill();
				List<String> parts = Arrays.asList(message.split(" "));
				String locations = parts.get(1);
				gb = new GameBoard(bsc, locations);
			} else if (message.contains(Protocol.AUROR)) {
				int location;
				List<String> parts = Arrays.asList(message.split(" "));
				if (message.contains(Protocol.AVADAKEDAVRA)) {
					location = Integer.parseInt(parts.get(2));
					gb.hitShip(location);
					gb.log("Your opponent sunk your "
							+ Protocol.normalizeShipName(parts.get(4) + " "));
				} else if (message.contains(Protocol.CRUCIO)) {
					location = Integer.parseInt(parts.get(2));
					gb.hitShip(location);
				} else if (message.contains(Protocol.STUPEFY)) {
					location = Integer.parseInt(parts.get(2));
					gb.missedShip(location);
				}
			} else if (message.contains(Protocol.RON)) {
				int location;
				List<String> parts = Arrays.asList(message.split(" "));
				if (message.contains(Protocol.AVADAKEDAVRA)) {
					location = Integer.parseInt(parts.get(2));
					gb.iHitShip(location);
					gb.log("You sunk your opponent's "
							+ Protocol.normalizeShipName(parts.get(4) + " "));
				} else if (message.contains(Protocol.CRUCIO)) {
					location = Integer.parseInt(parts.get(2));
					gb.iHitShip(location);
				} else if (message.contains(Protocol.STUPEFY)) {
					location = Integer.parseInt(parts.get(2));
					gb.iMissedShip(location);
				}
			} else if (message.contains(Protocol.POLYJUICE)) {
				List<String> parts = Arrays.asList(message.split(" "));
				bsc.opponentName = parts.get(1);
			} else if (message.contains(Protocol.HOOCH)) {
				System.out.println(message);
			}
		}
	}
}
