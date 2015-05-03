import java.io.*;
import java.net.*;
import java.util.*;

public class BattleShipClient{
	final int PORT = 1993;
	
	private Socket socket;
	public BufferedReader input;
	public PrintWriter output;
	public Scanner kb;
	
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
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output = new PrintWriter(socket.getOutputStream());
			kb = new Scanner(System.in);
		} catch (IOException e) {
			System.out.println("Error in retrieving input and output streams for threads");
			e.printStackTrace();
		}
	}
	
	public String readLine()	{
		try {
			return this.input.readLine();
		} catch (IOException e) {
			System.out.println("Error when client tried to read message");
			e.printStackTrace();
		}
		return null;
	}
	
	public void sendMessage(String message)	{
		output.println(message);
		output.flush();
	}
	
	public static void main(String[] args)	{
		BattleShipClient bsc = new BattleShipClient();
		GameBoard gb = null;
		BSAskShips getShips = null;
		while(true)	{
			String message = bsc.readLine();
			if(message.contains(Protocol.ALBUS))	{
				new BSAskName(bsc);
			}
			else if(message.contains(Protocol.VOLDY))	{
				getShips = new BSAskShips(bsc);
			}
			else if(message.contains(Protocol.POTTER))	{
				getShips.kill();
				List<String> parts = Arrays.asList(message.split(" "));
				String locations = parts.get(1);
				gb = new GameBoard(bsc, locations);
			}
		}
	}
}
