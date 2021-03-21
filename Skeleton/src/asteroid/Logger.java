package asteroid;

public class Logger {
	private static int indent = 0;
	
	public static void MethodCall(String functionName) {
		for (int i=0; i<indent; ++i) {			//TAB rész
			System.out.print("  ");	
		}
		System.out.println("->" + functionName);
		++indent;
	}
		
	public static void MethodReturn(String returnName) {
		--indent;
		for (int i=0; i<indent; ++i) {			//TAB rész
			System.out.print("  ");	
		}
		System.out.println("<-" + returnName);
	}
	
	public static void UserQuestion(String question) {
		for (int i=0; i<indent; ++i) {			//TAB rész
			System.out.print("  ");	
		}
		System.out.println("? " + question);
		for (int i=0; i<=indent; ++i) {			//+1 behúzás
			System.out.print("  ");	
		}
		System.out.println("1:Yes");
		for (int i=0; i<=indent; ++i) {			//+1 behúzás
			System.out.print("  ");	
		}
		System.out.println("2:No");
		for (int i=0; i<=indent; ++i) {			//+1 behúzás
			System.out.print("  ");	
		}
		System.out.print(":");
	}
	
	public static void UserQuestionRobot(String question) {
		for (int i=0; i<indent; ++i) {			//TAB rész
			System.out.print("  ");	
		}
		System.out.println("? " + question);
		for (int i=0; i<=indent; ++i) {			//+1 behúzás
			System.out.print("  ");	
		}
		System.out.println("1:Drill");
		for (int i=0; i<=indent; ++i) {			//+1 behúzás
			System.out.print("  ");	
		}
		System.out.println("2:Move");
		for (int i=0; i<=indent; ++i) {			//+1 behúzás
			System.out.print("  ");	
		}
		System.out.print(":");
	}
	
	public static void UserQuestionSettler(String question) {
		for (int i=0; i<indent; ++i) {			//TAB rész
			System.out.print("  ");	
		}
		System.out.println("? " + question);
		for (int i=0; i<=indent; ++i) {			//+1 behúzás
			System.out.print("  ");	
		}
		System.out.println("1:Move");
		for (int i=0; i<=indent; ++i) {			//+1 behúzás
			System.out.print("  ");	
		}
		System.out.println("2:Drill");
		for (int i=0; i<=indent; ++i) {			//+1 behúzás
			System.out.print("  ");	
		}
		System.out.println("3:Mine");
		for (int i=0; i<=indent; ++i) {			//+1 behúzás
			System.out.print("  ");	
		}
		System.out.println("4:Put back material");
		for (int i=0; i<=indent; ++i) {			//+1 behúzás
			System.out.print("  ");	
		}
		System.out.println("5:Build robot");
		for (int i=0; i<=indent; ++i) {			//+1 behúzás
			System.out.print("  ");	
		}
		System.out.println("6:Build teleport");
		for (int i=0; i<=indent; ++i) {			//+1 behúzás
			System.out.print("  ");	
		}
		System.out.println("7:Put down teleport");
		for (int i=0; i<=indent; ++i) {			//+1 behúzás
			System.out.print("  ");	
		}
		System.out.print(":");
	}
}
