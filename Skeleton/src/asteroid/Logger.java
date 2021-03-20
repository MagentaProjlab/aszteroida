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
	
	public static void UserQuestion() {
		
	}
}
