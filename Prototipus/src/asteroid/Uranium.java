package asteroid;

public class Uranium extends RawMaterial 
{
	int ReactionCount;
	/**
	 * Az uran konstruktora.
	 *
	 */
	public Uranium(Asteroid a, int recount) {
		this.asteroid = a;
		this.ReactionCount = recount;
	}
	
	/**
	 * A nyersanyagot elpusztito fuggveny.
	 */
	public void Perish() 
	{
		this.SetAsteroid(null);
	}
	
	/**
	 * Az egyedi azonositot visszaado fuggveny. Uran esesteben 'Uranium'.
	 */
	public String GetUniqueID() 
	{
		return "Uranium";
	}
	
	/**
	 * Az anyag reakciojat napkozelben kivalto absztrakt fuggveny.
	 * Az uran reakcioba lep napkozelben.
	 * Ha a tesztelo azt mondja, hogy Holedepth = CrustTickness(vagyis ki van furva)
	 * akkor felrobbantja az aszteroidat. Egyebkent nem csinal semmit.
	 */
	public void PerihelionReaction() 
	{
		if (this.asteroid.getCrustThickness().equals(this.asteroid.getHoleDepth())) {
			if (this.ReactionCount < 3) {
				this.ReactionCount++;
			}else {
				this.asteroid.Explode();
			}
		}
	}
	
	}

