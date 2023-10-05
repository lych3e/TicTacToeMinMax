package me.lychee.TicTacToeMiniMax;

import java.util.Scanner;

import me.lychee.TicTacToeMiniMax.Main.Player;

public class RealPlayer extends Bot {
	
	Scanner scanner;
	
	RealPlayer(Player identity, Scanner scanner) throws Exception {
		
		this.scanner = scanner;
	}
	/*
	int[] selectMove() {
		int x = 0, y = 0;
		
		String s = scanner.nextLine();
		
		String[] inputs = s.split(" ");
		
		x = Integer.parseInt(inputs[0]);
		y = Integer.parseInt(inputs[1]);
		
		return new int[] {x, y};
	}*/
	
	int[] miniMax(Board b) {
		boolean isValidInput = false;
		int x = 0, y = 0;
		while (!isValidInput) {
			String s = scanner.nextLine();
			if (s.length() != 3) {
				System.out.println("Could not interpret input. Please re-evaluate & re-input");
				continue;
			}
			String[] inputs = s.split(" ");
			isValidInput = true;
			try {
				x = 3 - Integer.parseInt(inputs[1]);
				y = Integer.parseInt(inputs[0]) - 1;
				
			} catch (Exception e) {
				isValidInput = false;
				System.out.println("Could not interpret input. Please re-evaluate & re-input");
				continue;
			}
			
			
		} 
		
		return new int[] {x, y};
	}
	

}
