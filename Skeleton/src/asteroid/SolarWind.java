package asteroid;


import java.util.ArrayList;
import java.util.Scanner;

public class SolarWind
{
	private ArrayList<SentientBeing> sentientbeings;
	/**
	 * Napszel konstruktora.
	 */
	public SolarWind()
	{
		Logger.MethodCall("SolarWind()");
		sentientbeings = new ArrayList<SentientBeing>();
		Logger.MethodReturn("");
	}
	/**
	 * Napszel keletkezik
	 * A napszel keletkezeset a felhasznalo donti el
	 * @param asteroids : Azok az aszteroidak, amiket eler a napszel
	 */
	public void solarWind(ArrayList<Asteroid> asteroids)
	{
		Logger.MethodCall("SolarWind()");
		Scanner scanner = new Scanner(System.in);
		Logger.UserQuestion("Is there a solar wind?");
		Boolean feltetel = false;
		String input = scanner.nextLine();
		if(input.equals("1"))
		{
			feltetel = true;
		}else if(input.equals("2"))
		{
			feltetel = false;
		}
		
		
		if(feltetel == true)
		{
			for(Asteroid a : asteroids)
			{
				a.SolarWindDeath();
			}	
		}
		Logger.MethodReturn("void");
	}
}
