import java.io.ObjectInputStream;

public class UpdateReceiver implements Runnable {
	private ObjectInputStream in;
	private UpdateListener updateListener;

	public UpdateReceiver(ObjectInputStream ois, UpdateListener ul) {
		in = ois;
		updateListener = ul;
	}

	public void run() {
		while(true) {
			try {
						Object obj = in.readObject();
						updateListener.updateMe(obj);
					
			}
			catch(Exception e) {
				e.printStackTrace();
				updateListener.removeMe();
				break;
			}
		}
	}
}