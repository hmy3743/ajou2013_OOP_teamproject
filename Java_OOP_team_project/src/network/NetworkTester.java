package network;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class NetworkTester {
	private static Scanner scan = new Scanner(System.in);
	public static void main (String args[]) {
		System.out.println("input 1 for server else integer for client");
		int choice = scan.nextInt();
		if(choice == 1){
			Server s = new Server();
			s.start();
		}
		else{
			System.out.println("input target server ip or domain");
			Queue<Object> msg = new LinkedList<Object>();
			Client c = new Client(msg);
			c.connect(scan.next());
		}
	}
}
