package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

public class Client {
	private Socket conn;
	private NetCallable pushTo;
	private String server;
	private ClientInputThread inThread;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	public Client (NetCallable pushTo, String server) {
		this.pushTo = pushTo;
		this.server = server;
		connect();
	}
	private void connect () {
		try {
			conn = new Socket(server, 10001);
			System.out.println("Connecting success!");
			in = new ObjectInputStream(conn.getInputStream());
			out = new ObjectOutputStream(conn.getOutputStream());
			inThread = new ClientInputThread(in, pushTo, this, 0);
			inThread.start();
		} catch (Exception e) {
			System.out.println("client "+this+"\t"+e);
		}
	}
	public void send (Serializable message) {
		try {
			out.writeObject(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	void disconnect () {
		try {
			in.close();
			out.close();
			conn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pushTo.disconnect();
	}
	public void exit () {
		disconnect();
	}
}
