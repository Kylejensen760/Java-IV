import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class PlayerClientHandler implements Runnable, UpdateListener {
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private Socket mySocket;
	private List<PlayerClientHandler> list;
	private Player m_player;
	private UpdateReceiver ur;

	public PlayerClientHandler(Socket socket, List<PlayerClientHandler> l) {
		try {
			mySocket = socket;
			in = new ObjectInputStream(mySocket.getInputStream());
			ur = new UpdateReceiver(in, this);
			Thread t = new Thread(ur);
			out = new ObjectOutputStream(mySocket.getOutputStream());
			t.start();
			list = l;
		} catch(Exception e) {
			System.out.println("Unable to connect.");
			//e.printStackTrace();
		}
	}

	public void updateMe(Object obj) {
		m_player = (Player) obj;
		System.out.println("Player " + m_player.getID() + " location: (" + m_player.getX() + ", " + m_player.getY() + ")");
		List<Player> pList = new ArrayList<Player>();
		for(PlayerClientHandler pcl : list) {
			pList.add(pcl.getPlayer());
		}
		for(int i = 0; i < list.size(); i++) {
				list.get(i).sendUpdate(pList);
		}
	}

	public void sendUpdate(Object obj) {
		try {
			out.writeObject(/*(List<Player>)*/ obj);
		} catch(Exception e) {
			System.out.println("Player cannot receive update.");
			e.printStackTrace();
		}
	}

	public void removeMe() {
		try {
			in.close();
			out.close();
			mySocket.close();
			list.remove(this);
			System.out.println("Player left.  There are " + list.size() + " players connected.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void run() {
		System.out.println("New player is here!!!");
	}
	
	public Player getPlayer() {
		return m_player;
	}
}