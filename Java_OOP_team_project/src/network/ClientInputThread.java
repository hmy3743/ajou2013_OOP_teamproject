package network;

import java.io.ObjectInputStream;

public class ClientInputThread extends Thread {
	private ObjectInputStream inStream;
	ClientInputThread (ObjectInputStream st) {
		inStream = st;
	}
	public void run () {
		try{
		Object line = null;
		while((line = inStream.readObject()) != null){
			System.out.println("return : "+(String)line);
		}
		} catch(Exception e) {
			System.out.println(this+"\t"+e);
		}
	}
}
