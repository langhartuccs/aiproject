package com.langhart.connect4;

import com.langhart.connect4.engine.Connect4Game;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int player=0;
		Connect4Game game = new Connect4Game();
		System.out.println(game.printGrid());
		game.getPlayers()[(player++)%2].makeMove(0);
		System.out.println(game.printGrid());
		game.getPlayers()[(player++)%2].makeMove(4);
		System.out.println(game.printGrid());
		game.getPlayers()[(player++)%2].makeMove(0);
		System.out.println(game.printGrid());
		game.getPlayers()[(player++)%2].makeMove(4);
		System.out.println(game.printGrid());
		game.getPlayers()[(player++)%2].makeMove(0);
		System.out.println(game.printGrid());
		game.getPlayers()[(player++)%2].makeMove(4);
		System.out.println(game.printGrid());
		game.getPlayers()[(player++)%2].makeMove(0);
		System.out.println(game.printGrid());
	}

}
