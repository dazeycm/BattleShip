import java.awt.Point;

import java.util.ArrayList;


public class Ship {
	private int health;
	public ArrayList<Point> locs;
	
	public Ship(int health, ArrayList<Point> locs)	{
		this.health = health;
		this.locs = locs;
	}
	
	public void shipHit()	{
		this.health =- 1;
	}
	
	public boolean isSunk()	{
		return health <= 0;
	}
}


