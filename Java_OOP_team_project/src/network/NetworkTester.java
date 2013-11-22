package network;

import java.io.Serializable;
import java.util.Scanner;

public class NetworkTester implements NetCallable {
	private static Server s = null;
	private static Client c = null;
	private static Scanner scan = new Scanner(System.in);
	public static void main (String args[]) {
		System.out.println("input 1 for server else integer for client");
		int choice = scan.nextInt();
		scan.nextLine();
		if(choice == 1){
			System.out.println("Input the size of room");
			s = new Server(new NetworkTester(), scan.nextInt());
			scan.nextLine();
			while(true){
				s.send(scan.nextLine()/*, scan.nextInt()*/);
			}
		}
		else{
			System.out.println("input target server ip or domain");
			c = new Client(new NetworkTester(), scan.next());
			scan.nextLine();
			while(true){
				c.send(scan.nextLine());
			}
		}
	}
	@Override
	public void pushMessage(Serializable message, int from) {
		// TODO Auto-generated method stub
		if (from == 0)
			System.out.println(message + "from = " + from);
		else{
			System.out.println(message + "from = " + from);
			s.send(message);
		}
	}
	@Override
	public void disconnect() {
		// TODO Auto-generated method stub
		System.out.println("Disconnect with server");
	}
	@Override
	public void disconnect(int playerNum) {
		// TODO Auto-generated method stub
		System.out.println("Disconnect with "+playerNum);
	}
}
