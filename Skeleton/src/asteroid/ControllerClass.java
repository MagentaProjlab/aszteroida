package asteroid;

import java.util.ArrayList;


public class ControllerClass
{
	private ArrayList<Asteroid> asteroids;
	private ArrayList<Settler> settlers;
	private ArrayList<Robot> robots;
	private SolarWind solarwind;
	private ArrayList<BillOfMaterials> bills;
	
	public ControllerClass() {
		Logger.MethodCall("ControllerClass()");
		asteroids = new ArrayList<Asteroid>();
		settlers = new ArrayList<Settler>();
		robots = new ArrayList<Robot>();
		bills = new ArrayList<BillOfMaterials>();
		InitAsteroids();
		InitSettlers();
		InitBills();
		Logger.MethodReturn("");
	}
	
	public void GameLoop()
	{
		Logger.MethodCall("GameLoop()");
		Logger.MethodReturn("void");
	}
	
	public void InitAsteroids()
	{
		Logger.MethodCall("InitAsteroids()");
		Asteroid a1 = new Asteroid();
		ConnectAsteroids();
		Logger.MethodReturn("void");
	}
	
	public void InitSettlers()
	{
		Logger.MethodCall("InitSettlers()");
		Settler s1 = new Settler();
		Asteroid a1 = GetRandomAsteroid();
		s1.setAsteroid(a1);
		Logger.MethodReturn("void");
	}
	
	public void InitBills() 
	{
		Logger.MethodCall("InitBills()");
		Logger.MethodReturn("void");
	}
	
	public void ConnectAsteroids() 
	{
		Logger.MethodCall("ConnectAsteroids()");
		Logger.MethodReturn("void");
	}
	
	public Asteroid GetRandomAsteroid() 
	{
		Logger.MethodCall("GetRandomAsteroid()");
		Logger.MethodReturn("Asteroid");
		return null;
	}
}
