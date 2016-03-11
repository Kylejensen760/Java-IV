import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class PlayerClient implements UpdateListener, MouseListener {
	private ObjectOutputStream out;	
	private Socket socket;
	private Player m_player;
	private int startX = 250;
	private int startY = 250;

	public static void main(String[] args) {
		new PlayerClient();
	}
	
	public PlayerClient() {
		m_player = new Player("Eric", "Main", startX, startY);
		run();
	}
	
	public void run() {		
		try {
			socket = new Socket("localhost", 4324);
			out = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			UpdateReceiver ur = new UpdateReceiver(in, this);
			Thread t = new Thread(ur);
			t.start();
			
			out.writeObject(m_player);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendUpdate() {
		try {
			out.writeObject(m_player);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateMe(Object obj) {
		List<Player> pList = (List<Player>) obj;
		for(Player p : pList) {
			System.out.println(p.getID());
		}
	}

	@Override
	public void removeMe() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		m_player.setX(e.getX());
		m_player.setY(e.getY());
		sendUpdate();
	}

	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
}
