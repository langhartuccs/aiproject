package com.langhart.connect4.engine;

import java.util.ArrayList;
import java.util.List;

public class Connect4Game {
	private static final int WIDTH = 7;
	private static final int HEIGHT = 6;
	private List<List<Cell>> cellsByRow;
	private List<List<Cell>> cellsByCol;
	private List<Cell> allCells;
	private List<Connect4Player> players;
	private int currentPlayer = 0; //0 or 1
	
	public Connect4Game(){
		initialize();
	}
	
	private void initialize(){
		players = new ArrayList<Connect4Player>();
		
		allCells = new ArrayList<Cell>(WIDTH * HEIGHT);
		cellsByRow = new ArrayList<List<Cell>>(HEIGHT);
		cellsByCol = new ArrayList<List<Cell>>(WIDTH);
		for(int i = 0; i < WIDTH; i++){
			ArrayList<Cell> col = new ArrayList<Cell>(HEIGHT);
			for(int n = 0; n < HEIGHT; n++){
				Cell cell = new Cell();
				allCells.add(cell);
				col.add(cell);
			}
			cellsByCol.add(col);
		}
		for(int i = 0; i < HEIGHT; i++){
			ArrayList<Cell> row = new ArrayList<Cell>(WIDTH);
			for(int n = 0; n < WIDTH; n++){
				row.add(cellsByCol.get(n).get(i));
			}
		}
		currentPlayer = ((int)(Math.random()*100))%2;
	}
	
	public Connect4Player[] getPlayers(){
		if(players.isEmpty()){
			players.add(new Connect4Player(this, TOKEN.BLACK));
			players.add(new Connect4Player(this, TOKEN.WHITE));
		}
		return (Connect4Player[]) players.toArray();
	}
	
	public void resetGame(){
		initialize();
	}
	
	protected STATUS playMove(int col, Connect4Player player){
		if(player != players.get(currentPlayer))
			throw new Connect4Exception("It is not this player's turn.");
		return STATUS.OTHER_PLAYERS_TURN;
	}
	
	public String printGrid(){
		StringBuilder output = new StringBuilder();
		for(int i = 0; i < HEIGHT; i++){
			for(int n = 0; n < WIDTH; n++){
				switch(cellsByRow.get(i).get(n).token){
				case BLACK:
					output.append("O");
					break;
				case WHITE:
					output.append("X");
					break;
				case BLANK:
					output.append(" ");
				}
			}
			output.append("\n");
		}
		return output.toString();
	}
}

class Cell {
	public TOKEN token = TOKEN.BLANK;
}
