package asteroid.logic;

public class Coal extends RawMaterial 
{
	/**
	 * A szen konstruktora.
	 *
	 */
	public Coal()
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
	 * Az egyedi azonositot visszaado fuggveny. Szen esesteben 'Coal'.
	 */
	public String GetUniqueID() 
	{
		return "Coal";
	}
	
	/**
	 * Az anyag reakciojat napkozelben kivalto absztrakt fuggveny.
	 * Szen eseteben nincs reakcio.
	 */
	public void PerihelionReaction() {}
	
}
