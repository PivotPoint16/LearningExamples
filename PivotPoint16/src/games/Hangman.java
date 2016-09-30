package games;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 * The purpose of this program is to have a user play hangman against
 * the computer
 * 
 * @author evanspat
 * Date Created 7/12/2016
 */
public class Hangman {

	public static void main(String[] args) throws FileNotFoundException
	{
		//Get the list of words, which is a String array, using the read() method.
		String[] words = read();
		
		//Initialize the number of missed guesses, an int.
		//The max number of missed will be 6
		int missed = 0;
		
		//Create a new Random()
		Random random = new Random();
		
		//Pick a random number between 0 and 144, remember to store it as a variable.
		int wordNum = random.nextInt(144);
		
		//Create a char array to hold the word we will be using.
		char[] word = words[wordNum].toCharArray();
		
		//Create a char array to hold the correct guesses.
		char[] correct = new char[words[wordNum].length()];
		
		//Set the guesses to _
		int i = 0;
		while (i < correct.length)
		{
			correct[i] = '_';
			i++;
		}
		
		//Print out the welcome and directions
		System.out.println("WELCOME TO HANGMAN");
		System.out.println("The words you will be guessing are animals.");
		System.out.println("Please only guess one letter at a time.");
		
		//Print out the board, using the draw(int missed) method.
		System.out.println(draw(missed));
		
		//Print out the blanks for the word, using the printWord(char[] word, char[] correct) method.
		System.out.println(printWord(word, correct));
		
		//Create a new Scanner() to read the users guesses, we want it to read from the keyboard(System.in)
		Scanner input = new Scanner(System.in);
		
		//Create an empty String to represent the users guess.
		String guess = "";
		
		//Create the game loop, we want it to continue as long as the word is not answered and they have less than 6 missed.
		//To do this use the answered(char[] word, char[] correct) method, which returns a boolean, and see if missed < 6.
		while (missed < 6 && !answered(word, correct))
		{
			//Prompt the user to enter their guess.
			System.out.println("Please enter the letter you would like to guess.");
			
			//Use the scanner to grab their guess.
			guess = input.nextLine();
			
			//Lets make sure they guessed a lower case letter.
			guess = guess.toLowerCase();
			
			//Check to see if their guess is in the word, using the reveal(char x, char[] correct, char[] word) method.
			if(reveal(guess.charAt(0), correct, word))
			{
				//Guess is correct.
				System.out.println("Correct Guess!");
			}
			else
			{
				//Guess is incorrect.
				System.out.println("Incorrect Guess.");
				
				//Add one to missed.
				missed++;
			}
			//Print the board again.
			System.out.println(draw(missed));
			
			//Print the word again.
			System.out.println(printWord(word, correct));
			
			//Check to see if they have won.
			if (answered(word, correct))
			{
				//Print Winning Statement.
				System.out.println("Congratulations! You Won!");
			}
			//If they didn't win lets check to see if they have more guesses.
			else if (missed >= 6)
			{
				//Print losing statement.
				System.out.println("I'm sorry you lost.");
				System.out.println("The word was: " + printWord(word, word));
			}
		}
		//Closes the Scanner
		input.close();
	}

	/**
	 * Reads the list of words in animals.txt, and writes them into an array for the game
	 * @return the array of possible words
	 * @throws FileNotFoundException
	 */
	public static String[] read() throws FileNotFoundException
	{
		//A file containing the possible words
		File f = new File("animals.txt");
		//The array of possible words
		String[] words = new String[144];
		Scanner reader = new Scanner(f);
		int x = 0;
		while (reader.hasNextLine())
		{
			words[x] = reader.nextLine();//Add words to the array
			x++;
		}
		reader.close();
		return words;
	}
	
	/**
	 * Draws the correct board, given then the number of misses
	 * @param num the current number of missed guesses
	 * @return the String representing the number of tries
	 */
	public static String draw(int num)
	{
		String ans = (" ----|\n"
				     +" |   |\n"
				     +"     |\n"
				     +"     |\n"
				     +"     |\n"
				     +"     |\n"
				     +"    /|\\\n"
				     +"-------");
		if (num == 1)
			ans = (" ----|\n"
				  +" |   |\n"
				  +" O   |\n"
				  +"     |\n"
				  +"     |\n"
				  +"     |\n"
				  +"    /|\\\n"
				  +"-------");
		if (num == 2)
			ans = (" ----|\n"
				  +" |   |\n"
				  +" O   |\n"
				  +" |   |\n"
				  +"     |\n"
				  +"     |\n"
				  +"    /|\\\n"
				  +"-------");
		if (num == 3)
			ans = (" ----|\n"
				  +" |   |\n"
				  +" O   |\n"
				  +"-|   |\n"
				  +"     |\n"
				  +"     |\n"
				  +"    /|\\\n"
				  +"-------");
		if (num == 4)
			ans = (" ----|\n"
				  +" |   |\n"
				  +" O   |\n"
				  +"-|-  |\n"
				  +"     |\n"
				  +"     |\n"
				  +"    /|\\\n"
				  +"-------");
		if (num == 5)
			ans = (" ----|\n"
				  +" |   |\n"
				  +" O   |\n"
				  +"-|-  |\n"
				  +"/    |\n"
				  +"     |\n"
				  +"    /|\\\n"
				  +"-------");
		if (num == 6)
			ans = (" ----|\n"
				  +" |   |\n"
				  +" O   |\n"
				  +"-|-  |\n"
				  +"/ \\  |\n"
				  +"     |\n"
				  +"    /|\\\n"
				  +"-------\n");
		return ans;
	}
	
	/**
	 * Displays the hidden word, with letters that have been guessed displayed
	 * @param word the correct word
	 * @param correct the correct guesses
	 * @return a String representation of the word with '_' for missing letters and extra spacing for ' '
	 */
	public static String printWord(char[] word, char[] correct)
	{
		String ans = "";
		for (int i = 0; i < word.length; i ++)
		{
			if (word[i] == ' ')
			{
				ans = ans + "   ";
			}
			else if (correct[i] == '_')
			{
				ans = ans + " _";
			}
			else
			{
				ans = ans + " " + correct[i];
			}
		}
		return ans;
	}
	
	/**
	 * Determines if the word has been completely guessed
	 * @param word the char array with the correct letters
	 * @param guessed the char array with the guessed letters
	 * @return true if the word has been completely guessed, false otherwise
	 */
	public static boolean answered(char[] word, char[] correct)
	{
		for (int i = 0; i < word.length; i++)
		{
			if (word[i] != ' ' && word[i] != correct[i])
			{
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Reveals the given char in the word if found
	 * @param x the char to parse with
	 * @param correct the array of correct guesses so far
	 * @param word the correct spelling of the word
	 * @return True if the word is found, false otherwise
	 */
	public static boolean reveal(char x, char[] correct, char[] word)
	{
		boolean ans = false;
		for (int i = 0; i < word.length; i++)
		{
			if (x == word[i])
			{
				correct[i] = x;
				ans = true;
			}
		}
		return ans;
	}
}
