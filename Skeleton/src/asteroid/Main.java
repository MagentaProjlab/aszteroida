package asteroid;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static public void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		String input = "-1";
		while (!(input.equals("0"))) {
			System.out.println("Choose a test method! (1-15)");
			input = scanner.nextLine();
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
		}
		scanner.close();
	}
	
	/**
	 * A settler drills teszteset
	 * Letrehoz ket aszteroidat es egy telepest
	 * Szomszedi viszonyba helyezi a ket aszteroidat
	 * Mivel a banyaszas soran a kulonbozo nyersanyagtipusok mas reakciot valtanak ki
	 * igy eloszor ki kell valasztani a nyersanyag tipusat
	 * Majd lefut az adott nyersanyaghoz tartozo furasi teszteset
	 */
	private static void Teszteset1()
	{
		System.out.println("The settler drills test method begins");
		Scanner sc = new Scanner(System.in);
		System.out.println("Choose corematerial! [1: Iron, 2: Ice, 3: Coal, 4: Uranium]");
		String input = sc.nextLine();
		Settler s = new Settler();
		Asteroid location = new Asteroid();
		Asteroid Neighbor = new Asteroid();
		location.AddNeighbor(Neighbor);
		location.RegisterBeing(s);
		s.location = location;
		switch(Integer.parseInt(input))
		{
		case 1:
			Iron ir = new Iron();
			s.location.SetCore(ir);
			ir.SetAsteroid(location);
			s.Drill();
			break;
		case 2:  
			Ice ice = new Ice();
			s.location.SetCore(ice);
			ice.SetAsteroid(location);
			s.Drill();
			break;
		case 3: 
			Coal c = new Coal();
			s.location.SetCore(c);
			c.SetAsteroid(location);
			s.Drill();
			break;
		case 4:  
			Uranium ur = new Uranium();
			s.location.SetCore(ur);
			ur.SetAsteroid(location);
			BillOfMaterials radbill = new BillOfMaterials();
			radbill.AddMaterialToBill(ur);
			location.SetBill(radbill);
			s.Drill();
			break;
		}
		System.out.println("The test method has ran successfully");
	}
	
	/**
	 * A robot drills teszteset
	 * Letrehoz ket aszteroidat es egy robotot
	 * Szomszedi viszonyba helyezi a ket aszteroidat
	 * Mivel a banyaszas soran a kulonbozo nyersanyagtipusok mas reakciot valtanak ki
	 * igy eloszor ki kell valasztani a nyersanyag tipusat
	 * Majd lefut az adott nyersanyaghoz tartozo furasi teszteset
	 */
	private static void Teszteset2()
	{
		System.out.println("The robot drills test method begins");
		Scanner sc = new Scanner(System.in);
		System.out.println("Choose corematerial! [1: Iron, 2: Ice, 3: Coal, 4: Uranium]");
		String input = sc.nextLine();
		Robot r = new Robot();
		Asteroid location = new Asteroid();
		location.RegisterBeing(r);
		r.location = location;
		switch(Integer.parseInt(input))
		{
		case 1:
			Iron ir = new Iron();
			r.location.SetCore(ir);
			ir.SetAsteroid(location);
			r.Drill();
			break;
		case 2:  
			Ice ice = new Ice();
			r.location.SetCore(ice);
			ice.SetAsteroid(location);
			r.Drill();
			break;
		case 3: 
			Coal c = new Coal();
			r.location.SetCore(c);
			c.SetAsteroid(location);
			r.Drill();
			break;
		case 4:  
			Uranium ur = new Uranium();
			Asteroid b = new Asteroid();
			location.AddNeighbor(b);
			b.AddNeighbor(location);
			r.location.SetCore(ur);
			ur.SetAsteroid(location);
			BillOfMaterials radbill = new BillOfMaterials();
			radbill.AddMaterialToBill(ur);
			location.SetBill(radbill);
			r.Drill();
			break;
		}
		System.out.println("The test method has ran successfully");
	}
	
	/**
	 * A settler moves teszteset
	 * Letrehoz ket aszteroidat es egy telepest
	 * Szomszedi viszonyba helyezi a ket aszteroidat
	 * A telepes mozgasa vegrehajtodik a ket aszteroida kozott
	 */
	private static void Teszteset3()
	{
		System.out.println("The settler moves test method begins");
		Settler s = new Settler();
		Asteroid location = new Asteroid();
		Asteroid destination = new Asteroid();
		location.AddNeighbor(destination);
		destination.AddNeighbor(location);
		location.RegisterBeing(s);
		s.location = location;
		s.Move(destination);
		System.out.println("The test method has ran successfully");
	}
	
	/**
	 * A robot moves teszteset
	 * Letrehoz ket aszteroidat es egy robotot
	 * Szomszedi viszonyba helyezi a ket aszteroidat
	 * A telepes mozgasa vegrehajtodik a ket aszteroida kozott
	 */
	private static void Teszteset4()
	{
		System.out.println("The robot moves test method begins");
		Robot r = new Robot();
		Asteroid source = new Asteroid();
		Asteroid dest = new Asteroid();
		source.AddNeighbor(dest);
		dest.AddNeighbor(source);
		source.RegisterBeing(r);
		r.location = source;
		r.Move(dest);
		System.out.println("The test method has ran successfully");
	}
	
	/**
	 * A settler mines teszteset
	 * Letrehoz ket aszteroidat es egy telepest
	 * Az aszteroida magjat beallitja vasra
	 * A telepes regisztralja az aszteroidara
	 * A telepes a megfurt aszteroidat kibanyassza
	 */
	private static void Teszteset5()
	{
		System.out.println("The settler mines test method begins");
		Settler s = new Settler();
		Asteroid a = new Asteroid();
		Iron iron = new Iron();
		a.SetCore(iron);
		a.RegisterBeing(s);
		s.location = a;
		s.Mine();
		System.out.println("The test method has ran successfully");
	}
	
	/**
	 * A settler fills empty asteroid teszteset
	 * Letrehoz ket aszteroidat es egy robotot
	 * Szomszedi viszonyba helyezi a ket aszteroidat
	 * Mivel a banyaszas soran a kulonbozo nyersanyagtipusok mas reakciot valtanak ki
	 * igy eloszor ki kell valasztani a nyersanyag tipusat
	 * Majd lefut az adott nyersanyaghoz tartozo a nyersanyag visszahelyezesi teszteset
	 */
	private static void Teszteset6()
	{
		System.out.println("The settler fills empty asteroid test method begins");
		Settler s = new Settler();
		Asteroid a = new Asteroid();
		Asteroid Neighbor = new Asteroid();
		a.AddNeighbor(Neighbor);
		a.SetBill(new BillOfMaterials());
		a.RegisterBeing(s);
		s.location = a;
		Scanner sc = new Scanner(System.in);
		System.out.println("Choose corematerial! [1: Iron, 2: Ice, 3: Coal, 4: Uranium]");
		String input = sc.nextLine();

		switch(Integer.parseInt(input))
		{
		case 1:
			Iron ir = new Iron();
			s.AddCarriedMaterial(ir);
			s.FillAsteroid(ir);
			break;
		case 2:  
			Ice ice = new Ice();
			s.AddCarriedMaterial(ice);
			s.FillAsteroid(ice);
			break;
		case 3: 
			Coal c = new Coal();
			s.AddCarriedMaterial(c);
			s.FillAsteroid(c);
			break;
		case 4:  
			Asteroid a2=new Asteroid();
			a.AddNeighbor(a2);
			a2.AddNeighbor(a2);
			Uranium ur = new Uranium();
			s.AddCarriedMaterial(ur);
			s.FillAsteroid(ur);
			break;
		}
		System.out.println("The test method has ran successfully");
	}
	
	/**
	 * A settler builds robot teszteset
	 * Letrehoz ket aszteroidat es egy telepest
	 * Szomszedi viszonyba helyezi a ket aszteroidat
	 * Letrehozza a robot epitesehez szukseges nyersanyagokat(vas, jeg, uran, szen)
	 * A telepes megkapja a nyersanyagokat
	 * Vegul a telepes megepiti a robotot
	 */
	private static void Teszteset7()
	{
		System.out.println("The settler builds robot test method begins");
		Settler s = new Settler();
		Asteroid location = new Asteroid();
		Asteroid Neighbor = new Asteroid();
		location.AddNeighbor(Neighbor);
		location.RegisterBeing(s);
		s.location = location;
		s.AddCarriedMaterial(new Iron());
		s.AddCarriedMaterial(new Ice());
		s.AddCarriedMaterial(new Uranium());
		s.AddCarriedMaterial(new Coal());
		s.BuildRobot();
		System.out.println("The test method has ran successfully");
	}
	
	/**
	 * A settler builds teleportgate pair teszteset
	 * Letrehoz egy telepest
	 * Letrehozza a robot epitesehez szukseges nyersanyagokat(vas, jeg, uran)
	 * A telepes megkapja a nyersanyagokat
	 * Vegul a telepes megepiti a teleportkapu part
	 */
	private static void Teszteset8()
	{
		System.out.println("The settler builds teleportgate pair test method begins");
		Settler s=new Settler();
		s.AddCarriedMaterial(new Iron());
		s.AddCarriedMaterial(new Ice());
		s.AddCarriedMaterial(new Uranium());
		s.BuildTeleportGatePair();
		System.out.println("The test method has ran successfully");
	}
	
	/**
	 * A settler puts down teleport teszteset
	 * Letrehoz egy telepest
	 * A telepes lerakja a teleportkapu par egyik darabjat
	 */
	private static void Teszteset9()
	{
		System.out.println("The settler puts down teleport test method begins");
		Settler s=new Settler();

		s.PutTeleportGateOnAsteroid();
		System.out.println("The test method has ran successfully");
	}
	
	/**
	 * A asteroid explodes teszteset
	 * Letrehoz egy aszteroidat
	 * Beallitja az aszteroida magjat uranra
	 * Es mivel minden feltetel adott, igy az aszteroida felrobban
	 */
	private static void Teszteset10()
	{
		System.out.println("The asteroid explodes test method begins");
		Asteroid a = new Asteroid();
		Asteroid Neighbor = new Asteroid();
		a.AddNeighbor(Neighbor);
		Uranium u = new Uranium();
		a.SetCore(u);
		BillOfMaterials radbill = new BillOfMaterials();
		radbill.AddMaterialToBill(u);
		a.SetBill(radbill);
		a.Explode();
		System.out.println("The test method has ran successfully");
	}
	
	/**
	 * A solar wind happens teszteset
	 * Letrehoz egy aszteroidat es egy telepest
	 * Hozzarendeli az aszteroidahoz a telepest es forditva
	 * Letrehozza a napvihart
	 * Majd megtortenik a napvihar
	 */
	private static void Teszteset11()
	{
		System.out.println("The solar wind happens test method begins");
		ArrayList<Asteroid> asteroids=new ArrayList<Asteroid>();
		Asteroid a=new Asteroid();
		Settler s=new Settler();
		//jobban nem "toltottem fel" a telepest, mert a szekvencian csak a Die-ig mennek vonalak.
		a.RegisterBeing(new Settler());
		s.setAsteroid(a);
		asteroids.add(a);
		
		SolarWind sw=new SolarWind();
		sw.solarWind(asteroids);
		System.out.println("The test method has ran successfully");
	}
	
	/**
	 * A asteroid check cores perihelion reaction teszteset
	 * Letrehoz ket aszteroidat
	 * Szomszedi viszonyba helyezi a ket aszteroidat
	 * Mivel a robbanas soran a kulonbozo nyersanyagtipusok mas reakciot valtanak ki
	 * igy eloszor ki kell valasztani a nyersanyag tipusat
	 * Majd lefut az adott nyersanyaghoz tartozo napkozeli esemeny teszteset
	 */
	private static void Teszteset12()
	{
		System.out.println("The asteroid check cores perihelion reaction test method begins");
		Asteroid a = new Asteroid();
		Asteroid neigbor = new Asteroid();
		a.AddNeighbor(neigbor);
		Iron ir = new Iron();
		Ice ice = new Ice();
		Coal c = new Coal();
		Uranium ur = new Uranium();
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
		System.out.println("The test method has ran successfully");
	}
	
	/**
	 * A teleport explodes teszteset
	 * Letrehoz ket aszteroidat, egy teleportkapupart es ket telepest
	 * A telepesekhez aszteroidat rendel
	 * Osszekoti a ket teleportkaput
	 * Mivel a robbanas soran a teleport helyezetol fuggoen mas reakciot valtanak ki
	 * igy eloszor ki kell valasztani a teleport helyzetet
	 * Majd lefut az adott teleport helyzetehez tartozo teszteset
	 */	
	private static void Teszteset13()
	{
		System.out.println("The teleport explodes test method begins");
		TeleportGate tg = new TeleportGate();
		TeleportGate tgsibling = new TeleportGate();
		Asteroid tglocation = new Asteroid();
		Asteroid tgsiblinglocation = new Asteroid();
		Settler tgowner = new Settler();
		Settler tgsiblingowner = new Settler();
		//settlers on asteroids
		tgowner.setAsteroid(tglocation);
		tgsiblingowner.setAsteroid(tgsiblinglocation);
		//settlers to asteroids
		tglocation.RegisterBeing(tgowner);
		tgsiblinglocation.RegisterBeing(tgsiblingowner);
		//siblings
		tg.SetSibling(tgsibling);
		tgsibling.SetSibling(tg);
		System.out.println("[1: Sibling is on asteroid, 2: Sibling is with a settler]");
		System.out.println("[3: Teleport and sibling is on asteroid, 4: Teleport on asteroid, sibling is with settler]");
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		switch(Integer.parseInt(input))
		{
		case 1:
			//tg is with settler, sibling is on asteroid
			//tg sets owner
			tg.SetOwner(tgowner);
			tgowner.AddTeleport(tg);
			//tgsibling sets asteroid, no owner
			tgsibling.SetAsteroid(tgsiblinglocation);
			tgsiblinglocation.AddNeighbor(tgsibling);
			tglocation = tgowner.location;
			tgsiblinglocation = tgsibling.GetAsteroid();
			System.out.println("Test starts");
			tg.Explode();
			break;
		case 2: 
			//tg is with settler, sibling is with settler
			//tg sets owner
			tg.SetOwner(tgowner);
			tgowner.AddTeleport(tg);
			//tgsibling sets owner
			tgsibling.SetOwner(tgsiblingowner);
			tgsiblingowner.AddTeleport(tgsibling);
			tgsiblinglocation = tgsiblingowner.location;
			tglocation = tgowner.location;
			System.out.println("Test starts");
			tg.Explode();
			break;
		case 3:
			//tg is on asteroid, sibling is on asteroid
			//tg sets asteroid
			tg.SetAsteroid(tglocation);
			tglocation.AddNeighbor(tg);
			//tgsibling sets asteroid
			tgsibling.SetAsteroid(tgsiblinglocation);
			tgsiblinglocation.AddNeighbor(tgsibling);
			tglocation = tg.GetAsteroid();
			tgsiblinglocation = tgsibling.GetAsteroid();
			System.out.println("Test starts");
			tg.Explode();
			break;
		case 4:
			//tg is on asteroid, sibling is with settler
			//tg sets asteroid
			tg.SetAsteroid(tglocation);
			tglocation.AddNeighbor(tg);
			//tgsibling sets owner
			tgsibling.SetOwner(tgsiblingowner);
			tgsiblinglocation = tgsiblingowner.location;
			tglocation = tg.GetAsteroid();
			System.out.println("Test starts");
			tg.Explode();
		break;
		}
		System.out.println("The test method has ran successfully");
	}
	
	/**
	 * A controller start game teszteset
	 * A controller osztaly peldanyositasa
	 * A controller osztely letrehoz egy uj jatekot
	 */
	private static void Teszteset14()
	{
		System.out.println("The controller start game test method begins");
		ControllerClass cc = new ControllerClass();
		System.out.println("The test method has ran successfully");
	}
	
	/**
	 * A controller step asteroids teszteset
	 * Letrehoz ket aszteroidat, ket robotot es ket telepest
	 * Szomszedi viszonyba helyezi a ket aszteroidat
	 * Minden lenyt hozzarendel egy aszteroidahoz
	 * A leptetes megtortenik
	 */
	private static void Teszteset15()
	{
		System.out.println("The controller step asteroids test method begins");
		Asteroid a = new Asteroid();
		Asteroid neigbor = new Asteroid();
		a.AddNeighbor(neigbor);
		Robot r1 = new Robot();
		r1.location = a;
		Robot r2 = new Robot();
		r2.location = a;
		Settler s1 = new Settler();
		s1.location = a;
		Settler s2 = new Settler();
		s2.location = a;
		a.RegisterBeing(r1);
		a.RegisterBeing(r2);
		a.RegisterBeing(s1);
		a.RegisterBeing(s2);
		a.StepBeings();
		System.out.println("The test method has ran successfully");
	}
}
