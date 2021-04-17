package asteroid;

public class Ice extends RawMaterial 
{
	/**
	 * A jeg konstruktora.
	 *
	 */
	public Ice(Asteroid a)
	{
		this.asteroid = a;
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
		//here goes the fuggveny for getting the crust thickness
		if (this.asteroid.getCrustThickness().equals(this.asteroid.getHoleDepth())) {
			this.asteroid.DropMaterial();
			this.Perish();
		}
	}

}
