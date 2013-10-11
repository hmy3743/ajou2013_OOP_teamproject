package network;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	private Socket conn;
	private Scanner scan = new Scanner(System.in);
	public void connect (String server) {
		try {
			conn = new Socket(server, 10001);
			ObjectOutputStream out;
			ObjectInputStream in;
			in = new ObjectInputStream(conn.getInputStream());
			out = new ObjectOutputStream(conn.getOutputStream());
			ClientInputThread inThread = new ClientInputThread(in);
			inThread.start();
			String output = "";
			while (!output.equals(":q")) {
				output = scan.next();
				out.writeObject(output);
			}
			conn.close();
			out.close();
			in.close();
		} catch (Exception e) {
			System.out.println(this+"\t"+e);
		}
	}
}
