package network;

import java.io.ObjectInputStream;
import java.io.Serializable;

public class ClientInputThread extends Thread {
	private ObjectInputStream inStream;
	private NetCallable pushTo;
	private Client parent;
	private int id;
	ClientInputThread (ObjectInputStream st, NetCallable pushTo, Client parent, int id) {
		inStream = st;
		this.pushTo = pushTo;
		this.parent = parent;
		this.id = id;
	}
	public void run () {
		System.out.println("ready for input");
		try{
		Serializable line = null;
		while((line = (Serializable) inStream.readObject()) != null){
//			System.out.println("catch "+(String)line);
			pushTo.pushMessage(line, id);
		}
		} catch(Exception e) {
			System.out.println("clientInputThread "+this+"\t"+e);
		} finally {
			parent.disconnect();
		}
	}
}
