package asteroid.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

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
		asteroids = new ArrayList<Asteroid>();
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
			/*for(int i = 0; i < asteroids.size(); i++)
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
				
			}*/
			
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
	public void GameLoop()
	{
		while(!CheckWin() || !CheckLose())
		{
			//fõ loop, egy nextround-nyi cucc lesz itt
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
						ArrayList<ID> teleList=new ArrayList<ID>();
						teleList.add(asteroids.get(i).getNeighbors().get(j));
						if(teleBill.CheckInventory(teleList)) {
							TeleportGate tg = (TeleportGate)asteroids.get(i).getNeighbors().get(j);
							if(!tg.GetStepped())
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
					ArrayList<ID> teleList=new ArrayList<ID>();
					teleList.add(asteroids.get(i).getNeighbors().get(j));
					if(teleBill.CheckInventory(teleList)) {
						TeleportGate tg = (TeleportGate)asteroids.get(i).getNeighbors().get(j);
						tg.SetStepped(false);
						
						//j--;
					}
				}
			}
		}
		
		if(CheckWin())
		{
			//nyerés
			System.out.println("win");
		}else if(CheckLose())
		{
			//vesztés
			System.out.println("lose");
		}
	}
	/**
	 * A controller inicializalja az aszteroidakat
	 */
	private void InitAsteroids(int numberofsettlers)
	{
		int asteroidcount = 0;
		Random rand = new Random();
		//3x4 aszteroida letrehozasa, kell minden anyagbol legalabb 1 hogy lehessen nyerni
		for(int i = 0; i < 3; i++)
		{
			//random int 0-tol 15-ig
			int crust = rand.nextInt(15 + 1);
			//random int 0-tol crust-ig
			int hole = rand.nextInt(crust + 1);
			asteroidcount++;
			Asteroid jeg = new Asteroid("asteroid" + String.valueOf(asteroidcount), new Ice(), crust, hole);
			asteroids.add(jeg);
			
			crust = rand.nextInt(15 + 1);
			hole = rand.nextInt(crust + 1);
			asteroidcount++;
			Asteroid vas = new Asteroid("asteroid" + String.valueOf(asteroidcount), new Iron(), crust, hole);
			asteroids.add(vas);
			
			crust = rand.nextInt(15 + 1);
			hole = rand.nextInt(crust + 1);
			asteroidcount++;
			Asteroid szen = new Asteroid("asteroid" + String.valueOf(asteroidcount), new Coal(), crust, hole);
			asteroids.add(szen);
			
			crust = rand.nextInt(15 + 1);
			hole = rand.nextInt(crust + 1);
			asteroidcount++;
			Asteroid uran = new Asteroid("asteroid" + String.valueOf(asteroidcount), new Uranium(0), crust, hole);
			asteroids.add(uran);
		}
		
		int numberofasteroids = (int) Math.pow(2, numberofsettlers);
		for(int i = 0; i < numberofasteroids; i++)
		{
			RawMaterial core = null;
			//random int 0-tol 5-ig
			int corenum = rand.nextInt(4 + 1);
			if(corenum == 0)
			{
				core = new Ice();
			}else if(corenum == 1)
			{
				core = new Iron();
			}else if(corenum == 2)
			{
				core = new Coal();
			}else if(corenum == 2)
			{
				core = new Uranium(0);
			}//ha 5 a corenum, akkor ures a mag
			//random int 0-tol 15-ig
			int crust = rand.nextInt(15 + 1);
			//random int 0-tol crust-ig
			int hole = rand.nextInt(crust + 1);
			asteroidcount++;
			Asteroid a = new Asteroid("asteroid" + String.valueOf(asteroidcount), core, crust, hole);
			asteroids.add(a);
		}
		
		ConnectAsteroids();
	}
	/**
	 * A controller inicializalja a telepeseket
	 */
	private void InitSettlers(int numberofsettlers)
	{
		int settlercount = 0;
		Random rand = new Random();
		for(int i = 0; i < numberofsettlers; i++)
		{
			settlercount++;
			Settler s = new Settler("settler" + String.valueOf(settlercount), 0, 0, 0, 0, false);
			//random szam 0 es asteroids.size() kozott
			int asteroidindex = rand.nextInt(asteroids.size());
			asteroids.get(asteroidindex).RegisterBeing(s);
			s.setAsteroid(asteroids.get(asteroidindex));
		}
	}
	/**
	 * A controller osszekoti az aszteroidakat
	 */
	private void ConnectAsteroids() 
	{
		int[][] conmatrix = new int[asteroids.size()][asteroids.size()];
		//Arrays.fill(conmatrix, 0);
		
		//utak generalasa
		for(int i = 0; i < asteroids.size() - 1; i++)
		{
			conmatrix[i][i + 1] = 1;
			conmatrix[i + 1][i] = 1;
		}
		
		//utak osszekotese
		for(int i = 1; i < asteroids.size(); i++)
		{
			for(int j = 0; j < i; j++)
			{
				if(conmatrix[i][j] == 1)
				{
					asteroids.get(i).AddNeighbor(asteroids.get(j));
					asteroids.get(j).AddNeighbor(asteroids.get(i));
				}
			}
		}
	}
	/**
	 * A controller visszaad egy random aszteroidat
	 * @return asteroid : a visszaadando aszteroida - egyenlore null - t ad vissza
	 */
	private Asteroid GetRandomAsteroid() 
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
	
	public void Init(int numberofsettlers)
	{
		InitAsteroids(numberofsettlers);
		InitSettlers(numberofsettlers);
	}
}
