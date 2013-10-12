package GameInterface;

import Settings.* ;

public class GameInterface {
	
	Settings set ;
	
	public GameInterface(Settings settings) {
		super();
		set = settings;
		set.setGameTime(10);	
		set.setMaximumRoud(10);
		set.setMaximumPoint(10);
		}


	public Settings getSet() {
		return set;
	}

	public void setSet(Settings set) {
		this.set = set;
	}

}
