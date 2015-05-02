import java.awt.Point;

import java.util.ArrayList;


public class Ship {
	private int health;
	public ArrayList<Point> locs;
	
	public Ship(int health, ArrayList<Point> locs)	{
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


