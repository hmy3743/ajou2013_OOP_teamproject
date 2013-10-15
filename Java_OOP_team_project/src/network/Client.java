package network;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Queue;
import java.util.Scanner;

public class Client extends Thread {
	private Socket conn;
	private Scanner scan = new Scanner(System.in);
	private Queue<Object> pushEnd;
	private String server;
	public Client (Queue<Object> msgQueue, String server) {
		pushEnd = msgQueue;
		this.server = server;
	}
	public void run () {
		connect();
	}
	private void connect () {
		try {
			conn = new Socket(server, 10001);
			System.out.println("Connecting success!");
			ObjectOutputStream out;
			ObjectInputStream in;
			in = new ObjectInputStream(conn.getInputStream());
			out = new ObjectOutputStream(conn.getOutputStream());
			ClientInputThread inThread = new ClientInputThread(in, pushEnd);
			inThread.start();
			Message output = new Message();
			while (true) {
				output.setType(MessageType.CHAT);
				output.setMessage(new ChatStruct(null, scan.nextLine()));
				if (output.getMessage().getMsg().equals(":q")) break;
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
