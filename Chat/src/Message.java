import java.io.Serializable;
import java.util.ArrayList;

public class Message implements Serializable{
	private String message;
	private String sender;
	private String receiver;
	private ArrayList<String> clients;
	
	public Message(String s, String r, String m, ArrayList<String> c) {
		sender = s;
		receiver = r;
		message = m;
		clients = c;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getSender() {
		return sender;
	}
	
	public String getReceiver() {
		return receiver;
	}
	
	public ArrayList<String> getClients() {
		return clients;
	}
}
