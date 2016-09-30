/**
 * 
 */
package homlessness;

/**
 * @author evanspat
 *
 */
public class State
{
	private String abrv;
	private int total;
	
	public State(String abbriviation, int totalHomeless)
	{
		abrv = abbriviation;
		total = totalHomeless;
	}
	
	public String getAbrv()
	{
		return abrv;
	}
	
	public int getTotal()
	{
		return total;
	}
	
	public String toString()
	{
		return abrv + " has " + total + " homeless.";
	}
}
