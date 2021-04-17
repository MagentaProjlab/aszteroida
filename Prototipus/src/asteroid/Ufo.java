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
		Logger.Message("[Ufo: "+id+"] has moved to "+place.getName());

	}
	/**
	 * Ufo ellopja az aszteroida nyersanyagat
	 * 
	 */
	public void Mine() 
	{
			if(!location.IsEmpty()&&location.isDrilled()) {
				RawMaterial rm=location.GetMaterial();
				this.carriedmaterials.add(rm);
				rm.SetAsteroid(null);
				location.DropMaterial();
				Logger.Message("[Ufo: "+id+"] has mined ");
			}
			else {
				Logger.Message("[Ufo: "+id+"] has failed to mine ");
			}
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
		//Doku szerint nem irat ki
		//Logger.Message("[Ufo: "+id+"] has died ");
	}
	/**
	 * A robot felrobban
	 * A tesztelo donti el, hogy van - e szomszedja az aszteroidanak.
	 * Ha nincsen, akkor a robot meghal.
	 * Ha van, akkor a szemoszedra mozdul.
	 */
	public void Explode()
	{
		Logger.Message("[Ufo: "+id+"] has exploded ");
		Die();
			
	}
	/**
	 * Az ufo lep.
	 * A tesztelo donti el, hogy mozog vagy fur.
	 */
	public void Step() 
	{
		Logger.Message("[Ufo: "+id+"] has been selected to step.");
		String command=Logger.NextLine();
		String[] command_parts=command.split(" ");
		switch (command_parts[0]) {
			case "move":
				if(command_parts.length!=2) {
					Logger.Message("[Ufo: "+id+"] failed to move  ");
				}
				else {
					ArrayList<Place> neighbors=location.getNeighbors();
					int celzottIndex=Integer.parseInt(command_parts[1]);
					if(neighbors.size()>=1+celzottIndex) {
						Move(neighbors.get(celzottIndex));
					}
					else {
						Logger.Message("[Ufo: "+id+"] failed to move  ");
					}
				}
				break;
			case "mine":
				this.Mine();
				break;
			default:
				Logger.Message("Bad command for the Ufo bucko");
				break;
		}
		
		
	}
}
