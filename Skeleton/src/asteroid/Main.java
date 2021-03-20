package asteroid;

import java.util.Scanner;

public class Main {
	static public void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Válassz egy tesztesetet! (1-15)");
		String input = scanner.nextLine();
		scanner.close();
		switch(Integer.parseInt(input))
		{
			case 1: Teszteset1();
			case 2: Teszteset2();
			case 3: Teszteset3();
			case 4: Teszteset4();
			case 5: Teszteset5();
			case 6: Teszteset6();
			case 7: Teszteset7();
			case 8: Teszteset8();
			case 9: Teszteset9();
			case 10: Teszteset10();
			case 11: Teszteset11();
			case 12: Teszteset12();
			case 13: Teszteset13();
			case 14: Teszteset14();
			case 15: Teszteset15();
		}
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
		System.out.println("test start");
		r.Move(dest);
		
	}
	
	private static void Teszteset5()
	{
		
	}
	
	private static void Teszteset6()
	{
		
	}
	
	private static void Teszteset7()
	{
		
	}
	
	private static void Teszteset8()
	{
		
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
		
	}
	
	private static void Teszteset15()
	{
		
	}
}
