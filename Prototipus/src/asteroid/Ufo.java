package asteroid;

import java.util.ArrayList;

public class Ufo extends SentientBeing
{
	String id;
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
			if(!location.IsEmpty()) {
				RawMaterial rm=location.GetMaterial();
				this.carriedmaterials.add(rm);
				rm.SetAsteroid(null);
				location.DropMaterial();
			}
	}
	/**
	 * Az ufo meghal
	 */
	public void Die() 
	{
		location.DropBeing(this);
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
		
	}
}
