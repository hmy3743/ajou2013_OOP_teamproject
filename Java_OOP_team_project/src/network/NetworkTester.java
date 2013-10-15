package network;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class NetworkTester {
	private static Scanner scan = new Scanner(System.in);
	public static void main (String args[]) {
		System.out.println("input 1 for server else integer for client");
		int choice = scan.nextInt();
		Queue<Object> msg = new LinkedList<Object>();
		if(choice == 1){
			Server s = new Server(msg);
			s.start();
		}
		else{
			System.out.println("input target server ip or domain");
			Client c = new Client(msg, scan.next());
			c.start();
		}
		
		Message in = null;
		while(true){
			while((in = (Message) msg.poll()) == null);
			System.out.println(in.toString());
		}
	}
}
