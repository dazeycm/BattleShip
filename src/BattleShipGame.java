
public class BattleShipGame {

	public ServerThread player1;
	public ServerThread player2;
	
	public Board player1Board;
	public Board player2Board;
	
	public BattleShipGame(ServerThread player1, ServerThread player2) {
		this.player1 = player1;
		this.player2 = player2;
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
		player1.sendMessage(Protocol.SNAPE);
		player2.sendMessage(Protocol.SNAPE);
		
		//make boards here from received message
	}

	public static void main(String[] args) {
		
	}
}
