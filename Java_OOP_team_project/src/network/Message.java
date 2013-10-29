package network;

public class Message {
	MessageType type = null;
	int x = 0;
	int y = 0;
	ChatStruct message = null;
	Exception error = null;
	public MessageType getType() {
		return type;
	}
	public void setType(MessageType type) {
		this.type = type;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public ChatStruct getMessage() {
		return message;
	}
	public void setMessage(ChatStruct message) {
		this.message = message;
	}
	public Exception getError() {
		return error;
	}
	public void setError(Exception error) {
		this.error = error;
	}
	public String toString () {
		switch (type) {
		case DOT:
			return "("+x+", "+y+")";
		case CHAT:
			return message.toString();
		case ERROR:
			return error.toString();
		default :
			return null;
		}
	}
}
