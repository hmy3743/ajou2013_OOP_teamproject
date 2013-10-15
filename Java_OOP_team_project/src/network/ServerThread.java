package network;

import java.io.ObjectInputStream;
import java.net.Socket;

public class ServerThread extends Thread {
	Socket conn;
	Server parent;
	
	ServerThread(Socket Clinet, Server p) {
		conn = Clinet;
		parent = p;
	}

	public void run() {
		ObjectInputStream input;
		String target = conn.getInetAddress().toString();
		try{
			input = new ObjectInputStream(conn.getInputStream());
			Object in = null;
			while ((in = input.readObject()) != null) {
				System.out.println("msg "+(String)in+" from "+conn.getInetAddress());
				synchronized(Server.class){
					parent.broadcast(conn, in);
				}
				in = null;
			}
			input.close();
			conn.close();
			parent.bye(conn);
		} catch (Exception e) {
			parent.bye(conn);
//			System.out.println("Disconnect from "+target);
		}
	}
}
