package UI;

import javax.swing.JFrame;

public class GameInterface extends JFrame {

	private static int WIDTH =  300 ;
	private static int HEIGHT = 200 ;
	
	public GameInterface() {
		super();
		System.out.println("Start Game Interface");
		this.startInterface();
	}
	
	public void startInterface(){
		this.setSize(WIDTH, HEIGHT);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
	
	

}
