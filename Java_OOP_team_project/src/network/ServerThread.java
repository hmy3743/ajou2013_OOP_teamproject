package network;

import java.io.ObjectInputStream;
import java.net.Socket;

public class ServerThread extends Thread {
	private Socket conn;
	private Server parent;
	public ServerThread(Socket Clinet, Server p) {
		conn = Clinet;
		parent = p;
	}

	public void run() {
//		System.out.println("ready for input");
		ObjectInputStream input;
		input = null;
		try{
			input = new ObjectInputStream(conn.getInputStream());
			Object in = null;
			while ((String)(in = input.readObject()) != null) {
//				System.out.println("catch "+(String)in);
				parent.broadcast(conn, in);
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
