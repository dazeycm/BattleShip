import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;


public class BattleShipClient{
	
	public static void main(String[] args)	{
		final int PORT = 1993;
		
		InetAddress address;
		String hostName = null;
		
		try {
			address = InetAddress.getLocalHost();
			hostName = address.getHostName();
		} catch (UnknownHostException e) {
			System.out.println("Error getting localhost");
			e.printStackTrace();
		}
		
		Socket s = null;
		try {
			s = new Socket(hostName, PORT);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		while(true){}
	}

}
