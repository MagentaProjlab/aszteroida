package asteroid;

public abstract class SentientBeing
{
	protected Asteroid location;
	
	public void setAsteroid(Asteroid loc)
	{
		Logger.MethodCall("setAsteroid(Asteroid loc)");
		this.location = loc;
		Logger.MethodReturn("void");
	}
	
	public abstract void Move(Place place);
	public abstract void Drill();
	public abstract void Die();
	public abstract void Explode();
	public abstract void Step();
}
