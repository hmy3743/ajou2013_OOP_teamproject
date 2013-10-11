package network;

import java.io.ObjectInputStream;

public class ServerThread extends Thread {
	ObjectInputStream input;
	ServerThread (ObjectInputStream inputFromClinet) {
		input = inputFromClinet;
	}
	public void run () {
		try{
		Object in = null;
		while ((in = input.readObject()) != null) {
			//받아서 부모한테 넘겨주는 코드 작
			in = null;
		}
		}catch(Exception e){
			System.out.println(e);
		}
	}
}
