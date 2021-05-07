package asteroid.logic;

import java.util.ArrayList;

import asteroid.view.View;


public class ControllerClass
{
	private ArrayList<Asteroid> asteroids;
	private SolarWind solarwind;
	private Logger log;
	private View view;
	
	
	/**
	 * A controller konstruktora
	 */
	public ControllerClass()
	{
		
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
					gate1.SetAsteroid(asteroids.get(index1));
					break;
				}
				index1++;
			}
			
			asteroids.get(index1).AddNeighbor(gate1);
			for(Asteroid a : asteroids)
			{
				if(a.getName().compareTo(asteroid2) == 0)
				{
					gate2.SetAsteroid(asteroids.get(index2));
					break;
				}
				index2++;
			}
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
					gate1.SetAsteroid(a);
					a.AddNeighbor(gate1);
					break;
				}
			}
			
			for(Asteroid a : asteroids)
			{
				index2 = 0;
				for(SentientBeing sb : a.getBeings())
				{
					if(sb.getName().compareTo(settler) == 0)
					{
						Settler s = (Settler)sb;
						s.AddTeleport(gate2);
						gate2.SetOwner(s);
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
					for(Place p:a.getNeighbors()) {
						Bill teleBill=new Bill();
						teleBill.AddMaterialToBill(new TeleportGate(null, null, null));
						ArrayList<ID> teleList=new ArrayList();
						teleList.add(p);
						if(teleBill.CheckInventory(teleList)) {
						      TeleportGate t=(TeleportGate)p;
						      t.Malfunction();
						}
						
					}
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
						String message = sb.getFullName();
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
			for(int i = 0; i < asteroids.size(); i++)
			{
				if(asteroids.get(i).getExploded() == true)
				{
					asteroids.remove(i);
					i--;
				}else
				{
					asteroids.get(i).StepBeings();
					for(int j = 0; j< asteroids.get(i).getNeighbors().size();j++) {
						Bill teleBill=new Bill();
						teleBill.AddMaterialToBill(new TeleportGate(null, null, null));
						ArrayList<ID> teleList=new ArrayList();
						teleList.add(asteroids.get(i).getNeighbors().get(j));
						if(teleBill.CheckInventory(teleList)) {
							TeleportGate tg = (TeleportGate)asteroids.get(i).getNeighbors().get(j);
							if(!tg.getstepped())
								tg.Move();
							
							//j--;
						}
					}
				}
			}
			for(int i = 0; i < asteroids.size(); i++)
			{
				for(int j = 0; j< asteroids.get(i).getNeighbors().size();j++) {
					Bill teleBill=new Bill();
					teleBill.AddMaterialToBill(new TeleportGate(null, null, null));
					ArrayList<ID> teleList=new ArrayList();
					teleList.add(asteroids.get(i).getNeighbors().get(j));
					if(teleBill.CheckInventory(teleList)) {
						TeleportGate tg = (TeleportGate)asteroids.get(i).getNeighbors().get(j);
						tg.setstepped(false);
						
						//j--;
					}
				}
				
			}
			
		}else if(params[0].equals("endgame"))
		{
			/*boolean w = false;
			for(Asteroid a : asteroids)
			{
				ArrayList<ID> l = new ArrayList<ID>();
				Bill win = new Bill();
				win.AddMaterialToBill(new Ice());
				win.AddMaterialToBill(new Iron());
				win.AddMaterialToBill(new Coal());
				win.AddMaterialToBill(new Uranium(0));
				win.AddMaterialToBill(new Ice());
				win.AddMaterialToBill(new Iron());
				win.AddMaterialToBill(new Coal());
				win.AddMaterialToBill(new Uranium(0));
				win.AddMaterialToBill(new Ice());
				win.AddMaterialToBill(new Iron());
				win.AddMaterialToBill(new Coal());
				win.AddMaterialToBill(new Uranium(0));
				
				for(SentientBeing sb : a.getBeings())
				{
					Bill b = new Bill();
					b.AddMaterialToBill(new Settler(null, 0, 0, 0, 0, false));
					ArrayList<ID> s = new ArrayList<ID>();
					s.add(sb);
					if(b.CheckInventory(s) == true)
					{
						Settler settler = (Settler)sb;
						for(RawMaterial r : settler.getInventory())
						{
							l.add(r);
						}
					}
				}
				
				if(win.CheckInventory(l) == true)
				{
					w = true;
					break;
				}
			}
			
			if(w)
			{
				Logger.Message("Congrats");
				return;
			}
			
			ArrayList<ID> lose = new ArrayList<ID>();
			Bill l = new Bill();
			l.AddMaterialToBill(new Ice());
			l.AddMaterialToBill(new Iron());
			l.AddMaterialToBill(new Coal());
			l.AddMaterialToBill(new Uranium(0));
			l.AddMaterialToBill(new Ice());
			l.AddMaterialToBill(new Iron());
			l.AddMaterialToBill(new Coal());
			l.AddMaterialToBill(new Uranium(0));
			l.AddMaterialToBill(new Ice());
			l.AddMaterialToBill(new Iron());
			l.AddMaterialToBill(new Coal());
			l.AddMaterialToBill(new Uranium(0));
			for(Asteroid a : asteroids)
			{
				if(a.GetMaterial() != null)
				{
					lose.add(a.GetMaterial());
				}
				
				for(SentientBeing sb : a.getBeings())
				{
					Bill b = new Bill();
					b.AddMaterialToBill(new Settler(null, 0, 0, 0, 0, false));
					ArrayList<ID> s = new ArrayList<ID>();
					s.add(sb);
					if(b.CheckInventory(s) == true)
					{
						Settler settler = (Settler)sb;
						for(RawMaterial r : settler.getInventory())
						{
							lose.add(r);
						}
					}
				}
			}
			
			if(l.CheckInventory(lose) == true)
			{
				return;
			}else
			{
				Logger.Message("Game over");
				return;
			}*/
			
		}
		//LoadTest 
		//Exit 
	}
	
	/**
	 * A controller jatekciklusa
	 */
	public void GameLoop(int numberofsettlers)
	{
		InitAsteroids();
		InitSettlers();
		
		while(!CheckWin() || !CheckLose())
		{
			//fõ loop, egy nextround-nyi cucc lesz itt
		}
		
		if(CheckWin())
		{
			//nyerés
		}else if(CheckLose())
		{
			//vesztés
		}
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
	
	public void SetView(View v)
	{
		view = v;
	}
	
	private boolean CheckLose()
	{
		ArrayList<ID> lose = new ArrayList<ID>();
		Bill l = new Bill();
		l.AddMaterialToBill(new Ice());
		l.AddMaterialToBill(new Iron());
		l.AddMaterialToBill(new Coal());
		l.AddMaterialToBill(new Uranium(0));
		l.AddMaterialToBill(new Ice());
		l.AddMaterialToBill(new Iron());
		l.AddMaterialToBill(new Coal());
		l.AddMaterialToBill(new Uranium(0));
		l.AddMaterialToBill(new Ice());
		l.AddMaterialToBill(new Iron());
		l.AddMaterialToBill(new Coal());
		l.AddMaterialToBill(new Uranium(0));
		for(Asteroid a : asteroids)
		{
			if(a.GetMaterial() != null)
			{
				lose.add(a.GetMaterial());
			}
			
			for(SentientBeing sb : a.getBeings())
			{
				Bill b = new Bill();
				b.AddMaterialToBill(new Settler(null, 0, 0, 0, 0, false));
				ArrayList<ID> s = new ArrayList<ID>();
				s.add(sb);
				if(b.CheckInventory(s) == true)
				{
					Settler settler = (Settler)sb;
					for(RawMaterial r : settler.getInventory())
					{
						lose.add(r);
					}
				}
			}
		}
		
		if(l.CheckInventory(lose) == true)
		{
			return false;
		}else
		{
			return true;
		}
	}
	
	private boolean CheckWin()
	{
		boolean w = false;
		for(Asteroid a : asteroids)
		{
			ArrayList<ID> l = new ArrayList<ID>();
			Bill win = new Bill();
			win.AddMaterialToBill(new Ice());
			win.AddMaterialToBill(new Iron());
			win.AddMaterialToBill(new Coal());
			win.AddMaterialToBill(new Uranium(0));
			win.AddMaterialToBill(new Ice());
			win.AddMaterialToBill(new Iron());
			win.AddMaterialToBill(new Coal());
			win.AddMaterialToBill(new Uranium(0));
			win.AddMaterialToBill(new Ice());
			win.AddMaterialToBill(new Iron());
			win.AddMaterialToBill(new Coal());
			win.AddMaterialToBill(new Uranium(0));
			
			for(SentientBeing sb : a.getBeings())
			{
				Bill b = new Bill();
				b.AddMaterialToBill(new Settler(null, 0, 0, 0, 0, false));
				ArrayList<ID> s = new ArrayList<ID>();
				s.add(sb);
				if(b.CheckInventory(s) == true)
				{
					Settler settler = (Settler)sb;
					for(RawMaterial r : settler.getInventory())
					{
						l.add(r);
					}
				}
			}
			
			if(win.CheckInventory(l) == true)
			{
				w = true;
				break;
			}
		}
		
		if(w)
		{
			return true;
		}else
		{
			return false;
		}
	}
}
