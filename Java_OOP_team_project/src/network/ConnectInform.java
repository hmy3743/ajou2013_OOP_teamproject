package network;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ConnectInform {
	private Socket socket;
	private ObjectOutputStream out;
	public ConnectInform(Socket client) {
		setSocket(client);
		try {
			setOut(new ObjectOutputStream(client.getOutputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("IO Exception on ConnectInform init : "+e);
		}
		// TODO Auto-generated constructor stub
	}
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	public ObjectOutputStream getOut() {
		return out;
	}
	public void setOut(ObjectOutputStream out) {
		this.out = out;
	}

}
