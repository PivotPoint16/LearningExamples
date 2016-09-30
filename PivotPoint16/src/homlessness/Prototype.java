/**
 * 
 */

package homlessness;

import java.io.FileNotFoundException;

/**
 * @author evanspat
 *
 */
public class Prototype
{
	private Year[] years;
	
	/**
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException
	{
		new Prototype(args);
	}

	public Prototype(String[] args) throws FileNotFoundException
	{
		years = new Year[args.length];
		for (int i = 0; i < args.length; i++)
		{
			int year = Integer.parseInt(args[i].substring(0,4));
			years[i] = new Year(args[i], year);
		}
	}
}
