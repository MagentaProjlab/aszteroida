package asteroid.logic;

public class Ice extends RawMaterial 
{
	/**
	 * A jeg konstruktora.
	 *
	 */
	public Ice()
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
	 * Az egyedi azonositot visszaado fuggveny. Jeg esesteben 'Ice'.
	 */
	public String GetUniqueID() 
	{
		return "Ice";
	}
	
	/**
	 * Az anyag reakciojat napkozelben kivalto absztrakt fuggveny.
	 * A jeg reakcioba lep napkozelben.
	 * Ha a tesztelo azt mondja, hogy Holedepth = CrustTickness(vagyis ki van furva)
	 * akkor elparolog a jeg. Egyebkent nem csinal semmit.
	 */
	public void PerihelionReaction() 
	{
		if (this.asteroid.isDrilled()) {
			this.asteroid.DropMaterial();
			this.Perish();
		}
	}

}
