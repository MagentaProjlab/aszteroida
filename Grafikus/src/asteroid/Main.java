package asteroid;
import java.awt.CardLayout;
import java.util.Scanner;

import asteroid.logic.Asteroid;
import asteroid.logic.ControllerClass;
import asteroid.logic.Settler;
import asteroid.logic.Uranium;
import asteroid.view.View;

public class Main {
	static public void main(String[] args)
	{
		View ablak = new View();
		/*ControllerClass controller = new ControllerClass();
		controller.SetView(ablak);
		ablak.SetController(controller);*/
		ablak.CreateAndShowGUI();
		//controller.GameLoop();
	}
}
