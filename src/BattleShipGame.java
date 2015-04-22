
public class BattleShipGame {

	public ServerThread player1;
	public ServerThread player2;
	
	public BattleShipGame(ServerThread player1, ServerThread player2) {
		this.player1 = player1;
		this.player2 = player2;
	}

	public void askForNames() {
		player1.sendMessage(new String("Placeholder"));
	}

	
	public static void main(String[] args) {
		
	}
}
