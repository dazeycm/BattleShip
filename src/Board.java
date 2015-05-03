import java.awt.Point;
import java.util.*;
import java.util.Map.*;

public class Board {
	HashMap<String, Ship> ships; 
	
	public Board()	{
		ships = new HashMap<String, Ship>();
	}
	
	public void addShip(String type, List<Integer> intShipLocs)	{
		Ship ship = new Ship(intShipLocs.size(), intShipLocs);
		ships.put(type, ship);
	}
	
	public int numShipsReamining()	{
		int numShipsLeft = 0;
		for(Entry<String, Ship> entry : ships.entrySet())	{
			if(!entry.getValue().isSunk())	{
				numShipsLeft++;
			}
		}
		return numShipsLeft;
	}
}
