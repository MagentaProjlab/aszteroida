package asteroid.logic;

import java.util.ArrayList;
import java.util.Random;

/**
 * A robot viselkedeset kezelo osztaly
 *
 */
public class Robot extends SentientBeing
{
	/**
	 * A robot neve
	 */
	String id;
	/**
	 * Robot konstruktora
	 */
	public Robot(Asteroid a, String name)
	{
		this.location = a;
		this.id = name;
	}
	
	/**
	 * Vissza adja a leny nevet, amit letrehozaskor kapott es a tipusat
	 * @return A leny neve
	 */
	public  String getFullName() {
		return "[Robot:"+id+"]";
	}
	
	/**
	 * Vissza adja a leny nevet, amit letrehozaskor kapott
	 * @return A leny neve
	 */
	public String getName() {
		return id;
	}
	/**
	 * Robot mozog az aszteroidak kozott
	 * @param place : az aszteroida, amire atmozdul
	 */
	public void Move(Place place) 
	{
		this.location.DropBeing(this);
		place.RegisterBeing(this);

	}
	/**
	 * A robot fur
	 * A tesztelo adja meg, hogy a robot furhat - e.
	 * Ha furhat, akkor noveli a lyuk meretet egyel
	 */
	public void Drill()
	{
		location.IncreaseHoleDepth();
	}	
	/**
	 * A robot meghal
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
		if(this.location.GetNeighbour() == null) {
			Die();
		} else {
			Move(this.location.GetNeighbour());
		}
	}
	/**
	 * A robot lep.
	 * A tesztelo donti el, hogy mozog vagy fur.
	 */
	public void Step() 
	{
		if (!this.getStepped()) {
			if (!location.isDrilled())
				this.Drill();
			else {
				ArrayList<Place> neighbors = location.getNeighbors();
				Random random = new Random();
				int index = random.nextInt(neighbors.size());
				Place place = neighbors.get(index);
				this.Move(place);
			}
			this.setStepped(true);
		}
	}
	
	/**
	 * Megadja a robot tipusat
	 */
	public String GetUniqueID()
	{
		return "Robot";
	}
}
