package me.lychee.TicTacToeMiniMax;

import java.util.ArrayList;

import me.lychee.TicTacToeMiniMax.Main.Player;

public class Board {
	Player[][] grid = new Player[3][3];
	int movesMade = 0;
	ArrayList<int[]> moves = new ArrayList<int[]>();
	boolean dummy = false;
	
	public Board() {
		for (int x = 0; x < 3; x++) for (int y = 0; y < 3; y++)  {
			grid[x][y] = Player.None;
		}
		draw();
	}
	
	public Board(boolean dummy) {
		this.dummy = dummy;
		for (int x = 0; x < 3; x++) for (int y = 0; y < 3; y++)  {
			grid[x][y] = Player.None;
		}
	}
	
	@Override
	protected Board clone() {
		Board b = new Board(true);
		
		for (int i = 0; i < 3; i++) {
			System.arraycopy(grid[i], 0, b.grid[i], 0, 3);
		}
		
		b.moves = new ArrayList<int[]>();
		for (int[] move : this.moves) b.moves.add(move.clone());
		return b;
	}
	
	void draw() {
		for (int x = 0; x < 3; x++) for (Integer y = 0; y < 3; y++)  {
			String toDisplay = "";
			
			if (y.equals(0)) toDisplay = toDisplay + "\n";
			
			toDisplay = toDisplay + grid[x][y].symbol;
			
			System.out.print(toDisplay);
		}
	}
	
	int hasEmptySquares() {
		int i = 0;
		
		for (Player[] row : grid) for (Player col : row) {
			if (col.equals(Player.None)) i += 1;
		}
		
		return i;
	}
	
	static Player hasWinner(Player[][] grid, int emptySquares) {
		for (Player[] row : grid) { // columns
			if ((!row[0].equals(Player.None)) && row[0].equals(row[1]) && row[1].equals(row[2])) {
				//System.out.println("column");
				return row[0];
			}
		} 
		for (int i = 0; i < 3; i++) { // rows
			if ((!grid[0][i].equals(Player.None)) && grid[0][i].equals(grid[1][i]) & grid[1][i].equals(grid[2][i])) {
				//System.out.println("row");
				return grid[0][i];
			}
		}
		if ((!grid[1][1].equals(Player.None)) && grid[0][0].equals(grid[1][1]) && grid[1][1].equals(grid[2][2])) return grid[0][0];
		if ((!grid[1][1].equals(Player.None)) && grid[2][0].equals(grid[1][1]) && grid[1][1].equals(grid[0][2])) return grid[2][0];
		
		if (emptySquares == 0) return Player.None;
		return null;
	}
	
	Player hasWinner() {
		return hasWinner(grid, hasEmptySquares());
	}
	
	boolean isSpaceEmpty(int x, int y) {
		if (grid[x][y].equals(Player.None)) return true;
		return false;
	}
	
	int[] lastMove() {
		if (moves.size() == 0) return null;
		return moves.get(moves.size() - 1);
	}
	
	ArrayList<int[]> getLegalMoves() {
		ArrayList<int[]> moves = new ArrayList<int[]>();
		
		for (int x = 0; x < 3; x++) for (int y = 0; y < 3; y++) {
			if (isSpaceEmpty(x, y)) moves.add(new int[] {x, y});
		}
		
		return moves;
	}
	
	Board makeMove(Player player, int x, int y) throws IllegalArgumentException {
		if (!isSpaceEmpty(x,y)) {
			throw new IllegalArgumentException("Space was already occupied!");
		}
		
		grid[x][y] = player;
		moves.add(new int[] {x,y});
		movesMade++;
		
		return this;
	}
}
