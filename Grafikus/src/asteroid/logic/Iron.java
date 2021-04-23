package asteroid.logic;

public class Iron extends RawMaterial
{
	/**
	 * A vas konstruktora.
	 *
	 */
	public Iron()
	{
		
	}
	
	/**
	 * A nyersanyagot elpusztito fuggveny.
	 */
	public void Perish()
	{
		this.SetAsteroid(null);
	}
	
	/**
	 * Az egyedi azonositot visszaado fuggveny. Vas esesteben 'Iron'.
	 */
	public String GetUniqueID()
	{
		return "Iron";
	}
	
	/**
	 * Az anyag reakciojat napkozelben kivalto absztrakt fuggveny.
	 * Vas eseteben nincs reakcio.
	 */
	public void PerihelionReaction(){}
}
