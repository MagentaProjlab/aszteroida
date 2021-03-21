package asteroid;

public abstract class Place
{
	/**
	 * Hozzarendel egy leny az adott "helyszinhez"
	 * @param being - hozzarendelendo leny
	 */
	public abstract void RegisterBeing(SentientBeing being);
	/**
	 * Felrobbantja az adott "helyszint"
	 */
	public abstract void Explode();
}
