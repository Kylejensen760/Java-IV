import java.util.*;
import java.net.*;
import java.io.*;

public class ServerSideClientHandler implements Runnable, MessageListener {
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private ArrayList<ServerSideClientHandler> list;
	private MessageReceiver mr;
	private String userID;
	private boolean firstMessage = true;

	public ServerSideClientHandler(Socket socket, ArrayList<ServerSideClientHandler> l) {
		try {
			in = new ObjectInputStream(socket.getInputStream());
			mr = new MessageReceiver(in, this);
			Thread t = new Thread(mr);
			out = new ObjectOutputStream(socket.getOutputStream());
			t.start();
			list = l;
		} catch(Exception e) {
			System.out.println("Unable to connect.");
			//e.printStackTrace();
		}
	}

	public void deliverMessage(Message m) {
		if(firstMessage) {
			userID = m.getSender();
			firstMessage = false;
		}
		else {
			System.out.println(m.getSender() + " said: " + m.getMessage());
			ArrayList<String> clients = new ArrayList<String>();
			
			for(int i = 0; i < list.size(); i++) 
				clients.add(list.get(i).getUserID());
			
			Message outMessage = new Message(m.getSender(), m.getReceiver(), m.getMessage(), clients);
			for(int i = 0; i < list.size(); i++) {
				if(m.getSender().equals(list.get(i).getUserID()) && !(m.getReceiver().equals("All Users"))) {
					list.get(i).sendMail(outMessage);
				}
				if(m.getReceiver().equals(list.get(i).getUserID())) {
					list.get(i).sendMail(outMessage);
				}
				else if(m.getReceiver().equals("All Users")){
					list.get(i).sendMail(outMessage);
				}
			}
		}
	}

	public void sendMail(Message m) {
		try {
			out.writeObject(m);
		} catch(Exception e) {
			System.out.println("Client can not receive mail.");
			//e.printStackTrace();
		}
	}

	public void removeMe() {
		list.remove(this);
		System.out.println("Client left.  There are " + list.size() + " clients connected.");
	}

	public void run() {
		System.out.println("New client is here!!!");
	}
	
	public String getUserID() {
		return userID;
	}
}