/**
 * 
 */
package homlessness;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author evanspat
 *
 */
public class Year
{
	private State[] states;
	private int year;
	
	public Year(String fileName, int yr) throws FileNotFoundException
	{
		this.year = yr;
		states = this.readYear(fileName);
	}

	public State[] readYear(String fileName) throws FileNotFoundException
	{
		State[] table = new State[54];
		Scanner scanner = new Scanner(new File(fileName));
		scanner.useDelimiter(",");
		Scanner lineReader;
		int counter = 0;
		String abrv;
		int tot;
		String temp;
		scanner.nextLine();
		while (scanner.hasNextLine() && counter < 54)
		{
			lineReader = new Scanner(scanner.nextLine());
			lineReader.useDelimiter(",");
			abrv = lineReader.next();
			lineReader.next();
			temp = lineReader.next();
			temp += lineReader.next();
			temp = temp.replaceAll("\"", "");
			tot = Integer.parseInt(temp);
			table[counter] = new State(abrv, tot);
			counter++;
			lineReader.close();
		}
		scanner.close();
		
		int total = 0;
		State high = table[0];
		State low = table[0];
		
		System.out.println("Year: " + year);
		
		for (State element : table)
		{
			System.out.println(element.toString());
			total += element.getTotal();
			if (element.getTotal() > high.getTotal())
			{
				high = element;
			}
			else if (element.getTotal() < low.getTotal())
			{
				low = element;
			}
		}
		
		System.out.println("The total homeless population in " + this.getYear() + " is: " + total);
		System.out.println("The state with the largest homeless population in " + this.getYear() + " is: " + high.getAbrv());
		System.out.println("The state with the smallest homeless population in " + this.getYear() + " is: " + low.getAbrv());
		System.out.println("");
		return table;
	}
	
	public int getYear()
	{
		return year;
	}
	
	public State[] getStates()
	{
		return states;
	}
	
}
