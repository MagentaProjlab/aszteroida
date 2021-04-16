package asteroid;

import java.util.ArrayList;


public class ControllerClass
{
	private ArrayList<Asteroid> asteroids;
	private ArrayList<Settler> settlers;
	private ArrayList<Robot> robots;
	private SolarWind solarwind;
	private ArrayList<BillOfMaterials> bills;
	/**
	 * A controller konstruktora
	 */
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
	/**
	 * A controller jatekciklusa
	 */
	public void GameLoop()
	{
		Logger.MethodCall("GameLoop()");
		Logger.MethodReturn("void");
	}
	/**
	 * A controller inicializalja az aszteroidakat
	 */
	public void InitAsteroids()
	{
		Logger.MethodCall("InitAsteroids()");
		Asteroid a1 = new Asteroid();
		ConnectAsteroids();
		Logger.MethodReturn("void");
	}
	/**
	 * A controller inicializalja a telepeseket
	 */
	public void InitSettlers()
	{
		Logger.MethodCall("InitSettlers()");
		Settler s1 = new Settler();
		Asteroid a1 = GetRandomAsteroid();
		s1.setAsteroid(a1);
		Logger.MethodReturn("void");
	}
	/**
	 * A controller inicializalja a billeket
	 */
	public void InitBills() 
	{
		Logger.MethodCall("InitBills()");
		Logger.MethodReturn("void");
	}
	/**
	 * A controller osszekoti az aszteroidakat
	 */
	public void ConnectAsteroids() 
	{
		Logger.MethodCall("ConnectAsteroids()");
		Logger.MethodReturn("void");
	}
	/**
	 * A controller visszaad egy random aszteroidat
	 * @return asteroid : a visszaadando aszteroida - egyenlore null - t ad vissza
	 */
	public Asteroid GetRandomAsteroid() 
	{
		Logger.MethodCall("GetRandomAsteroid()");
		Logger.MethodReturn("Asteroid");
		return null;
	}
}
