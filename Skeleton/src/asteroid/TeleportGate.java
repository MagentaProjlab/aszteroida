package asteroid;


import java.util.Scanner;

public class TeleportGate extends Place 
{
	private TeleportGate sibling;
	private Asteroid asteroid;
	private Settler owner;
	/**
	 * A TeleportGate konstruktora
	 */
	public TeleportGate()
	{
		Logger.MethodCall("TeleportGate()");
		Logger.MethodReturn("");
	}
	/**
	 * A teleportkapu tulajdonosat beallito fuggveny.
	 *
	 * @param s : a teleportkapu - t birtoklo telepes
	 */
	public void SetOwner(Settler s) 
	{
		Logger.MethodCall("SetOwner(Settler s)");
		owner = s;
		Logger.MethodReturn("void");
	}
	/**
	 * A teleportkapu parjat beallito fuggveny
	 * @param sibling : A teleportkapu parja
	 */
	public void SetSibling(TeleportGate sibling) 
	{
		Logger.MethodCall("SetSibling()");
		Logger.MethodReturn("void");
		this.sibling = sibling;
	}
	/**
	 * A teleportkapu aszteroidajat beallito fuggveny
	 * @param asteroid : A teleportkapu aszteroidaja
	 */
	public void SetAsteroid(Asteroid asteroid) 
	{
		Logger.MethodCall("SetAsteroid()");
		Logger.MethodReturn("void");
		this.asteroid = asteroid;
	}
	/**
	 * A teleportkapu aszteroidajat visszaado fuggveny
	 * @return asteroid : A teleportkapu jelenlegi aszteroidaja
	 */
	public Asteroid GetAsteroid() 
	{
		Logger.MethodCall("GetAsteroid()");
		Logger.MethodReturn("Asteroid");
		return this.asteroid;
	}
	/**
	 * A teleportkapu parjat visszaado fuggveny
	 * @return sibling : A teleportkapu parja
	 */
	public TeleportGate GetSibling()
	{
		Logger.MethodCall("GetSibling()");
		Logger.MethodReturn("TeleportGate");
		return this.sibling;
	}
	
	/**
	 * A teleportkapu elvesziti a parjat.
	 * 
	 */
	public void DropOwner()
	{
		Logger.MethodCall("DropOwner()");
		Logger.MethodReturn("void");
	}
	/**
	 * A teleportkapu parjat visszaado fuggveny
	 * @return owner : A teleportkapu - t birtoklo telepes
	 */
	public Settler GetOwner()
	{
		Logger.MethodCall("GetOwner()");
		Logger.MethodReturn("Settler");
		return this.owner;
	}
	/**
	 * A teleportkapu elmozdit egy telepest.
	 */
	public void RegisterBeing(SentientBeing being)
	{
		Logger.MethodCall("RegisterBeing()");
		Logger.MethodReturn("void");
	}
	/**
	 * A teleportkapu felrobban
	 * Ha a teleportkapu aszteroidaja null, akkor telepesnel van
	 * Ha a teleportkapu aszteroidaja nem null, akkor aszteroidan van
	 */
	public void Explode() 
	{
		Logger.MethodCall("Explode()");		
		Asteroid a = this.GetAsteroid();	
		if(a != null)
		{
			asteroid.DropNeighbor(this);
		}else
		{
			owner.DropCarriedTeleport(this);
		}
		
		Asteroid a2 = this.sibling.GetAsteroid();
		if(a2 != null)
		{
			sibling.GetAsteroid().DropNeighbor(sibling);
			sibling.SetAsteroid(null);
		}else
		{
			sibling.GetOwner().DropCarriedTeleport(sibling);
		}
		Logger.MethodReturn("void");
	}
}
