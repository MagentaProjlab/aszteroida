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
		sentientbeings = new ArrayList<SentientBeing>();
	}
	/**
	 * Napszel keletkezik
	 * A napszel keletkezeset a felhasznalo donti el
	 * @param asteroids : Azok az aszteroidak, amiket eler a napszel
	 */
	public void solarWind(ArrayList<Asteroid> asteroids)
	{		
		for(Asteroid a : asteroids)
		{
			a.SolarWindDeath();
		}	
	}
}
