import java.io.Serializable;

@SuppressWarnings("serial")
public class Player implements Serializable{

	private String userID;
	private String location;
	private int health = 100;
	private int gold = 0;
	private int x;
	private int y;
	private int destX;
	private int destY;

	public Player(String setID, String setLoc, int startX, int startY) {
		userID = setID;
		setLocation(setLoc);
		setX(startX);
		setY(startY);
	}
	
	public String getID()
		{ return userID; }
	public String getLocation() 
		{ return location; }
	public void setLocation(String l) 
		{ location = l; }
	public int getX() 
		{ return x; }
	public void setX(int num) 
		{ x = num; }
	public int getY() 
		{ return y; }
	public void setY(int num) 
		{ y = num; }
	public int getDestX() 
		{ return destX; }
	public void setDestX(int num) 
		{ destX = num; }
	public int getDestY() 
		{ return destY; }
	public void setDestY(int num) 
		{ destY = num; }
	public int getGold() 
		{ return gold; }
	public void setGold(int num) 
		{ gold = num; }
	public int getHealth() 
		{ return health; }
	public void setHealth(int num)
		{ health = num; }
}