package asteroid.logic;

import java.util.ArrayList;
import java.util.Random;

/**
 * Az ufo viselkedeset kezelo osztaly
 *
 */
public class Ufo extends SentientBeing
{
	private String id;
	private ArrayList<RawMaterial> carriedmaterials;
	/**
	 * Ufo konstruktora
	 */
	public Ufo(Asteroid a, String name)
	{
		this.location = a;
		this.id = name;
		carriedmaterials = new ArrayList<RawMaterial>();
	}
	/**
	 * Vissza adja a leny nevet, amit letrehozaskor kapott es a tipusat
	 * @return A leny neve
	 */
	public  String getFullName() {
		return "[Ufo:"+id+"]";
	}
	
	/**
	 * Vissza adja a leny nevet, amit letrehozaskor kapott
	 * @return A leny neve
	 */
	public String getName() {
		return id;
	}
	/**
	 * Ufo mozog az aszteroidak kozott
	 * @param place : az aszteroida, amire atmozdul
	 */
	public void Move(Place place) 
	{
		this.location.DropBeing(this);
		place.RegisterBeing(this);
	}
	/**
	 * Ufo ellopja az aszteroida nyersanyagat
	 * 
	 */
	public void Mine() 
	{
		RawMaterial rm=location.GetMaterial();
		this.carriedmaterials.add(rm);
		rm.SetAsteroid(null);
		location.DropMaterial();
	}
	/**
	 * Az ufo meghal
	 */
	public void Die() 
	{
		location.DropBeing(this);
		for(RawMaterial item:carriedmaterials) {
			item.Perish();
		}
	}
	
	/**
	 * A robot felrobban
	 * A tesztelo donti el, hogy van - e szomszedja az aszteroidanak.
	 * Ha nincsen, akkor a robot meghal.
	 * Ha van, akkor a szemoszedra mozdul.
	 */
	public void Explode()
	{
		Die();
	}
	/**
	 * Az ufo lep.
	 * A tesztelo donti el, hogy mozog vagy fur.
	 */
	public void Step() 
	{
		if (!this.getStepped()) {
			if(!location.IsEmpty()&&location.isDrilled()) {
				this.Mine();
			}
			else {
				ArrayList<Place> neighbors = location.getNeighbors();
				int neighborSize = neighbors.size();
				Random random = new Random();
				int index = random.nextInt(neighborSize);
				Place place = neighbors.get(index);
				this.Move(place);
			}
			this.setStepped(true);
		}
	}
	
	/**
	 * Visszaadja, hogy Ufo tipusu
	 */
	public String GetUniqueID()
	{
		return "Ufo";
	}
}
