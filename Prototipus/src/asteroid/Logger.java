package asteroid;

/**
 * Kiiratasert felelos osztaly
 */
public class Logger {
	
	private String output;
	
	Logger(int teszteset)
	{
		
	}
	
	public void Message(String message) {
		System.out.println(message);
		output += message + "\n";
	}
}
