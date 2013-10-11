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
		try{
			input = new ObjectInputStream(conn.getInputStream());
			Object in = null;
			while ((in = input.readObject()) != null) {
				synchronized(Server.class){
					parent.broadcast(conn, in);
				}
				in = null;
			}
			conn.close();
			input.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
