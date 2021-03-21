package asteroid;

/**
 * Kiiratásért felelős osztály
 */
public class Logger {
	/**
	 * Behúzás mértékét tároló változó
	 */
	private static int indent = 0;
	
	/**
	 * Függvényhívás elején ez a metódus írja ki a függvény nevét
	 * @param functionName: függvény neve
	 */
	public static void MethodCall(String functionName) {
		for (int i=0; i<indent; ++i) {
			System.out.print("  ");	
		}
		System.out.println("->" + functionName);
		++indent;
	}
	
	/**
	 * Függvényhívás végén ez a metódus írja ki a visszatérési értéket
	 * Konstruktor esetén csak a nyilat írja ki
	 * @param returnName: visszatérési érték
	 */
	public static void MethodReturn(String returnName) {
		--indent;
		for (int i=0; i<indent; ++i) {
			System.out.print("  ");	
		}
		System.out.println("<-" + returnName);
	}
	
	/**
	 * Felhasználónak küldött igen-nem válaszú kérdésért felelős
	 * @param question: kérdés szövege
	 */
	public static void UserQuestion(String question) {
		for (int i=0; i<indent; ++i) {
			System.out.print("  ");	
		}
		System.out.println("? " + question);
		for (int i=0; i<=indent; ++i) {
			System.out.print("  ");	
		}
		System.out.println("1:Yes");
		for (int i=0; i<=indent; ++i) {
			System.out.print("  ");	
		}
		System.out.println("2:No");
		for (int i=0; i<=indent; ++i) {
			System.out.print("  ");	
		}
		System.out.print(":");
	}
	
	/**
	 * Felhasználónak feltett kérdés robottal kapcsolatban
	 * Robot Step függvényénél használva
	 * @param question: kérdés szövege
	 */
	public static void UserQuestionRobot(String question) {
		for (int i=0; i<indent; ++i) {
			System.out.print("  ");	
		}
		System.out.println("? " + question);
		for (int i=0; i<=indent; ++i) {
			System.out.print("  ");	
		}
		System.out.println("1:Drill");
		for (int i=0; i<=indent; ++i) {
			System.out.print("  ");	
		}
		System.out.println("2:Move");
		for (int i=0; i<=indent; ++i) {
			System.out.print("  ");	
		}
		System.out.print(":");
	}
	
	/**
	 * Felhasználónak feltett kérdés telepessel kapcsolatban
	 * Settler Step függvényénél használva
	 * @param question: kérdés szövege
	 */
	public static void UserQuestionSettler(String question) {
		for (int i=0; i<indent; ++i) {
			System.out.print("  ");	
		}
		System.out.println("? " + question);
		for (int i=0; i<=indent; ++i) {
			System.out.print("  ");	
		}
		System.out.println("1:Move");
		for (int i=0; i<=indent; ++i) {
			System.out.print("  ");	
		}
		System.out.println("2:Drill");
		for (int i=0; i<=indent; ++i) {
			System.out.print("  ");	
		}
		System.out.println("3:Mine");
		for (int i=0; i<=indent; ++i) {
			System.out.print("  ");	
		}
		System.out.println("4:Put back material");
		for (int i=0; i<=indent; ++i) {
			System.out.print("  ");	
		}
		System.out.println("5:Build robot");
		for (int i=0; i<=indent; ++i) {
			System.out.print("  ");	
		}
		System.out.println("6:Build teleport");
		for (int i=0; i<=indent; ++i) {
			System.out.print("  ");	
		}
		System.out.println("7:Put down teleport");
		for (int i=0; i<=indent; ++i) {
			System.out.print("  ");	
		}
		System.out.print(":");
	}
	
	/**
	 * Felhasználónak küldött tájékoztató üzenetet jelenít meg
	 * @param message: üzenet szövege
	 */
	public static void Message(String message) {
		for (int i=0; i<indent; ++i) {
			System.out.print("  ");	
		}
		System.out.println(message);
	}
}
