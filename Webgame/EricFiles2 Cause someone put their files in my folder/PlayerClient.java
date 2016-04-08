import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class PlayerClient implements UpdateListener {
	private ObjectOutputStream out;	
	private Socket socket;
	private Player m_player;
	private List<Player> pList;
	private Map map; 
	private int startX = 250;
	private int startY = 250;

	public static void main(String[] args) {
		new PlayerClient();
	}
	
	public PlayerClient() {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter Username: ");
		String name = input.nextLine();
		input.close();
		m_player = new Player(name, "Main", startX, startY);
		map = new Map(this, "Level1.txt", pList);
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
			out.writeUnshared(m_player);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendUpdate() {
		try {
			out.writeUnshared(m_player);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateMe(Object obj) {
		//Update update = (Update) obj;
	//	pList = update.getPList();
		
		List<Player>  pList = (List<Player>) obj;
		if(pList.size() > 0) {
			for(int i = 0; i < pList.size(); i++) {
				Player p = pList.get(i);
				System.out.println("Client Side: " + p.toString());
			}
		}
		
		map.updatePlayers(pList);
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
	
	public Player getPlayer() {
		return m_player;
	}
}
