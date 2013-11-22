package network;

import java.io.Serializable;

public interface NetCallable {
	public void pushMessage (Serializable message, int from);
	public void disconnect ();
	public void disconnect (int playerNum);
}
