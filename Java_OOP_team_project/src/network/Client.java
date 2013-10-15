package network;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Queue;
import java.util.Scanner;

public class Client {
	private Socket conn;
	private Scanner scan = new Scanner(System.in);
	private Queue<Object> pushEnd;
	public Client (Queue<Object> msgQueue) {
		pushEnd = msgQueue;
	}
	public void connect (String server) {
		try {
			conn = new Socket(server, 10001);
			System.out.println("Connecting success!");
			ObjectOutputStream out;
			ObjectInputStream in;
			in = new ObjectInputStream(conn.getInputStream());
			out = new ObjectOutputStream(conn.getOutputStream());
			ClientInputThread inThread = new ClientInputThread(in, pushEnd);
			inThread.start();
			String output;
			while (true) {
				output = scan.nextLine();
				if (output.equals(":q")) break;
				out.writeObject(output);
			}
			out.close();
			in.close();
			conn.close();
			System.out.println("Disconnect!");
		} catch (Exception e) {
//			System.out.println(this+"\t"+e);
		}
	}
}
