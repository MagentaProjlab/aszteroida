package asteroid.logic;

public abstract class SentientBeing implements ID
{
	private boolean hasStepped;
	protected Asteroid location;
	/**
	 * A sentientbeing beallitja az aszteroidajat
	 * @param loc : A beallitando aszteroida
	 */
	public void setAsteroid(Asteroid loc)
	{
		this.location = loc;
		this.hasStepped = false;
	}
	
	/**
	 * Megadja, hogy melyik aszteroidan all a being
	 * @return location: az aktív aszteroida
	 */
	public Asteroid getAsteroid()
	{
		return location;
	}
	
	/**
	 * Visszaadja, hogy a being lepett mar-e az aktiv korben
	 * @return hasStepped: true, ha mar lepett az aktiv korben
	 */
	public boolean getStepped()
	{
		return hasStepped;
	}
	
	/**
	 * Beallitja, hogy adott korben lepett mar-e a being
	 * @param arg: true, ha lepett a korben
	 */
	public void setStepped(boolean arg)
	{
		hasStepped = arg;
	}
	/**
	 * Vissza adja a leny nevet, amit letrehozaskor kapott
	 * @return A leny neve
	 */
	public abstract String getFullName();
	/**
	 * Vissza adja a leny nevet, es a tipusat a prototipus dokumentacioban meghatarozott modon
	 * @return A leny tipusa es neve
	 */
	public abstract String getName();
	/**
	 * A leny absztrakt mozogasert felelos fuggvenye
	 * @param place: A hely ahova mozog
	 */
	public abstract void Move(Place place);
	/**
	 * A leny absztrakt halalert felelos fuggvenye
	 */
	public abstract void Die();
	/**
	 * A leny absztrakt felrobbanaert felelos fuggvenye
	 */
	public abstract void Explode();
	/**
	 * A leny absztrakt lepesert felelos fuggvenye
	 */
	public abstract void Step();
	
}
