package network;

import java.io.ObjectInputStream;
import java.util.Queue;

public class ClientInputThread extends Thread {
	private ObjectInputStream inStream;
	private Queue<Object> pushEnd;
	ClientInputThread (ObjectInputStream st, Queue<Object> giveTo) {
		inStream = st;
		pushEnd = giveTo;
	}
	public void run () {
		try{
		Object line = null;
		while((line = inStream.readObject()) != null){
			pushEnd.add(line);
//			System.out.println("return : "+(String)line);
		}
		} catch(Exception e) {
			System.out.println(this+"\t"+e);
		}
	}
}
