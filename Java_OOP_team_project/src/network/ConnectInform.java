package network;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ConnectInform {
	private Socket socket;
	private ObjectOutputStream out;
	ConnectInform (Socket so) {
		setSocket(so);
		try {
			setOut(new ObjectOutputStream(so.getOutputStream()));
		} catch (IOException e) {
			System.out.println(this+"\t"+e);
		}
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
