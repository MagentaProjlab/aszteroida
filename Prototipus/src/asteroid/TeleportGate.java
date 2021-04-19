package asteroid;
public class TeleportGate extends Place
{
	private TeleportGate sibling;
	private Asteroid asteroid;
	private Settler owner;
	private boolean Malfunctioning; 
	String id;
	/**
	 * A TeleportGate konstruktora
	 */
	public TeleportGate(Asteroid a, Settler o, String name)
	{
		this.asteroid = a;
		this.owner = o;
		this.id = name;
		this.Malfunctioning = false;
	}
	
	public void SetName(String n) {
		id = n;
	}

	public String getName() {
		return id;
	}
	/**
	 * A teleportkapu tulajdonosat beallito fuggveny.
	 *
	 * @param s : a teleportkapu - t birtoklo telepes
	 */
	public void SetOwner(Settler s) 
	{
		owner = s;
	}
	/**
	 * A teleportkapu parjat beallito fuggveny
	 * @param sibling : A teleportkapu parja
	 */
	public void SetSibling(TeleportGate sibling) 
	{
		this.sibling = sibling;
	}
	/**
	 * A teleportkapu aszteroidajat beallito fuggveny
	 * @param asteroid : A teleportkapu aszteroidaja
	 */
	public void SetAsteroid(Asteroid asteroid) 
	{
		this.asteroid = asteroid;
		asteroid.AddNeighbor(this);
	}
	/**
	 * A teleportkapu aszteroidajat visszaado fuggveny
	 * @return asteroid : A teleportkapu jelenlegi aszteroidaja
	 */
	public Asteroid GetAsteroid() 
	{
		return this.asteroid;
	}
	/**
	 * A teleportkapu parjat visszaado fuggveny
	 * @return sibling : A teleportkapu parja
	 */
	public TeleportGate GetSibling()
	{
		return this.sibling;
	}
	
	/**
	 * A teleportkapu elvesziti a parjat.
	 * 
	 */
	public void DropOwner()
	{
		this.owner = null;
	}
	/**
	 * A teleportkapu parjat visszaado fuggveny
	 * @return owner : A teleportkapu - t birtoklo telepes
	 */
	public Settler GetOwner()
	{
		return this.owner;
	}
	/**
	 * A teleportkapu elmozdit egy telepest.
	 */
	public void RegisterBeing(SentientBeing being)
	{
		being.setAsteroid(this.sibling.GetAsteroid());
	}
	/**
	 * A teleportkapu felrobban
	 * Ha a teleportkapu aszteroidaja null, akkor telepesnel van
	 * Ha a teleportkapu aszteroidaja nem null, akkor aszteroidan van
	 */
	public void Explode() 
	{		
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
	}

	public String GetUniqueID() {
		return new String("teleportgate");
	}
	/**
	 * Beallitja a malfunction valtozot
	 */
	public void Malfunction() {
		this.Malfunctioning = true;
	}
	/**
	 * lekerdezi a malfunction valtozot
	 * @return Malfunctioning
	 */
	public boolean GetMalfunction() {
		return this.Malfunctioning;
	}
}
