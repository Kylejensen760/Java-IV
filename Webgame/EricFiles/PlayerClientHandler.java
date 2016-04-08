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
			//System.out.println("Player " + m_player.getID() + " location: (" + m_player.getX() + ", " + m_player.getDestX() + ")");
		}
	}
		/*	List<Player> pList = new ArrayList<Player>();
			for(PlayerClientHandler pcl : list) {
				pList.add(pcl.getPlayer());
			}
			for(int i = 0; i < list.size(); i++) {
					list.get(i).sendUpdate(new Update(pList));
			}
		}
		else if(obj instanceof Update) {
			for(int i = 0; i < list.size(); i++) {
				list.get(i).sendUpdate(obj);
			}
		}
	}*/

	public void sendUpdate(Object obj) {
		try {
			List<Player> pList2 = new ArrayList<Player>();
			List<Player>  pList = (List<Player>) obj;
			if(pList.size() > 0) {
				for(int i = 0; i < pList.size(); i++) {
					Player p = pList.get(i);
					pList2.add(new Player(p));
					System.out.println("Client Handler " + p.toString());
				}
			} //DATA GETTING LOST HERE, NOT MAKING IT TO PLAYER CLIENT
		//	System.out.println("Sending Updates");
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