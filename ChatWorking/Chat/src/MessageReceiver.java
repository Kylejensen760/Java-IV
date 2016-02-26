import java.io.*;

public class MessageReceiver implements Runnable {
	private ObjectInputStream in;
	private MessageListener messageListener;

	public MessageReceiver(ObjectInputStream ois, MessageListener l) {
		in = ois;
		messageListener = l;
	}

	public void run() {
		while(true) {
			try {
				Message message = (Message) in.readObject();
				System.out.println("test----->" + message.getMessage() + message.getSender() + message.getReceiver());
				messageListener.deliverMessage(message);
			}
			catch(Exception e) {
				e.printStackTrace();
				messageListener.removeMe();
				break;
			}
		}
	}
}