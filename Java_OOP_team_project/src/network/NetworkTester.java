package network;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class NetworkTester {
	private static Scanner scan = new Scanner(System.in);
	public static void main (String args[]) {
		System.out.println("input 1 for server else integer for client");
		int choice = scan.nextInt();
		scan.nextLine();
		Queue<Object> msgIn = new LinkedList<Object>();
		Queue<Object> msgOut = new LinkedList<Object>();
		if(choice == 1){
			System.out.println("Input the size of room");
			Server s = new Server(msgIn, msgOut, scan.nextInt());
			scan.nextLine();
			s.start();
		}
		else{
			System.out.println("input target server ip or domain");
			Client c = new Client(msgIn, msgOut, scan.next());
			scan.nextLine();
			c.start();
		}
		while(true){
			while(!msgOut.isEmpty())
				System.out.println((String)(msgOut.poll()).toString());
			String msg = scan.nextLine();
//			System.out.println("Inqueue size = "+msgOut.size());
//			System.out.println("echo on tester "+msg);
			msgIn.offer(msg);
		}
	}
}
