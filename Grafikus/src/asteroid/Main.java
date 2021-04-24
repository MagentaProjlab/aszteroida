package asteroid;
import java.awt.CardLayout;
import java.util.Scanner;

import asteroid.logic.ControllerClass;
import asteroid.view.View;

public class Main {
	static public void main(String[] args)
	{
		/*Scanner scanner = new Scanner(System.in);
		int input = -1;
		System.out.println("Choose a test case! (1-36)");
		System.out.println("1. Settler drills");
		System.out.println("2. Settler drills - Asteroid already fully drilled");
		System.out.println("3. Robot drills");
		System.out.println("4. Robot drills � Asteroid already fully drilled");
		System.out.println("5. Settler move");
		System.out.println("6. Robot move");
		System.out.println("7. Settler mine");
		System.out.println("8. Settler mines � Asteroid is empty");
		System.out.println("9. Settler mines � Settler is full");
		System.out.println("10. Settler puts back material ");
		System.out.println("11. Settler puts back material � Asteroid already has material");
		System.out.println("12. Settler puts back material � Asteroid not fully drilled");
		System.out.println("13. Settler builds robot");
		System.out.println("14. Settler builds robot � Settler doesn�t have resources");
		System.out.println("15. Settler builds teleport gate pair");
		System.out.println("16. Settler builds teleport gate pair � Settler doesn�t have resources");
		System.out.println("17. Asteroid Explode");
		System.out.println("18. Asteroid Explode � explodes neighbors");
		System.out.println("19. Asteroid Explode � explodes living beings");
		System.out.println("20. Asteroid Explode � explodes robot");
		System.out.println("21. Controller kills with solar wind");
		System.out.println("22. Asteroid checks core�s perihelion reaction");
		System.out.println("23. Asteroid checks core�s perihelion reaction - core is radioactive");
		System.out.println("24. Asteroid checks core�s perihelion reaction - core is made of ice");
		System.out.println("25. Teleport Gate explodes");
		System.out.println("26. Teleport Gate explodes � a settler has sibling");
		System.out.println("27. Teleport Gate explodes � sibling is on an asteroid");
		System.out.println("28. Controller steps asteroids");
		System.out.println("29. Controller ends game - Lose");
		System.out.println("30. Controller ends game - Win");
		System.out.println("31. Ufo moves");
		System.out.println("32. Ufo picks up material");
		System.out.println("33. Ufo uses teleport");
		System.out.println("34. Teleportgate starts to move");
		System.out.println("35. Solar wind affecting asteroids");
		System.out.println("36. Complex test");
		input = scanner.nextInt();
		ControllerClass controller = new ControllerClass(input);
		controller.GameLoop();
		scanner.close();*/
		
		View ablak = new View();
		ablak.CreateAndShowGUI();
	}
}
