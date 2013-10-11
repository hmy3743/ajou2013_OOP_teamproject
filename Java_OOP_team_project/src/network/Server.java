package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
	
	private ExecutorService threads = Executors.newFixedThreadPool(10);
	private ArrayList<Socket> sockets = new ArrayList<Socket>();
	private ArrayList<ObjectOutputStream> cast;
	
	public void start () {
		run();
	}
	private void run () {
		try{
			ServerSocket ss = new ServerSocket(10001);
			
			System.out.println("Waiting for Connection...");
			
			while(true){
				Socket so = ss.accept();
				addSocket(so);
				addThread(so);
			}
			
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	private void addSocket (Socket client) {
		sockets.add(client);
		try {
			cast.add(new ObjectOutputStream(client.getOutputStream()));
		} catch (IOException ignore) {}
	}
	
	private void addThread (Socket client) {
		try{
			ServerThread st = new ServerThread(client, this);
			threads.execute(st);
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	void broadcast (Object msg) {
		try{
			for(ObjectOutputStream cell : cast) {
				cell.writeObject(msg);
			
			}
		} catch (IOException ignore) {}
	}
}
