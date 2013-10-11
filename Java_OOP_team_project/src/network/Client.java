package network;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	private Socket conn;
	private Scanner scan = new Scanner(System.in);
	public void connect () {
		try {
			conn = new Socket("overpl.us", 10001);
			ObjectOutputStream out;
			ObjectInputStream in;
			in = new ObjectInputStream(conn.getInputStream());
			out = new ObjectOutputStream(conn.getOutputStream());
			while (true) {
				System.out.println(">");
				String input = scan.nextLine();
				if(input.equals(":q")){
					break;
				}
				out.writeObject(input);
				System.out.println((String)in.readObject());
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
