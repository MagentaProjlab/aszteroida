package asteroid;

import java.util.Scanner;

public class Robot extends SentientBeing
{
	/**
	 * Robot konstruktora
	 */
	public Robot()
	{
		Logger.MethodCall("Robot()");
		Logger.MethodReturn("");
	}
	/**
	 * Robot mozog az aszteroidak kozott
	 * @param place : az aszteroida, amire atmozdul
	 */
	public void Move(Place place) 
	{
		Logger.MethodCall("Move(Place place)");
		this.location.DropBeing(this);
		place.RegisterBeing(this);
		Logger.MethodReturn("void");
	}
	/**
	 * A robot fur
	 * A tesztelo adja meg, hogy a robot furhat - e.
	 * Ha furhat, akkor noveli a lyuk meretet egyel
	 */
	public void Drill()
	{
		Logger.MethodCall("Drill()");
		Logger.UserQuestion("Can the robot drill?");
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		if(input.equals("1"))
		{
			location.IncreaseHoleDepth();
		}
		Logger.MethodReturn("void");
	}	
	/**
	 * A robot meghal
	 */
	public void Die() 
	{
		Logger.MethodCall("Die()");
		location.DropBeing(this);
		Logger.MethodReturn("void");
	}
	/**
	 * A robot felrobban
	 * A tesztelo donti el, hogy van - e szomszedja az aszteroidanak.
	 * Ha nincsen, akkor a robot meghal.
	 * Ha van, akkor a szemoszedra mozdul.
	 */
	public void Explode()
	{
		Logger.MethodCall("Explode()");
		Scanner scanner = new Scanner(System.in);
		Logger.UserQuestion("Does the asteroid have more neighbors?");
		String input = scanner.nextLine();
		if(input.equals("2")) {
			Die();
		} else {
			Move(this.location.GetNeighbour());
		}
		Logger.MethodReturn("void");
	}
	/**
	 * A robot lep.
	 * A tesztelo donti el, hogy mozog vagy fur.
	 */
	public void Step() 
	{
		Logger.MethodCall("Step()");
		Scanner scanner = new Scanner(System.in);
		Logger.UserQuestionRobot("Drill or move the robot?");
		String input = scanner.nextLine();
		if(input.equals("1")) {
			Logger.Message("Refer to test 2");
		} else if(input.equals("2")) {
			Logger.Message("Refer to test 4");
		}
		Logger.MethodReturn("void");
	}
}
