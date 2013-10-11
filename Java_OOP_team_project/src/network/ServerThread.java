package network;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerThread extends Thread {
	Socket conn;

	ServerThread(Socket Clinet) {
		conn = Clinet;
	}

	public void run() {
		ObjectInputStream input;
		ObjectOutputStream output;
		try{
			input = new ObjectInputStream(conn.getInputStream());
			output = new ObjectOutputStream(conn.getOutputStream());
			Object in = null;
			while ((in = input.readObject()) != null) {
				output.writeObject(in);
				in = null;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
