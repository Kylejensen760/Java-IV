import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;


public class Controller implements Runnable, ActionListener{

	private ObjectOutputStream out;
	private List<PlayerClientHandler> list;
	private Update updates;

	
	public Controller(List<PlayerClientHandler> l) {
		list = l;
		Timer timer = new Timer(50, this);
		timer.addActionListener(this);
		timer.start();
	}
	
	private void prepareUpdates() {
		if(list.size() > 0) {
			List<Player> pList = new ArrayList<Player>();
			for(PlayerClientHandler pch : list) {
				if(pch.getPlayer() != null)
					pList.add(pch.getPlayer());
			}
			
			for(Player p : pList) {
				System.out.println("Before: " + p.getX() + " " + p.getDestX());
				int dX = p.getDestX() - p.getX();
				int dY = p.getDestY() - p.getY();
				double magnitude = Math.sqrt(Math.pow(dX, 2) + Math.pow(dY, 2));
				double uX = (dX / magnitude);
				int xVel = (int) Math.rint(uX * 5);
			
				double uY = (dY / magnitude);
				int yVel = (int) Math.rint(uY * 5);
				if(p.getX() != p.getDestX())
					p.setX(p.getX() + xVel);
				if(p.getY() != p.getDestY())
					p.setY(p.getY() + yVel);
				
				System.out.println("After set: " + p.getX() + " " + p.getDestX() + " " + xVel);
			}
			//updates = new Update(pList);
			sendUpdate(pList);
		}
	}
	
	private void sendUpdate(Object obj) {
		for(int i = 0; i < list.size(); i++) 
			list.get(i).sendUpdate(obj);
	}

	@Override
	public void run() {
		System.out.println("Controller has started..");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(list.size() > 0 && list.get(0).getPlayer() != null)
			//System.out.println(list.get(0).getPlayer().getX() + " " + list.get(0).getPlayer().getDestX());
		prepareUpdates();
		//sendUpdate(pList);
	}
}
