import java.util.*;
import java.util.Map.*;
import javafx.util.*;

public class Board {
	int[][] board = new int[11][11];
	HashMap<String, Ship> ships = new HashMap<String, Ship>();
	
	public void addShip(String type, ArrayList<Pair<Integer, Integer>> locs)	{
		Ship ship = new Ship(locs.size(), locs);
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
	
	//need method to try and shoot ship
}
