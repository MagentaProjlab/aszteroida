package asteroid;

import java.util.ArrayList;


public class ControllerClass
{
	private ArrayList<Asteroid> asteroids;
	private SolarWind solarwind;
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
		
		
		if(params[0].equals("asteroid"))
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
		}else if(params[0].equals("neighbor"))
		{
			String name1 = params[1];
			String name2 = params[2];
			int index1 = 0, index2 = 0;
			
			for(Asteroid a : asteroids)
			{
				if(a.getName().compareTo(name1) == 0)
				{
					break;
				}
				index1++;
			}
			
			for(Asteroid a : asteroids)
			{
				if(a.getName().compareTo(name2) == 0)
				{
					break;
				}
				index2++;
			}
			
			asteroids.get(index1).AddNeighbor(asteroids.get(index2));
			asteroids.get(index2).AddNeighbor(asteroids.get(index1));
		}else if(params[0].equals("settler"))
		{
			String name = params[1];
			String asteroid = params[2];
			int coal = Integer.valueOf(params[3]);
			int ice = Integer.valueOf(params[4]);
			int iron = Integer.valueOf(params[5]);
			int uranium = Integer.valueOf(params[6]);
			boolean teleportgate  = params[7].compareTo("2") == 0? true : false;
			
			for(Asteroid a : asteroids)
			{
				if(a.getName().compareTo(asteroid) == 0)
				{
					a.RegisterBeing(new Settler(name, coal, ice, iron, uranium, teleportgate));
					break;
				}
			}
		}else if(params[0].equals("teleport"))
		{
			String name1 = params[1];
			String name2 = params[2];
			String asteroid1 = params[3];
			String asteroid2 = params[4];
			
			TeleportGate gate1 = new TeleportGate(null, null, name1);
			TeleportGate gate2 = new TeleportGate(null, null, name2);
			gate1.SetSibling(gate2);
			gate2.SetSibling(gate1);
			
			int index1 = 0, index2 = 0;
			
			for(Asteroid a : asteroids)
			{
				if(a.getName().compareTo(asteroid1) == 0)
				{
					break;
				}
				index1++;
			}
			
			for(Asteroid a : asteroids)
			{
				if(a.getName().compareTo(asteroid2) == 0)
				{
					break;
				}
				index2++;
			}
			
			gate1.SetAsteroid(asteroids.get(index1));
			gate2.SetAsteroid(asteroids.get(index2));
			
			asteroids.get(index1).AddNeighbor(gate2);
			asteroids.get(index2).AddNeighbor(gate1);
		}else if(params[0].equals("teleport2"))
		{
			String name = params[1];
			String asteroid = params[2];
			String settler = params[3];
			
			TeleportGate gate1 = new TeleportGate(null, null, name);
			TeleportGate gate2 = new TeleportGate(null, null, null);
			gate1.SetSibling(gate2);
			gate2.SetSibling(gate1);
			
			int index2 = 0;
			
			for(Asteroid a : asteroids)
			{
				if(a.getName().compareTo(asteroid) == 0)
				{
					a.AddNeighbor(gate1);
					break;
				}
			}
			
			for(Asteroid a : asteroids)
			{
				index2 = 0;
				for(SentientBeing sb : a.getBeings())
				{
					if(sb.getName().compareTo(name) == 0)
					{
						Settler s = (Settler)sb;
						s.AddTeleport(gate2);
						a.getBeings().set(index2, s);
						break;
					}
					index2++;
				}
			}
		}else if(params[0].equals("robot"))
		{
			String name = params[1];
			String asteroid = params[2];
			
			for(Asteroid a : asteroids)
			{
				if(a.getName().compareTo(asteroid) == 0)
				{
					a.RegisterBeing(new Robot(a, name));
					break;
				}
			}
		}else if(params[0].equals("ufo"))
		{
			String name = params[1];
			String asteroid = params[2];
			
			for(Asteroid a : asteroids)
			{
				if(a.getName().compareTo(asteroid) == 0)
				{
					a.RegisterBeing(new Ufo(a, name));
					break;
				}
			}
		}else if(params[0].equals("perihelion"))
		{
			String asteroid = params[1];
			
			for(Asteroid a : asteroids)
			{
				if(a.getName().compareTo(asteroid) == 0)
				{
					a.CheckPerihelionReaction();
					break;
				}
			}
		}else if(params[0].equals("solarwind"))
		{
			String asteroid = params[1];
			
			for(Asteroid a : asteroids)
			{
				if(a.getName().compareTo(asteroid) == 0)
				{
					a.SolarWindDeath();
					break;
				}
			}
		}else if(params[0].equals("listasteroids"))
		{
			for(Asteroid a : asteroids)
			{
				String message = "[Asteroid: " + a.getName() + "]";
				Logger.Message(message);
			}
		}else if(params[0].equals("listbeings"))
		{
			String asteroid = params[1];
			
			for(Asteroid a : asteroids)
			{
				if(a.getName().compareTo(asteroid) == 0)
				{
					for(SentientBeing sb : a.getBeings())
					{
						String message = "[SentientBeing: " + sb.getName() + "]";
						Logger.Message(message);
					}
					break;
				}
			}
		}else if(params[0].equals("listneighbors"))
		{
			String asteroid = params[1];
			
			for(Asteroid a : asteroids)
			{
				if(a.getName().compareTo(asteroid) == 0)
				{
					for(Place p : a.getNeighbors())
					{
						String message = "[Place: " + p.getName() + "]";
						Logger.Message(message);
					}
					break;
				}
			}
		}else if(params[0].equals("nextround"))
		{
			for(Asteroid a : asteroids)
			{
				a.StepBeings();
			}
		}else if(params[0].equals("endgame"))
		{
			
		}
		//LoadTest 
		//Exit
		
		//  ezek mind a beingekhez kellenek
		//Move 
		//Drill 
		//Die 
		//NoAction 
		//Mine 
		//ListInventory 
		//BuildTeleport 
		//PutDownTeleport 
		//BuildRobot 
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
