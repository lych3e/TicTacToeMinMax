package me.lychee.TicTacToeMiniMax;

import java.util.Scanner;

public class Main {
	
	enum Player {
		None('.'),
		Player1('X'),
		Player2('O');

		public char symbol;
		
		Player(char symbol) {
			this.symbol = symbol;
		}
		
		public static Player valueOf(int ordinal) {
			return values()[ordinal];
		}
	}

	public static void main(String[] args) throws Exception {
		Game game = new Game();

		//game.simulate(new RealPlayer(Player.Player1, new Scanner(System.in)), new Bot(Player.Player2), true);
		game.simulate(new RealPlayer(Player.Player1, new Scanner(System.in)), new Bot(), true);
		//game.simulate(new RealPlayer(Player.Player1, s), new RealPlayer(Player.Player2, s), true);
	}

}
