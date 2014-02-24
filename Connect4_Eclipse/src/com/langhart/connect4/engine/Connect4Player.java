package com.langhart.connect4.engine;

public class Connect4Player {
	private final Connect4Game parentGame;
	public final TOKEN token;
	
	protected Connect4Player(Connect4Game game, TOKEN tokenType){
		parentGame = game;
		token = tokenType;
	}
	
	public STATUS makeMove(int col){
		return parentGame.playMove(col, this);
	}
}
