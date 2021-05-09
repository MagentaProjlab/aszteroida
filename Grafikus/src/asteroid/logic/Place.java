package asteroid.logic;

/**
 * Az aszteroidak es teleportkapu kozos ose, szomszedsagi viszonyt letesit
 *
 */
public abstract class Place implements ID
{
	/**
	 * Az adott hely neve
	 */
	protected String name;
	/**
	 * Hozzarendel egy leny az adott "helyszinhez"
	 * @param being - hozzarendelendo leny
	 */
	public abstract void RegisterBeing(SentientBeing being);
	/**
	 * Felrobbantja az adott "helyszint"
	 */
	public abstract void Explode();
	/**
	 * Megadja az adott hely nevet
	 * @return: a hely neve
	 */
	public abstract String getName();
}
