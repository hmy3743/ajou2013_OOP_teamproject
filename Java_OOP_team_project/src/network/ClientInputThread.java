package network;

import java.io.ObjectInputStream;

public class ClientInputThread extends Thread {
	private ObjectInputStream inStream;
	private NetCallable pushTo;
	private Client parent;
	ClientInputThread (ObjectInputStream st, NetCallable pushTo, Client parent) {
		inStream = st;
		this.pushTo = pushTo;
		this.parent = parent;
	}
	public void run () {
		System.out.println("ready for input");
		try{
		Object line = null;
		while((line = inStream.readObject()) != null){
//			System.out.println("catch "+(String)line);
			pushTo.pushMessage(line);
		}
		} catch(Exception e) {
			System.out.println("clientInputThread "+this+"\t"+e);
		} finally {
			parent.disconnect();
		}
	}
}
