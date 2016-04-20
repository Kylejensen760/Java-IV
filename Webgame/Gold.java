import java.util.Random;
import java.lang.Math;
import javax.swing.ImageIcon;

public class Gold
{
	private ImageIcon gold = new ImageIcon(getClass().getResource("images/CoinGif.gif")); //Image
	private boolean alive; //Status of gold - alive or dead
	private int xPos; // x-position
	private int yPos; //y-position

	public Gold() //Default Constructor
	{

	}

	public void regenerateGold()	//Respawns gold in random new location
	{
		Random r = new Random();
		int newX = r.nextInt(500);
		int newY = r.nextInt(500);
		xPos = 10*(newX/10);
		yPos = 10*(newY/10);
		alive = true;
	}

	public boolean getState()
	{
		return this.alive;
	}
	public void setState(boolean s)
	{
		this.alive = s;
	}

	public int getX()
	{
		return this.xPos;
	}
	public int getY()
	{
		return this.yPos;
	}

}