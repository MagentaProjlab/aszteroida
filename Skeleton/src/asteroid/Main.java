package asteroid;

import java.util.Scanner;

public class Main {
	static public void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Válassz egy tesztesetet! (1-15)");
		String input = scanner.nextLine();
		switch(Integer.parseInt(input))
		{
			case 1: Teszteset1(); break;
			case 2: Teszteset2(); break;
			case 3: Teszteset3(); break;
			case 4: Teszteset4(); break;
			case 5: Teszteset5(); break;
			case 6: Teszteset6(); break;
			case 7: Teszteset7(); break;
			case 8: Teszteset8(); break;
			case 9: Teszteset9(); break;
			case 10: Teszteset10(); break;
			case 11: Teszteset11(); break;
			case 12: Teszteset12(); break;
			case 13: Teszteset13(); break;
			case 14: Teszteset14(); break;
			case 15: Teszteset15(); break;
		}
		scanner.close();
	}
	
	private static void Teszteset1()
	{
		System.out.println("A settler drills teszteset indul");
		Settler s = new Settler();
		Asteroid location = new Asteroid();
		location.RegisterBeing(s);
		s.location = location;
		s.Drill();
		System.out.println("A teszt sikeresen lefutott");
	}
	
	private static void Teszteset2()
	{
		System.out.println("A robot drills teszteset indul");
		Robot r = new Robot();
		Asteroid location = new Asteroid();
		location.RegisterBeing(r);
		r.location = location;
		r.Drill();
		System.out.println("A teszt sikeresen lefutott");
	}
	
	private static void Teszteset3()
	{
		System.out.println("A settler moves teszteset indul");
		Settler s = new Settler();
		Asteroid location = new Asteroid();
		Asteroid destination = new Asteroid();
		location.AddNeighbor(destination);
		destination.AddNeighbor(location);
		location.RegisterBeing(s);
		s.location = location;
		s.Move(destination);
		System.out.println("A teszt sikeresen lefutott");
	}
	
	private static void Teszteset4()
	{
		Robot r = new Robot();
		Asteroid source = new Asteroid();
		Asteroid dest = new Asteroid();
		source.AddNeighbor(dest);
		dest.AddNeighbor(source);
		source.RegisterBeing(r);
		r.location = source;
		System.out.println("Test start");
		r.Move(dest);
		System.out.println("Test finished");
		
	}
	
	private static void Teszteset5()
	{
		Settler s = new Settler();
		Asteroid a = new Asteroid();
		Iron iron = new Iron();
		a.SetCore(iron);
		a.RegisterBeing(s);
		s.location = a;
		System.out.println("Test starts");
		s.Mine();
		System.out.println("Test finished");
	}
	
	private static void Teszteset6()
	{
		
	}
	
	private static void Teszteset7()
	{
		
	}
	
	private static void Teszteset8()
	{
		Settler s=new Settler();
		s.AddCarriedMaterial(new Iron());
		s.AddCarriedMaterial(new Ice());
		s.AddCarriedMaterial(new Uranium());
		System.out.println("test start");
		s.BuildTeleportGatePair();
	}
	
	private static void Teszteset9()
	{
		
	}
	
	private static void Teszteset10()
	{
		
	}
	
	private static void Teszteset11()
	{
		
	}
	
	private static void Teszteset12()
	{
		
	}
	
	private static void Teszteset13()
	{
		
	}
	
	private static void Teszteset14()
	{
		ControllerClass cc = new ControllerClass();
	}
	
	private static void Teszteset15()
	{
		
	}
}
