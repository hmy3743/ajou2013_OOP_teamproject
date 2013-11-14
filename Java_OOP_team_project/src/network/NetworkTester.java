package network;

import java.util.Scanner;

public class NetworkTester implements NetCallable {
	private static Scanner scan = new Scanner(System.in);
	public static void main (String args[]) {
		System.out.println("input 1 for server else integer for client");
		int choice = scan.nextInt();
		scan.nextLine();
		Server s;
		Client c;
		if(choice == 1){
			System.out.println("Input the size of room");
			s = new Server(new NetworkTester(), scan.nextInt());
			scan.nextLine();
			while(true){
				s.send(scan.nextLine());
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
	public void pushMessage(Object message) {
		// TODO Auto-generated method stub
		System.out.println(message);
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
