package asteroid;

public abstract class SentientBeing implements ID
{
	protected Asteroid location;
	/**
	 * A sentientbeing beallitja az aszteroidajat
	 * @param loc : A beallitando aszteroida
	 */
	public void setAsteroid(Asteroid loc)
	{
		this.location = loc;
	}
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
