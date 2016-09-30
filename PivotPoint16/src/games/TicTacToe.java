package games;
import java.util.Scanner;
import java.util.Random;

/**
 * The purpose of this program is to create a Tic-Tac-Toe game the user
 * will play against the computer.
 * 
 * @author evanspat
 * Date Created 7/12/2016
 */
public class TicTacToe
{
	
	
	public static void main(String[] args)
	{
		//Initialize the board, we will use an int array, it needs 9 spots
		int[] board = new int[9]; 
		//Represents the tic tac toe game board
		//0 = empty		1 = X		4 = Y 
		
		//Print out the Welcome Message and Directions
		System.out.println("WELCOME TO TIC TAC TOE");
		System.out.println("**********************");
		System.out.println("Today you will be playing against the computer, you will be X, the computer will be O.");
		System.out.println("There are 1,145 possible game combinations, so best of luck!");
		System.out.println();
		
		//Print out the inital board
		System.out.println("   |   |   \n"
						  +" 0 | 1 | 2 \n"
						  +"___|___|___\n"
						  +"   |   |   \n"
						  +" 3 | 4 | 5 \n"
						  +"___|___|___\n"
						  +"   |   |   \n"
						  +" 6 | 7 | 8 \n"
						  +"   |   |   \n");
		
		System.out.println("Please remember these numbered locations");

		
		//Make a boolean for determining if the game is over
		//The game will start with the game not being over so gameOver should be false
		boolean gameOver = false;
		
		//Create a new Scanner() that reads from the keyboard(System.in)
		Scanner input = new Scanner(System.in);
		
		//Create a new Random() to be used to make the computer's choices
		Random random = new Random();
		
		//Main game loop
		while (!gameOver)
		{
			
			//THE USERS TURN****************************************************************************************************************************
			
			//Print prompt to enter a place to play
			System.out.println("Please enter the number of the location you would like to place an X");
			
			//Use the scanner to grab the next integer that is entered
			int userLocation = input.nextInt();
			
			//Check to see if that space in the board[] is empty
			//We will need to repeat this incase they continue to pick an illegal location
			while (board[userLocation] != 0)
			{
				//Print prompt to enter a place to play
				System.out.println("Please enter the number of the location you would like to place an X");
				
				//Use the scanner to grab the next integer that is entered
				userLocation = input.nextInt();
			}
			//Insert an X into that location in the board[], remember X = 1
			board[userLocation] = 1;
			
			//Print out the board
			System.out.println(draw(board));
			
			//Check to see if you have won
			if (win(board) == 1)
			{
				//User wins
				System.out.println("Congratulations you won!");
				System.exit(0);
			}
			else if (win(board) == 3)
			{
				//It's a tie
				System.out.println("It's a tie!");
				
				//Exit the game
				System.exit(0);
			}
			
			//THE COMPUTERS TURN************************************************************************************************************************
			
			//Create a new random integer between 0 and 8
			int computerLocation = random.nextInt(9);
			
			//Check to see if that space in the board[] is empty
			//We will need to repeat this incase the computer continues to pick an illegal location
			while (board[computerLocation] != 0)
			{			
				//Use the scanner to grab the next integer that is entered
				computerLocation = random.nextInt(9);
			}
			//Insert an Y into that location in the board[], remember Y = 4
			board[computerLocation] = 4;
			
			//Tell the user where the computer played
			System.out.println("The computer played at space: " + computerLocation);
			
			//Print out the board
			System.out.println(draw(board));
			
			//Check to see if you have won
			if (win(board) == 2)
			{
				//The computer won
				System.out.println("I'm sorry you lost");
				
				//Exit the game
				System.exit(0);
			}
			else if (win(board) == 3)
			{
				//It's a tie
				System.out.println("It's a tie!");
				
				//Exit the game
				System.exit(0);
			}
		}
		input.close();
	}

	/**
	 * Converts the int array board[] into a string to be printed
	 * @param board the game board
	 * @return the string representation of the board
	 */
	public static String draw(int[] board)
	{
		char[] spots = new char[9];
		int i = 0;
		for (int spot : board)
		{
			if (spot == 0)
			{
				spots[i] = ' ';
			}
			else if (spot == 1)
			{
				spots[i] = 'X';
			}
			else if (spot == 4)
			{
				spots[i] = 'O';
			}
			i++;
		}
		StringBuilder ans = new StringBuilder("   |   |   \n"
				  							 +"  |  |  \n"
				  							 +"___|___|___\n"
				  							 +"   |   |   \n"
				  							 +"  |  |  \n"
				  							 +"___|___|___\n"
				  							 +"   |   |   \n"
				  							 +"  |  |  \n"
				  							 +"   |   |   \n");
		int x = 13;
		i = 0;
		for(char spot : spots)
		{
			ans.insert(x, spot);
			i++;
			if (i % 3 == 0)
			{
				x += 28;
			}
			else
			{
				x += 4;
			}
		}
		return(ans.toString());
	}
	
	/**
	 * Determines the ending conditions of the board, tie, win or lose.
	 * @param board the game board
	 * @return 0 if no one has won, 1 if the user has won, 2 if the computer has won, and 3 if there is a tie
	 */
	public static int win(int[] board)
	{
		if (board[0] + board[1] + board[2] == 3)
		{
			return 1;
		}
		else if (board[3] + board[4] + board[5] == 3)
		{
			return 1;
		}
		else if (board[6] + board[7] + board[8] == 3)
		{
			return 1;
		}
		else if (board[0] + board[3] + board[6] == 3)
		{
			return 1;
		}
		else if (board[1] + board[4] + board[7] == 3)
		{
			return 1;
		}
		else if (board[2] + board[5] + board[8] == 3)
		{
			return 1;
		}
		else if (board[0] + board[4] + board[8] == 3)
		{
			return 1;
		}
		else if (board[2] + board[4] + board[6] == 3)
		{
			return 1;
		}
		else if (board[0] + board[1] + board[2] == 12)
		{
			return 2;
		}
		else if (board[3] + board[4] + board[5] == 12)
		{
			return 2;
		}
		else if (board[6] + board[7] + board[8] == 12)
		{
			return 2;
		}
		else if (board[0] + board[3] + board[6] == 12)
		{
			return 2;
		}
		else if (board[1] + board[4] + board[7] == 12)
		{
			return 2;
		}
		else if (board[2] + board[5] + board[8] == 12)
		{
			return 2;
		}
		else if (board[0] + board[4] + board[8] == 12)
		{
			return 2;
		}
		else if (board[2] + board[4] + board[6] == 12)
		{
			return 2;
		}
		for (int spot : board)
		{
			if (spot == 0)
			{
				return 0;
			}
		}
		return 3;
	}
}
