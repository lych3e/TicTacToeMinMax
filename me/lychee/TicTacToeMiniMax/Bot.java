package me.lychee.TicTacToeMiniMax;

import java.util.ArrayList;

import me.lychee.TicTacToeMiniMax.Main.Player;

public class Bot {
	
	static Player getOppositeIdentity(Player identity) throws Exception {
		switch (identity) {
		case Player1:
			return Player.Player2;
		case Player2:
			return Player.Player1;
		default:
			throw new Exception("Player cannot be None or null");
		}
	}
	
	int[] miniMax(Board b, Player identity) {
		return getMinimaxInput(identity.equals(Player.Player2), b);
	}
	private int[] getMinimaxInput(boolean player, Board board) { // the ai is true, player is false
		int[] i = getMinimaxInput(0, board.clone(), player);
		//System.out.println(i[1]+" "+count); // for debug [weight, num of simulated boards]
		
		return new int[] {i[3], i[4]};
	}
	private int[] getMinimaxInput(int depth, Board board, boolean OP) {
		Player winner =  board.hasWinner();
		if (winner != null) {
			Integer win = winner.equals(OP ? Player.Player2 : Player.Player1) ? 1 : 2;
			
			win = winner.equals(Player.None) ? 0 : win;
			
			//System.out.println(win);
			
			// If it's a draw
			if (win == 0) return new int[] {depth, 0, 0, -1, -1};
			
			// If player 1 wins AND OP is player 1 (aka if OP is player 1 AND the winner)
			// win condition
			else if (win == 1 && OP) return new int[] {depth, 0, 1, -1, -1};
			
			// If player 2 wins (aka if the winner is player 2)
			// lose condition
			else return new int[] {depth, -1, 0, -1, -1};
		}
		
		//If the depth is too big (we dont want it to take ages)
		if (depth > 12) return new int[] {0, 0, -1, -1};
		
		// list of moves & their worth
		ArrayList<int[]> moves = new ArrayList<int[]>();
		//System.out.println("fake\n"+state.toString());
		//Creates a set of possible moves for the next layer of creation
		for (int[] move : board.getLegalMoves()) {
			int[] i = getMinimaxInput(
					depth + 1, board.clone().makeMove(
							Player.valueOf((depth % 2 == 0 ? OP : !OP) ? 1 : 2), 
									move[0], move[1]), OP);
			moves.add(new int[] {i[0], i[1], i[2], move[0], move[1]});
		}
		//weight /= moves.size();
		//System.out.println(weight);
		// Returns the best possible move
		int bestID = findLowest(moves.toArray(new int[moves.size()][]));
		int[] best = moves.get(bestID);
		return best;
	}
	
	private int findLowest(int[][] list) {
		int lowestID = 0;
		
		for (int i = 0; i < list.length; i++) {
			if (list[i][1] < list[lowestID][1]) lowestID = i;
			else if (list[i][1] ==  list[lowestID][1] &&
					list[i][0] < list[lowestID][0]) lowestID = i;
		}
		
		//System.out.println(list[highestID]);
		
		return lowestID;
	}
}