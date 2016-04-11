import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class PlayerClientHandler implements Runnable, UpdateListener {
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private List<PlayerClientHandler> list;
	private Player m_player;
	private UpdateReceiver ur;

	public PlayerClientHandler(Socket socket, List<PlayerClientHandler> l) {
		try {
			in = new ObjectInputStream(socket.getInputStream());
			ur = new UpdateReceiver(in, this);
			Thread t = new Thread(ur);
			out = new ObjectOutputStream(socket.getOutputStream());
			t.start();
			list = l;
		} catch(Exception e) {
			System.out.println("Unable to connect.");
			//e.printStackTrace();
		}
	}

	public void updateMe(Object obj) {
		if(obj instanceof Player) {
			m_player = (Player) obj;
		}
	}

	public void sendUpdate(Object obj) {
		try {
			List<Player>  pList = (List<Player>) obj;
			List<Player> pList2 = new ArrayList<Player>();
			if(pList.size() > 0) {
				for(Player p : pList) {
					pList2.add(new Player(p));
				}
			} 
			out.writeUnshared(pList2);
		} catch(Exception e) {
			System.out.println("Player cannot receive update.");
			e.printStackTrace();
		}
	}

	public void removeMe() {
		list.remove(this);
		System.out.println("Player left.  There are " + list.size() + " players connected.");
	}

	public void run() {
		System.out.println("New player is here!!!");
	}
	
	public Player getPlayer() {
		return m_player;
	}
}