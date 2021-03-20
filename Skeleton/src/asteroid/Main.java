package asteroid;

import java.util.ArrayList;
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
		System.out.println("A settler puts back material teszteset indul");
		Settler s = new Settler();
		Asteroid location = new Asteroid();
		RawMaterial m = new Iron();
		s.AddCarriedMaterial(m);
		location.SetMaterial(m, s);
		s.DropCarriedMaterial(m);
		location.AtPerihelion();
		System.out.println("A teszt sikeresen lefutott");
	}
	
	private static void Teszteset7()
	{
		System.out.println("A settler builds robot teszteset indul");
		Settler s = new Settler();
		Asteroid location = new Asteroid();
		location.RegisterBeing(s);
		s.location = location;
		s.AddCarriedMaterial(new Iron());
		s.AddCarriedMaterial(new Ice());
		s.AddCarriedMaterial(new Uranium());
		s.AddCarriedMaterial(new Coal());
		s.BuildRobot();
		System.out.println("A teszt sikeresen lefutott");
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
		Settler s=new Settler();
		//Jelenlegi megold�som az "incializ�l�s" sor�n felmer�l� extra �zenetek elker�l�s�sre.
		//Ez lehet fars. A tesztben haszn�lt objektumok, a met�dsuban vannak l�trehozva.

		System.out.println("test start");
		s.PutTeleportGateOnAsteroid();
		
		

	}
	

		
	private static void Teszteset11()
	{
		System.out.println("SolarWind teszteset");
		ArrayList<Asteroid> asteroids=new ArrayList<Asteroid>();
		Asteroid a=new Asteroid();
		Settler s=new Settler();
		//jobban nem "toltottem fel" a telepest, mert a szekvencian csak a Die-ig mennek vonalak.
		a.RegisterBeing(new Settler());
		s.setAsteroid(a);
		asteroids.add(a);
		//TODO de nem baj, hogy a tester látja itt ezeket felettem?(az incializalasokat)
		
		SolarWind sw=new SolarWind();
		sw.solarWind(asteroids);
	}

	
	private static void Teszteset10()
	{
		System.out.println("Az asteroid explodes teszteset indul");
		Asteroid a = new Asteroid();
		Uranium u = new Uranium();
		a.SetCore(u);
		BillOfMaterials radbill = new BillOfMaterials();
		radbill.AddMaterialToBill(u);
		a.SetBill(radbill);
		a.Explode();
		System.out.println("A teszt sikeresen lefutott");
	}
	
	private static void Teszteset12()
	{
		Asteroid a = new Asteroid();
		Iron ir = new Iron();
		Ice ice = new Ice();
		Coal c = new Coal();
		Uranium ur = new Uranium();
		System.out.println("Test starts");
		System.out.println("Choose corematerial! [1: Iron, 2: Ice, 3: Coal, 4: Uranium]");
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		switch(Integer.parseInt(input))
		{
		case 1: a.SetCore(ir);
		ir.SetAsteroid(a);
		a.CheckPerihelionReaction();
		break;
		case 2:  a.SetCore(ice);
		ice.SetAsteroid(a);
		a.CheckPerihelionReaction();
		break;
		case 3:  a.SetCore(c);
		c.SetAsteroid(a);
		a.CheckPerihelionReaction();
		break;
		case 4:  a.SetCore(ur);
		BillOfMaterials radbill = new BillOfMaterials();
		radbill.AddMaterialToBill(ur);
		a.SetBill(radbill);
		ur.SetAsteroid(a);
		a.CheckPerihelionReaction();
		break;
		}
		System.out.println("Test finished");
		sc.close();
		
	}
	
	private static void Teszteset13()
	{
		TeleportGate tg = new TeleportGate();
		TeleportGate tgsibling = new TeleportGate();
		Asteroid tglocation = new Asteroid();
		Asteroid tgsiblinglocation = new Asteroid();
		Settler tgowner = new Settler();
		Settler tgsiblingowner = new Settler();
		tgowner.setAsteroid(tglocation);
		tglocation.RegisterBeing(tgowner);
		tg.SetSibling(tgsibling);
		tgsibling.SetSibling(tg);
		tg.SetOwner(tgowner);
		tgowner.addteleport(tg);
		tglocation = tgowner.location;
		tgsiblingowner.setAsteroid(tgsiblinglocation);
		tgsiblinglocation.RegisterBeing(tgsiblingowner);
		tgsiblinglocation = tgsiblingowner.location;
		System.out.println("Test starts");
		System.out.println("[1: Sibling is on asteroid, 2: Sibling is with a settler]");
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		switch(Integer.parseInt(input))
		{
		case 1:
			tgsibling.SetAsteroid(tgsiblinglocation);
			tg.Explode();
			break;
		case 2: 
			tgsibling.SetOwner(tgsiblingowner);
			tg.Explode();
			break;
		}
	
		System.out.println("Test finished");
	}
	
	private static void Teszteset14()
	{
		System.out.println("Test starts");
		ControllerClass cc = new ControllerClass();
		System.out.println("Test finished");
	}
	
	private static void Teszteset15()
	{
		
	}
}
