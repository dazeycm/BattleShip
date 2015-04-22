import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;


public class BattleShipClient{
	final int PORT = 1993;
	
	private Socket socket;
	
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
	}
	
	public static void main(String[] args)	{
		BattleShipClient bsc = new BattleShipClient();
		
		while(true){}
	}

}
