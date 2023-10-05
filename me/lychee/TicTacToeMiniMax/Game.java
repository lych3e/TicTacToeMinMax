package me.lychee.TicTacToeMiniMax;

import me.lychee.TicTacToeMiniMax.Main.Player;

public class Game {
	
	float xWins = 0, oWins = 0;
	
	void simulate(Bot xbot, Bot obot, boolean print) throws Exception {
		Board board = new Board();
		Player current_turn = Player.Player1;
		Player winner = null;
		
		
		
		for (int i = 0; i < 9; i++) {
			if (winner != null) {
				//System.out.println("X: "+xWins+" O: "+oWins);
				break;
			}
			System.out.println();
			int[] choice;
			switch (current_turn) {
				case Player1:
					if (xbot instanceof RealPlayer) choice = ((RealPlayer) xbot).miniMax(board);
					else choice = xbot.miniMax(board, Player.Player1);
					//System.out.println("xbot");
					board.makeMove(current_turn, choice[0], choice[1]);
					current_turn = Player.Player2;
					break;
				case Player2:
					if (obot instanceof RealPlayer) choice = ((RealPlayer) obot).miniMax(board);
					else choice = obot.miniMax(board, Player.Player2);
					//System.out.println("obot");
					board.makeMove(current_turn, choice[0], choice[1]);
					current_turn = Player.Player1;
					break;
				default:
					throw new IllegalArgumentException();
			}
			
			
			winner = board.hasWinner();
			
			if (print) board.draw();
			
			if (winner == null) continue;
			
			switch(winner) {
			case None:
				System.out.println(" Twas a draw");
				xWins += 0.5; oWins += 0.5;
				break;
			case Player1:
				System.out.println(" Player 1 has won.");
				xWins += 1;
				break;
			case Player2:
				System.out.println(" Player 1 has won.");
				oWins += 1;
				break;
			default:
				break;
			}
		}
	}

}
