package network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
	
	private ExecutorService threads = Executors.newFixedThreadPool(10);
	private ArrayList<Socket> sockets = new ArrayList<Socket>();
	
	public void start (int iclientNum) {
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
	}
	
	private void addThread (Socket client) {
		try{
			ServerThread st = new ServerThread(new ObjectInputStream(client.getInputStream()));
			threads.execute(st);
		}catch(Exception e) {
			System.out.println(e);
		}
	}
}
