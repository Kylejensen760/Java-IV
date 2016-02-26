import java.io.Serializable;

public class Message implements Serializable{
	private String message;
	private String sender;
	private String receiver;
	
	public Message(String s, String r, String m) {
		sender = s;
		receiver = r;
		message = m;
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
}
