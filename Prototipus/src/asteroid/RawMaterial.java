package asteroid;

public abstract class RawMaterial
{
	protected Asteroid asteroid;
	
	/**
	 * A nyersanyagot elpusztito fuggveny.
	 */
	public void Perish()
	{
		Logger.MethodCall("Perish()");
		this.SetAsteroid(null);
		Logger.MethodReturn("void");
	}
	
	/**
	 * Beallitja az anyagot tartalmazo aszteroidat tarolo valtozot.
	 * @param asteroid: az anyagot tartalmazo aszteroida
	 */
	public void SetAsteroid(Asteroid asteroid)
	{
		Logger.MethodCall("SetAsteroid(Asteroid asteroid)");
		this.asteroid = asteroid;
		Logger.MethodReturn("void");
	}
	
	/**
	 * Az egyedi azonositot visszaado absztrakt fuggveny.
	 */
	public abstract String GetUniqueID();
	
	/**
	 * Az anyag reakciojat napkozelben kivalto absztrakt fuggveny.
	 */
	public abstract void PerihelionReaction();
}
