package battleship;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/* @author Celia Heath
 * 10/22/15
 * 
 * Details:
 * Asks user for their name, the board size and the number of bombs and gives the user 10 tries to hit the bombs.
 * Reports the status of the board and how many turns the user has left.
 * Asks the user for coordinates to shoot at and tells the user what happened.
 * Asks the user if they want to play again.
 */

public class Battleship {

	public static void main(String[] args) {

		//declarations:
		Random rand = new Random();
		Scanner scan = new Scanner(System.in);
		int row = 0;
		int column = 0;
		int turns = 9;
		String playagain = "";
		boolean go = true;
		int userrow = 0;
		int usercolumn = 0;
		int userbombs = 0;
		String name = "";

		while(go==true){

			//welcome and ask for board size and number of dots:
			System.out.println("Hello, welcome to dotwars. What is your name?");
			name = scan.next();
			System.out.println("Hi " + name + ", how many rows would you like your board to have?");
			while(true) {
				try {
					userrow = scan.nextInt();
					break;
				} catch(InputMismatchException e) {
					System.out.println("that is not a number, please try again");
					scan = new Scanner(System.in);
				}
			}
			System.out.println("How many columns would you like your board to have?");
			while(true) {
				try {
					usercolumn = scan.nextInt();
					break;
				} catch(InputMismatchException e) {
					System.out.println("that is not a number, please try again");
					scan = new Scanner(System.in);
				}
			}
			System.out.println("How many bombs would you like your board to have?");
			while(true) {
				try {
					userbombs = scan.nextInt();
					break;
				} catch(InputMismatchException e) {
					System.out.println("that is not a number, please try again");
					scan = new Scanner(System.in);
				}
			}
			System.out.println("Okay, " + name + ", you're all set. You have 10 tries to get all the bombs. Good luck!");
			
			//declare board array with requested board size:
			String[][] board = new String[userrow][usercolumn];
			
			//assign board to empty:
			for (int i=0; i<board.length; i++){
				for (int j=0; j<board[0].length; j++){
					board[i][j]="X";
				}
			} //end of for

			//assigning random dots:
			for (int bomb = 0; bomb<userbombs;){
				row = rand.nextInt(userrow);
				column = rand.nextInt(usercolumn);
				if (board[row][column].equals("X")){
					bomb++;
					board[row][column] = "B";
				}
			} //end of for

			while(turns>-1){
				
				//ask for row and column to shoot at:
				System.out.println("Enter the row that you would like to shoot at, " + name + ".");
				while(true) {
					try {
						row = scan.nextInt()-1;
						break;
					} catch(InputMismatchException e) {
						System.out.println("that is not a number, please try again");
						scan = new Scanner(System.in);
					}
				}
				
				System.out.println("Enter the column that you would like to shoot at, " + name + ".");
				while(true) {
					try {
						column = scan.nextInt()-1;
						break;
					} catch(InputMismatchException e) {
						System.out.println("that is not a number, please try again");
						scan = new Scanner(System.in);
					}
				}
				
				//say whether hit or miss:
				if (board[row][column] == "B"){
					System.out.println("You hit! Good job, " + name + ". You have " + turns + " turns left.");
					board[row][column] = "H";
					turns --;
				}
				else{
					System.out.println("Sorry, you missed, " + name + ". You have " + turns + " turns left.");
					turns--;
				}

				//printing the board:
				for (int i=0; i<board.length; i++){
					for (int j=0; j<board[0].length; j++){
						if (board[i][j].equals("X")){
							System.out.print("❓ ");
						}
						else if (board[i][j].equals("B")){
							System.out.print("❓ ");
						}
						else {
							System.out.print("💣 ");
						}
					}
					System.out.println();
				} //end of for

				//game over, play again?
				if (turns == 0){
					System.out.println("Game over. Would you like to play again? (type yes or no)");
					playagain = scan.next();
					if (playagain.equals("yes")){
						go = true;
						turns = 10;
						break;
					}
					else if(playagain.equals("no")){
						System.out.println("Okay, goodbye, " + name + ".");
						go = false;
						break;
					}
					else {
						System.out.println("Sorry, I don't know what you mean.");
					}
				} //end of if

			} //end of while turns

		} //end of while go
		
	} //end main
	
} //end program
