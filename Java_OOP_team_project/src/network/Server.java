package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

	private ExecutorService threads = Executors.newFixedThreadPool(10);
	private ArrayList<Socket> sockets = new ArrayList<Socket>();
	private ArrayList<ConnectInform> cast = new ArrayList<ConnectInform>();

	public void start() {
		run();
	}

	private void run() {
		try {
			ServerSocket ss = new ServerSocket(10001);

			System.out.println("Waiting for Connection...");

			while (true) {
				Socket so = ss.accept();
				System.out.println("new connect from " + so.getInetAddress());
				addSocket(so);
				addThread(so);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void addSocket(Socket client) {
		sockets.add(client);
		cast.add(new ConnectInform(client));
	}

	private void addThread(Socket client) {
		try {
			ServerThread st = new ServerThread(client, this);
			threads.execute(st);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	void broadcast(Socket from, Object msg) {
		try {
			for (ConnectInform cell : cast) {
				if (cell.getSocket() == from) {
					continue;
				}
				cell.getOut().writeObject(msg);
			}
		} catch (IOException ignore) {
		}
	}
}
