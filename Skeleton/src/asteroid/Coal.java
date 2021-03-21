package asteroid;

public class Coal extends RawMaterial 
{
	/**
	 * A szen konstruktora.
	 *
	 */
	public Coal()
	{
		Logger.MethodCall("Coal()");
		Logger.MethodReturn("");
	}
	
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
	 * Az egyedi azonositot visszaado fuggveny. Szen esesteben 'Coal'.
	 */
	public String GetUniqueID() 
	{
		Logger.MethodCall("GetUniqueID()");
		Logger.MethodReturn("String: Coal");
		return "Coal";
	}
	
	/**
	 * Az anyag reakciojat napkozelben kivalto absztrakt fuggveny.
	 * Szen eseteben nincs reakcio.
	 */
	public void PerihelionReaction() 
	{
		Logger.MethodCall("PerihelionReaction()");
		Logger.MethodReturn("void");
	}
	
}
