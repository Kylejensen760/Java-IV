import java.util.Random;
import java.lang.Math;

public class Gold
{
	boolean alive = false;
	int xPos = 0;
	int yPos = 0;




	public void regenerateGold()	//Respawns gold in random new location
	{
		Random r = new Random();
		double newX = r.nextInt(500);
		double newY = r.nextInt(500);
		xPos = 10*(newX/10);
		yPos = 10*(newY/10);
		alive = true;
	}

}