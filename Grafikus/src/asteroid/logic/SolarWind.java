package asteroid.logic;


import java.util.ArrayList;

public class SolarWind
{
	/**
	 * Napszel konstruktora.
	 */
	public SolarWind()
	{
		
	}
	
	/**
	 * Napszel keletkezik
	 * A napszel keletkezeset a felhasznalo donti el
	 * @param asteroids : Azok a helyek, amiket eler a napszel
	 */
	public void solarWind(ArrayList<Place> places)
	{	
		for(int i = 0; i < places.size(); i++)
		{
			Bill a = new Bill();
			Place p = places.get(i);
			a.AddMaterialToBill(new Asteroid(null, null, 0, 0));
			ArrayList<ID> l = new ArrayList<ID>();
			l.add(p);
			
			if(a.CheckInventory(l) == true)
			{
				Asteroid as = (Asteroid)p;
				as.SolarWindDeath();
			}else
			{
				TeleportGate tg = (TeleportGate)p;
				tg.Malfunction();
			}
		}
	}
}
