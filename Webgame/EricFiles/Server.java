import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
	
	private static List<PlayerClientHandler> list;
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		list = new ArrayList<PlayerClientHandler>();
		System.out.println("Server Started...");
		ServerSocket ss = new ServerSocket(4324);

		while(true) {
			System.out.println("Waiting for players to connect...");
			Socket socket = ss.accept();
			PlayerClientHandler ssch = new PlayerClientHandler(socket, list);
			list.add(ssch);
			Thread t = new Thread(ssch);
			t.start();
			System.out.println("Player connected.  There are " + list.size() + " players connected.");
		}
	}
	
	
}
