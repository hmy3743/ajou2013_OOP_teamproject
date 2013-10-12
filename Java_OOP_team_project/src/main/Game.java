package main;

import GameInterface.*;
import Settings.* ;

public class Game {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Settings settings = new Settings() ;
		
		GameInterface game = new GameInterface(settings) ;
		
		System.out.println(settings.getGameTime());
		game.toString() ;
		
	}

}
