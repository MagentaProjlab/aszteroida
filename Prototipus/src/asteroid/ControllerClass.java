package asteroid;

import java.util.ArrayList;


public class ControllerClass
{
	private ArrayList<Asteroid> asteroids;
	private SolarWind solarwind;
	private ArrayList<Bill> bills;
	
	
	
	private Logger log;
	
	/**
	 * A controller konstruktora
	 */
	public ControllerClass(int teszteset)
	{
		log = new Logger(teszteset);
	}
	
	public void Command(String cmd)
	{
		String[] params = cmd.split(" ");
		
		
		if(params[0].equals("Asteroid"))
		{
			String name = params[1];
			String materialtype = params[2];
			int crustthickness = Integer.valueOf(params[3]);
			int holedepth = Integer.valueOf(params[4]);
			
			RawMaterial material = null;
			
			if(materialtype.equals("uranium"))
			{
				int rcount = Integer.valueOf(params[5]);
				material = new Uranium(rcount);
			}else if(materialtype.equals("coal"))
			{
				material = new Coal();
			}else if(materialtype.equals("ice"))
			{
				material = new Ice();
			}else if(materialtype.equals("iron"))
			{
				material = new Iron();
			}
			
			asteroids.add(new Asteroid(name, material, crustthickness, holedepth));
		}
		
		if(params[0].equals("SetNeighbors"))
		{
			//asteroids-ban a két szomszéd nevének megkeresése majd hozzáadása
		}
		
		if(params[0].equals("Settler"))
		{
			String name = params[1];
			String asteroid = params[2];
			String coal = params[3];
			String ice = params[4];
			String iron = params[5];
			String uranium = params[6];
			String teleportgate  = params[7];
			
			//asteroid index kikeresés
			//asteroid registerbeing
			//add coal, add ice, add iron, add uranium
			//add teleport
			
		}
		
		
		//  ezek mind a beingekhez kellenek
		//Move 
		//Drill 
		//Die 
		//NoAction 
		//Mine 
		//ListInventory 
		//BuildTeleport 
		//PutDownTeleport 
		
		if(params[0].equals("Teleport"))
		{
			String name1 = params[1];
			String name2 = params[2];
			String asteroid1 = params[3];
			String asteroid2 = params[4];
			
			//teleportok létrehozása
			//teleportok hozzáadása az aszteroidákhoz
		}
		
		if(params[0].equals("Teleport2"))
		{
			String name = params[1];
			String asteroid = params[2];
			String settler = params[3];
			
			//teleport létrehozása
			//asteroid megkeresése
			//settler megkeresése és teleport hozzáadása
		}
		
		//BuildRobot 
		
		if(params[0].equals("Robot"))
		{
			String name = params[1];
			String asteroid = params[2];
			
			//robot létrehozás adott aszteroidán
		}
		
		if(params[0].equals("Ufo"))
		{
			String name = params[1];
			String asteroid = params[2];
			
			//ufo létrehozás adott aszteroidán
		}
		
		if(params[0].equals("Perihelion"))
		{
			String asteroid = params[1];
			
			//setperihelion(true) adott aszteroidán
		}
		
		if(params[0].equals("SolarWind"))
		{
			String asteroid = params[1];
			
			//solarwind csinálás adott aszteroidán
		}
		
		//CheckEndGame 
		
		if(params[0].equals("ListAsteroids"))
		{
			//for ciklus és aszteroidák kiratása loggerrel
		}
		
		if(params[0].equals("ListBeings"))
		{
			String asteroid = params[1];
			
			//for ciklus és telepesek kiratása loggerrel adott aszteroidán
		}
		
		if(params[0].equals("ListNeighbors"))
		{
			String asteroid = params[1];
			
			//for ciklus és szomszédok kiratása loggerrel adott aszteroidán
		}
		
		if(params[0].equals("NextRound"))
		{
			//összes aszteroidán meghívja a step-et 1x
		}
		
		//LoadMap 
		//SaveMap 
		//LoadTest 
		//Exit
	}
	
	/**
	 * A controller jatekciklusa
	 */
	public void GameLoop()
	{
		
	}
	/**
	 * A controller inicializalja az aszteroidakat
	 */
	public void InitAsteroids()
	{
		
	}
	/**
	 * A controller inicializalja a telepeseket
	 */
	public void InitSettlers()
	{
		
	}
	/**
	 * A controller inicializalja a billeket
	 */
	public void InitBills() 
	{
		
	}
	/**
	 * A controller osszekoti az aszteroidakat
	 */
	public void ConnectAsteroids() 
	{
		
	}
	/**
	 * A controller visszaad egy random aszteroidat
	 * @return asteroid : a visszaadando aszteroida - egyenlore null - t ad vissza
	 */
	public Asteroid GetRandomAsteroid() 
	{
		return null;
	}
}
