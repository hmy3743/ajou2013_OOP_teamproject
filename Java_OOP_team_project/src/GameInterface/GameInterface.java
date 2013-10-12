package GameInterface;

import Settings.* ;

public class GameInterface {
	
	Settings set ;
	
	public GameInterface(Settings settings) {
		super();
		set = settings;
		set.setGameTime(10);	
		set.setMaximumRoud(10);
	}
	
	

}
