package network;

public class ChatStruct {
	private String from;
	private String content;
	public ChatStruct (String from, String content) {
		this.from = from;
		this.content = content;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getMsg() {
		return content;
	}
	public void setMsg(String msg) {
		this.content = msg;
	}
	public String toString () {
		return "Message: "+content+", from: "+from;
	}
}
