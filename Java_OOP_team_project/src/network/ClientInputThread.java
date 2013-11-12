package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Queue;

public class ClientInputThread extends Thread {
	private ObjectInputStream inStream;
	private Queue<Object> outQueue;
	ClientInputThread (ObjectInputStream st, Queue<Object> outQueue) {
		inStream = st;
		this.outQueue = outQueue;
	}
	public void run () {
		System.out.println("ready for input");
		try{
		Object line = null;
		while((line = inStream.readObject()) != null){
			System.out.println("catch "+(String)line);
			outQueue.offer(line);
		}
		} catch(Exception e) {
			System.out.println("clientInputThread "+this+"\t"+e);
		} finally {
			try {
				inStream.close();
			} catch (IOException e) {
			}
		}
	}
}
