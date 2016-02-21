import java.util.*;
import java.net.*;
import java.io.*;

public class Client implements MessageListener
{
	public static void main(String[] args)
	{
		new Client();
	}

	public Client()
	{
		String request = "";
		Scanner keys = new Scanner(System.in);
		try
		{
			Socket socket = new Socket("localhost", 4324);
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			MessageReceiver mr = new MessageReceiver(in, this);
			Thread t = new Thread(mr);
			t.start();
			while(!request.equals("bye"))
			{
				//get request
				request = keys.nextLine();
				//send request
				out.writeObject(request);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void deliverMessage(String s)
	{
		System.out.println(s);
	}

	public void removeMe()
	{
		System.exit(0);
	}
}