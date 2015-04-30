import java.io.*;
import java.net.*;

public class BattleShipServer	{
	
	static final int PORT = 1993;
	
	ServerThread player1;
	ServerThread player2;
	ServerSocket ss = null;
	static BattleShipGame bsg;
	
	public void getClients()	{
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
		
		BattleShipServer.bsg = new BattleShipGame(player1, player2);
	}
	
	public static void main(String[] args) {
		BattleShipServer bss = new BattleShipServer();
		bss.getClients();
		bsg.askForNames();
		while(true){}
	}
}

class ServerThread extends Thread{

	Socket client;
	String name;
	BufferedReader input;
	PrintWriter output;
	
	public ServerThread(Socket client) {
		this.client = client;
		initIO();
	}

	public void run() {
		while(true){}
	}

	private void initIO() {
		try {
			input = new BufferedReader(new InputStreamReader((client.getInputStream())));
			output = new PrintWriter(client.getOutputStream());
		} catch (IOException e) {
			System.out.println("Error in retrieving input and output streams for threads");
			e.printStackTrace();
		}
	}

	public void sendMessage(String message)	{
		output.println(message);
		output.flush();
	}

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
