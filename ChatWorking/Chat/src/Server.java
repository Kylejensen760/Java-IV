import java.util.*;
import java.net.*;
import java.io.*;

public class Server {
	
	private static ArrayList<ServerSideClientHandler> list;
	
	public static void main(String[] args) throws Exception {
		list = new ArrayList<ServerSideClientHandler>();
		System.out.println("Server Started...");
		ServerSocket ss = new ServerSocket(4324);

		while(true) {
			System.out.println("Waiting for clients to connect...");
			Socket socket = ss.accept();
			ServerSideClientHandler ssch = new ServerSideClientHandler(socket, list);
			list.add(ssch);
			Thread t = new Thread(ssch);
			t.start();
			System.out.println("Client connected.  There are " + list.size() + " clients connected.");
		}
	}
	
	public static ArrayList<String> getUsers() {
		ArrayList<String> userList = new ArrayList<String>();
		for(ServerSideClientHandler c: list) {
			String temp = c.getUserID();
			userList.add(temp);
			System.out.println(temp);
		}
		
		return userList;	
	}
}