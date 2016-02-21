import java.io.*;

public class MessageReceiver implements Runnable
{
	private ObjectInputStream in;
	private MessageListener messageListener;

	public MessageReceiver(ObjectInputStream ois, MessageListener l)
	{
		in = ois;
		messageListener = l;
	}

	public void run()
	{
		while(true)
		{
			try
			{
				String message = (String)in.readObject();
				messageListener.deliverMessage(message);
			}
			catch(Exception e)
			{
				//e.printStackTrace();
				messageListener.removeMe();
				break;
			}
		}

	}

}