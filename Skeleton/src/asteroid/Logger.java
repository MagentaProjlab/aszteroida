package asteroid;

public class Logger {
	private static int indent = 0;
	
	public static void MethodCall(String functionName) {
										//TAB rész
		System.out.println(functionName);
		++indent;
	}
		
	public static void MethodReturn() {
		--indent;
	}
	
	public static void UserQuestion() {
		
	}
}
