package network;

import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.Socket;

public class ServerThread extends Thread {
	private Socket conn;
	private Server parent;
	private NetCallable pushTo;
	private int id;
	public ServerThread(Socket Clinet, Server p, NetCallable pushTo, int id) {
		conn = Clinet;
		parent = p;
		this.pushTo = pushTo;
		this.id = id;
	}

	public void run() {
//		System.out.println("ready for input");
		ObjectInputStream input;
		input = null;
		try{
			input = new ObjectInputStream(conn.getInputStream());
			Serializable in = null;
			while ((in = (Serializable)input.readObject()) != null) {
				pushTo.pushMessage(in, id);
				in = null;
			}
		} catch (Exception e) {
			parent.bye(conn);
			try{
				conn.close();
				input.close();
			}catch(Exception ignore){}
//			System.out.println("Disconnect from "+target);
		}
	}
}
