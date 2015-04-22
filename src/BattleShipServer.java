import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class BattleShipServer	{
	
	static final int PORT = 1993;
	
	ServerThread playerA;
	ServerThread playerB;
	ServerSocket ss = null;
	BattleShipGame bsg;
	
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
		ServerThread player1 = new ServerThread(client);
		player1.run();
		
		try {
			client = ss.accept();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ServerThread player2 = new ServerThread(client);
		player2.run();
		
		bsg = new BattleShipGame(player1, player2);
		
		bsg.askForNames();
		while(true){}
		
	}
	
	public static void main(String[] args) {
		BattleShipServer bss = new BattleShipServer();
		bss.getClients();
	}
}

class ServerThread implements Runnable {

	Socket client;
	String name;
	InputStream input;
	OutputStream output;
	
	public ServerThread(Socket client) {
		this.client = client;
	}

	public void run() {
		initIO();
		while(true){}
	}

	private void initIO() {
		try {
			input = client.getInputStream();
			output = client.getOutputStream();
		} catch (IOException e) {
			System.out.println("Error in retrieving input and output streams for threads");
			e.printStackTrace();
		}
	}

	public void sendMessage(String message) {
		try {
			output.write("message".getBytes());
		} catch (IOException e) {
			System.out.println("Error sending message from server to client");
			e.printStackTrace();
		}
	}
	
}
