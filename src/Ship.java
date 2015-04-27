import java.util.ArrayList;
import javafx.util.Pair;

public class Ship {
	private int health;
	public ArrayList<Pair> locs;
	
	public Ship(int health, ArrayList<Pair> locs)	{
		this.health = health;
		for(int i = 0; i < locs.size(); i++)	{
			this.locs.add(locs.get(i));
		}
	}
	
	public void shipHit()	{
		this.health =- 1;
	}
	
	public boolean isSunk()	{
		return health <= 0;
	}
}


