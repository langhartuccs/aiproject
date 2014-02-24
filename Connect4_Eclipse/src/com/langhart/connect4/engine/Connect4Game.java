package com.langhart.connect4.engine;

/*
 * 
 * To do:
 * -Game event listener interface + registration
 * -Synchronization of game plays
 * -How will auto players know where the pieces are? Don't allow them to
 * modify the state of the game directly
 */

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
			cellsByRow.add(row);
		}
		currentPlayer = 0;//((int)(Math.random()*100))%2;
	}
	
	/**
	 * Returns an array of two Connect4Player objects.
	 * @return
	 */
	public Connect4Player[] getPlayers(){
		Connect4Player[] output = new Connect4Player[4];
		if(players.isEmpty()){
			players.add(new Connect4Player(this, TOKEN.X));
			players.add(new Connect4Player(this, TOKEN.O));
		}
		output[0] = players.get(0);
		output[1] = players.get(1);
		return output;
	}
	
	public void resetGame(){
		initialize();
	}
	
	protected STATUS playMove(int col, Connect4Player player){
		if(player != players.get(currentPlayer))
			throw new Connect4Exception("It is not this player's turn.");
		
		if(col >= WIDTH || col < 0)
			throw new Connect4Exception("Column value "+col+" is out of range.");
		
		Cell cell = null;
		for(int i = HEIGHT-1; i >= 0; i--){
			cell = cellsByCol.get(col).get(i);
			if(cell.token == TOKEN.BLANK)
				break;
			cell = null;
		}
		
		if(cell == null)
			throw new Connect4Exception("Column "+col+" is occupied.");
		
		cell.token = player.token;
		
		currentPlayer = (currentPlayer+1)%2;
		
		updateWinningCondition(col);
		
		return STATUS.OTHER_PLAYERS_TURN;
	}
	
	public String printGrid(){
		StringBuilder output = new StringBuilder();
		
		output.append(repeatChar('-', (2*WIDTH+1)));
		output.append("\n");
		
		for(int i = 0, b = 0; i < HEIGHT; i++){		
			output.append('|');
			for(int n = 0, g = 0; n < WIDTH; n++){
				switch(cellsByRow.get(i).get(n).token){
				case X:
					output.append(TOKEN.X.toString());
					break;
				case O:
					output.append(TOKEN.O.toString());
					break;
				case BLANK:
					output.append(" ");
				}
				output.append('|');
			}
			output.append("\n");
			output.append(repeatChar('-', (2*WIDTH+1)));
			output.append("\n");
		}
		return output.toString();
	}
	
	private void updateWinningCondition(int col){
		
	}
	
	private String repeatChar(char c, int times){
		StringBuilder output = new StringBuilder();
		for(int i = 0; i < times; i++){
			output.append(c);
		}
		return output.toString();
	}
}


