package asteroid;
import java.util.Scanner;

public class Main {
	static public void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		int input = -1;
		System.out.println("Choose a test case! (1-36)");
		input = scanner.nextInt();
		ControllerClass controller = new ControllerClass(input);
		scanner.close();
	}
}
