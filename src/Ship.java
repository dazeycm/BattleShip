import java.util.List;


public class Ship {
	private int health;
	public List<Integer> locs;
	
	public Ship(int health, List<Integer> shipLocs)	{
		this.health = health;
		this.locs = shipLocs;
	}
	
	public void shipHit()	{
		this.health -= 1;
	}
	
	public boolean isSunk()	{
		return health <= 0;
	}
}


