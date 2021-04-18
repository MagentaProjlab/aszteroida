package asteroid;

import java.util.ArrayList;

public class Robot extends SentientBeing
{
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
		Logger.Message("[Robot: "+id+"] has moved to "+place.getName()+".");

	}
	/**
	 * A robot fur
	 * A tesztelo adja meg, hogy a robot furhat - e.
	 * Ha furhat, akkor noveli a lyuk meretet egyel
	 */
	public void Drill()
	{
		if(!location.isDrilled()) {
			location.IncreaseHoleDepth();
			Logger.Message("[Robot: "+id+"] has drilled "+location.getName()+".");
		}
		else {
			Logger.Message("[Robot: "+id+"] has failed to drill "+location.getName()+".");
		}
	}	
	/**
	 * A robot meghal
	 */
	public void Die() 
	{
		location.DropBeing(this);
		//Doku szerint nem irat ki
		//Logger.Message("[Robot: "+id+"] has died ");

	}
	/**
	 * A robot felrobban
	 * A tesztelo donti el, hogy van - e szomszedja az aszteroidanak.
	 * Ha nincsen, akkor a robot meghal.
	 * Ha van, akkor a szemoszedra mozdul.
	 */
	public void Explode()
	{
		Logger.Message("[Robot: "+id+"] has exploded "+".");

		if(this.location.GetNeighbour() != null) {
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
		Logger.Message("[Robot: "+id+"] has been selected to step.");
		String command=Logger.NextLine();
		String[] command_parts=command.split(" ");
		switch (command_parts[0]) {
			case "move":
				if(command_parts.length!=2) {
					Logger.Message("[Robot: "+id+"] failed to move"+".");
				}
				else {
					ArrayList<Place> neighbors=location.getNeighbors();
					int celzottIndex = -1;
					for (int i = 0; i< neighbors.size(); i++) {
						if(neighbors.get(i).getName().equals(command_parts[1])) {
							celzottIndex = i;
						}
					}
					if(celzottIndex != -1) {
						Move(neighbors.get(celzottIndex));
					}
					else {
						Logger.Message("[Robot: "+id+"] failed to move"+".");
					}
				}
				break;
			case "drill":
				this.Drill();
				break;	
			default:
				Logger.Message("Bad command for the Robot bucko"+".");
				break;
		}
		this.setStepped(true);
	}
	
	public String GetUniqueID()
	{
		return "Robot";
	}
}
