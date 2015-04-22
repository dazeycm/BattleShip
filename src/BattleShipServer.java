import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class BattleShipServer	{
	
	static final int PORT = 1993;
	
	ServerThread playerA;
	ServerThread playerB;
	ServerSocket ss = null;
	
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
		player1.start();
		
		try {
			client = ss.accept();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ServerThread player2 = new ServerThread(client);
		player2.start();
		
		while(true){}
		
	}
	
	public static void main(String[] args) {
		BattleShipServer bss = new BattleShipServer();
		bss.getClients();
	}
}

class ServerThread extends Thread	{

	Socket client;
	String name;
	
	public ServerThread(Socket client) {
		this.client = client;
	}

	public void run() {
		while(true){}
	}
	
}
