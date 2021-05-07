package asteroid.logic;

import java.util.ArrayList;

public class TeleportGate extends Place
{
	private TeleportGate sibling;
	private Asteroid asteroid;
	private Settler owner;
	private boolean malfunctioning; 
	private String id;
	private boolean stepped;
	/**
	 * A TeleportGate konstruktora
	 */
	public TeleportGate(Asteroid a, Settler o, String name)
	{
		this.asteroid = a;
		this.owner = o;
		this.id = name;
		this.malfunctioning = false;
		stepped=false;
	}
	
	public void SetStepped(boolean azt) {
		stepped=azt;
	}
	
	public boolean GetStepped() {
		return stepped;
	}
	
	public void SetName(String n) {
		id = n;
	}
	/**
	 * Teleportkapu mozgasa, ha mar erte napszel
	 */
	public void Move() 
	{		
		if(this.GetMalfunction()==true && this.GetStepped()) {
			Bill teleBill=new Bill();
			teleBill.AddMaterialToBill(new TeleportGate(null, null, null));
			ArrayList<ID> teleList=new ArrayList<ID>();
			int index = 0;
			int max = this.asteroid.getNeighbors().size();
			while(index < max) {
				teleList.add(this.asteroid.getNeighbors().get(index));
				if(teleBill.CheckInventory(teleList)) {
					teleList.remove(this.asteroid.getNeighbors().get(index));
					index++;
					
				}else {
					asteroid.DropNeighbor(this);
					this.asteroid = (Asteroid)this.asteroid.getNeighbors().get(index);
					asteroid.AddNeighbor(this);
					break;
				}
			}
			this.stepped=true;
		}
	}
	
	/**
	 * Visszaadja a teleportkapu nevet, amit a letrehozasakor kapott
	 */
	public String getName() {
		return id;
	}
	/**
	 * A teleportkapu tulajdonosat beallito fuggveny.
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
		asteroid.AddNeighbor(this); //ez eredetileg ki volt kommentezve, de miert? szerintem szukseges, kiveve ha valamivel utkozik...
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
		this.sibling.GetAsteroid().RegisterBeing(being);
	}
	
	/**
	 * A teleportkapu felrobban
	 * Ha a teleportkapu aszteroidaja null, akkor telepesnel van
	 * Ha a teleportkapu aszteroidaja nem null, akkor aszteroidan van
	 */
	public void Explode() 
	{	Asteroid a = this.GetAsteroid();	
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
			a2.DropNeighbor(sibling);
			sibling.SetAsteroid(null);
		}else
		{
			sibling.GetOwner().DropCarriedTeleport(sibling);
		}
	}
	
	/**
	 * Visszaadja, hogy teleportgate tipusu
	 */
	public String GetUniqueID() {
		return new String("teleportgate");
	}
	/**
	 * Beallitja a malfunction valtozot
	 */
	public void Malfunction() {
		this.malfunctioning = true;
	}
	/**
	 * lekerdezi a malfunction valtozot
	 * @return Malfunctioning
	 */
	public boolean GetMalfunction() {
		return this.malfunctioning;
	}
}
