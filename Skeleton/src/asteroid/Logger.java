package asteroid;

/**
 * Kiiratasert felelos osztaly
 */
public class Logger {
	/**
	 * Behuzas merteket tarolo valtozo
	 */
	private static int indent = 0;
	
	/**
	 * Fuggvenyhivas elejen ez a metodus irja ki a fuggveny nevet
	 * @param functionName: fuggveny neve
	 */
	public static void MethodCall(String functionName) {
		for (int i=0; i<indent; ++i) {
			System.out.print("  ");	
		}
		System.out.println("->" + functionName);
		++indent;
	}
	
	/**
	 * Fuggvenyhivas vegen ez a metodus irja ki a visszateresi erteket
	 * Konstruktor eseten csak a nyilat irja ki
	 * @param returnName: visszateresi ertek
	 */
	public static void MethodReturn(String returnName) {
		--indent;
		for (int i=0; i<indent; ++i) {
			System.out.print("  ");	
		}
		System.out.println("<-" + returnName);
	}
	
	/**
	 * Felhasznalonak kuldott igen-nem valaszú kerdesert felelos
	 * @param question: kerdes szovege
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
	 * Felhasznalonak feltett kerdes robottal kapcsolatban
	 * Robot Step fuggvenyenel hasznalva
	 * @param question: kerdes szovege
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
	 * Felhasznalonak feltett kerdes telepessel kapcsolatban
	 * Settler Step fuggvenyenel hasznalva
	 * @param question: kerdes szovege
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
	 * Felhasznalonak kuldott tajekoztato uzenetet jelenit meg
	 * @param message: uzenet szovege
	 */
	public static void Message(String message) {
		for (int i=0; i<indent; ++i) {
			System.out.print("  ");	
		}
		System.out.println(message);
	}
}
