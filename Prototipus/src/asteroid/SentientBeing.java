package asteroid;

public abstract class SentientBeing
{
	protected Asteroid location;
	/**
	 * A sentientbeing beallitja az aszteroidajat
	 * @param loc : A beallitando aszteroida
	 */
	public void setAsteroid(Asteroid loc)
	{
		Logger.MethodCall("setAsteroid(Asteroid loc)");
		this.location = loc;
		Logger.MethodReturn("void");
	}
	/**
	 * A leny absztrakt mozogasert felelos fuggvenye
	 * @param place: A hely ahova mozog
	 */
	public abstract void Move(Place place);
	/**
	 * A leny absztrakt furasert felelos fuggvenye
	 */
	public abstract void Drill();
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
