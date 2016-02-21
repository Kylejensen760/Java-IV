import java.util.*;
import java.net.*;
import java.io.*;

public class Server
{
	public static void main(String[] args) throws Exception
	{
		ArrayList<ServerSideClientHandler> list = new ArrayList<ServerSideClientHandler>();
		System.out.println("Server Started...");
		ServerSocket ss = new ServerSocket(4324);
		while(true)
		{
			System.out.println("Waiting for clients to connect...");
			Socket socket = ss.accept();
			ServerSideClientHandler ssch = new ServerSideClientHandler(socket, list);
			list.add(ssch);
			Thread t = new Thread(ssch);
			t.start();
			System.out.println("Client connected.  There are " + list.size() + " clients connected.");
		}
	}
}
