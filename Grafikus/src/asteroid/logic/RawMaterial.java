package asteroid.logic;

/**
 * Az aszteroidakban levo anyagok kozos ose
 *
 */
public abstract class RawMaterial implements ID 
{
	/**
	 * Tarolja, hogy az anyag melyik aszteroidaban van
	 */
	protected Asteroid asteroid;
	
	/**
	 * A nyersanyagot elpusztito fuggveny.
	 */
	public void Perish()
	{
		this.SetAsteroid(null);
	}
	
	/**
	 * Beallitja az anyagot tartalmazo aszteroidat tarolo valtozot.
	 * @param asteroid: az anyagot tartalmazo aszteroida
	 */
	public void SetAsteroid(Asteroid asteroid)
	{
		this.asteroid = asteroid;
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
