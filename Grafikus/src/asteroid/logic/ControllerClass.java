package asteroid.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import asteroid.view.View;


public class ControllerClass
{
	private ArrayList<Asteroid> asteroids;
	private SolarWind solarwind;
	private View view;
	private static LoopThread loopthread;
	
	public class LoopThread extends Thread
	{
		private Object gamelock = new Object();
		
		public void WaitLoop()
		{
			synchronized(gamelock)
			{
				try
				{
					gamelock.wait();
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}
		
		public void NotifyLoop()
		{
			synchronized(gamelock)
			{
				gamelock.notify();
			}
		}
		
		public void run()
	    {
	    	GameLoop();
	    }
	}
	
	static public void StartLoop()
	{
		loopthread.start();
	}
	
	static public void WaitLoop()
	{
		loopthread.WaitLoop();
	}
	
	static public void NotifyLoop()
	{
		loopthread.NotifyLoop();
	}
	
	public ControllerClass()
	{
		asteroids = new ArrayList<Asteroid>();
		loopthread = new LoopThread();
		solarwind = new SolarWind();
	}
	
	/**
	 * A controller jatekciklusa
	 */
	public void GameLoop()
	{
		while(!CheckWin() || !CheckLose())
		{
			//fõ loop, egy nextround-nyi cucc lesz itt
			ArrayList<Integer> sw = new ArrayList<Integer>();
			Random rand = new Random();
			for(int i = 0; i < rand.nextInt(asteroids.size() + 1); i++)
			{
				Integer index = rand.nextInt(asteroids.size());
				while(sw.contains(index));
				{
					index = rand.nextInt(asteroids.size());
				}
				sw.add(index);
			}
			ArrayList<Place> places = new ArrayList<Place>();
			for(int i = 0; i < sw.size(); i++)
			{
				places.add(asteroids.get(sw.get(i)));
			}
			if(rand.nextInt(50) == 49)
			{
				solarwind.solarWind(places);
			}
			
			for(int i = 0; i < asteroids.size(); i++)
			{
				
				if(asteroids.get(i).GetPerihelion())
				{
					asteroids.get(i).SetPerihelion(false);
				}else
				{
					if(rand.nextInt(10) == 9)
					{
						asteroids.get(i).SetPerihelion(true);
					}
				}
			}
			
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
			int crust = rand.nextInt(15) + 1;
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
		
		//utak generalasa
		for(int i = 0; i < asteroids.size() - 1; i++)
		{
			conmatrix[i][i + 1] = 1;
			conmatrix[i + 1][i] = 1;
		}
		
		Random rand = new Random();
		int db = rand.nextInt((asteroids.size() * (asteroids.size() - 1) / 2) + 1);
		for(int i = 0; i < db; i++)
		{
			int index1 = rand.nextInt(asteroids.size());
			int index2 = rand.nextInt(asteroids.size());
			conmatrix[index1][index2] = 1;
			conmatrix[index2][index1] = 1;
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
