package asteroid.logic;

/**
 * TeleportGate és RawMaterial közös interfésze
 */
public interface ID {
	/**
	 * Adott objektum tipusat lehet vele lekerdezni
	 * @return String: objektum tipusa
	 */
	public abstract String GetUniqueID();
}
