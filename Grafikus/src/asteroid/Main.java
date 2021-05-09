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
		ControllerClass controller = new ControllerClass();
		View ablak = new View(controller);
		ablak.CreateAndShowGUI();
	}
}
