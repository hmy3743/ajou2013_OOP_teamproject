package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Queue;

public class Client extends Thread {
	private Socket conn;
	private Queue<Object> inQueue;
	private Queue<Object> outQueue;
	private String server;
	private ClientInputThread inThread;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	public Client (Queue<Object> inQueue, Queue<Object> outQueue, String server) {
		this.inQueue = inQueue;
		this.outQueue = outQueue;
		this.server = server;
	}
	public void run () {
		connect();
	}
	private void connect () {
		try {
			conn = new Socket(server, 10001);
			System.out.println("Connecting success!");
			in = new ObjectInputStream(conn.getInputStream());
			out = new ObjectOutputStream(conn.getOutputStream());
			inThread = new ClientInputThread(in, outQueue);
			inThread.start();
			int qsize = -1;
			while (true) {
				if(qsize != inQueue.size()){
					qsize = inQueue.size();
//					System.out.println("qsize = "+qsize);
				}
				if (!inQueue.isEmpty()){
//					System.out.println("inQueue = "+inQueue);
//					System.out.println("outQueue = "+outQueue);
//					if(inQueue.peek() instanceof ExitFlag) break;
					out.writeObject(inQueue.poll());
//					System.out.println("writing on socket");
				}
			}
		} catch (Exception e) {
			System.out.println("client "+this+"\t"+e);
		} finally {
			inThread.interrupt();
			try {
				out.close();
				conn.close();
			} catch (IOException ignore) {}
			System.out.println("Disconnect!");
		}
	}
}
