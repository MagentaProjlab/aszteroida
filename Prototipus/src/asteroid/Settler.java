package asteroid;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Settler osztaly 
 * Kepessegei:mozgas, furas, banyaszas, robot vagy teleport epites, utobbi lehelyezese.
 * Viszont az o explode metodusa egyertelmuen a die metodust hivja tovabb.
 * A jatek vegere neki es vagy tarsainak kell osszegyujteni a szukseges nyersanyagokat.
 * 
 */
public class Settler extends SentientBeing {
	/**
	 * A telepes altal osszegyujtott nyersanyagokat tartalmazo lista.
	 * A kezelesehez kulon metodusokat fogunk majd hasznalni.
	 */
	private ArrayList<RawMaterial> carriedmaterials;
	/**
	 * A Settler altal epitheto entitasokhoz szukseges BillOfMaterialok gyujtemenye.
	 * Az osztaly konstruktoraban kerulnek feltoltesre a megfelelo "receptekkel".
	 */
	private ArrayList<Bill> bills;
	/**
	 * A telepes altal birtokolt teleportokat tartalmazo lista.
	 * Az epito metodusban kerul ellenorzesre, hogy egyszerre
	 * ne lehessen tobb teleportkapuja mint egy par.
	 */
	private ArrayList<TeleportGate> carriedteleports;
	/**
	 * A Settler konstruktora
	 * A tesztesetek miatt csupan ures BOM-ok vannak hozzaadva.
	 * A jo mukodes vegett vannak inicializalva a listak ezekben 
	 * egy adott teszteseten tul tarolast nem vegzunk.
	 */
	public Settler() 
	{
		Logger.MethodCall("Settler()");
		carriedmaterials = new ArrayList<RawMaterial>();
		carriedteleports = new ArrayList<TeleportGate>();
		bills = new ArrayList<Bill>();
		bills.add(new Bill());
		bills.add(new Bill());
		bills.add(new Bill());
		Logger.MethodReturn("");
	}
	/**
	 * AddTeleport metodus- egy teleportkapu hozzadasara
	 * @param TeleportGate A hozzadni kivant teleportkapu
	 */
	public void AddTeleport(TeleportGate tg) 
	{
		Logger.MethodCall("AddTeleport(TeleportGate tg)");
		this.carriedteleports.add(tg);
		Logger.MethodReturn("void");
	}
	/**
	 * Drill metodus - A telepes aszteroidajanak furasara.
	 * Meghivja az aktualis aszteroida kopeny csokkento metodusat.
	 */
	public void Drill() 
	{
		Logger.MethodCall("Drill()");
		Logger.UserQuestion("Can the settler drill?");
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		if(input.equals("1")) {//TODO tudni kell, hogy meg furhatja-e
			location.IncreaseHoleDepth();
		}
		Logger.MethodReturn("void");
	}
	/**
	 * Mine metodus - A mar lefurt aszteroida banyaszasahoz.
	 * Akkor hajtja vegre a folymatot ha az aszteroidaban van is anyag.
	 */
	public void Mine() 
	{
		Logger.MethodCall("Mine()");
		Logger.UserQuestion("Does it still fit in the settler's invetory?");
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		if(input.equals("1")) {
			if(!location.IsEmpty()) {
				RawMaterial rm=location.GetMaterial();
				AddCarriedMaterial(rm);
				rm.SetAsteroid(null);
				location.DropMaterial();
			}
		}
		Logger.MethodReturn("void");
	}
	/**
	 * Die metodus - A telepes halalakor meghivodo metodus
	 * Minden nala tartolt objektumokat toroli	
	 * (anyagok, teleportkapuk). Es torli magat az aszteroidarol.
	 */
	public void Die() 
	{
		Logger.MethodCall("Die()");
		for(RawMaterial item:carriedmaterials) {
			item.Perish();
		}
		for(TeleportGate item:carriedteleports) {
			item.Explode();
		}
		Logger.MethodReturn("void");
	}
	/**
	 * AddCarriedMaterial metodus - Egy anyag hozzadasa a telepeshez.
	 */
	public void AddCarriedMaterial(RawMaterial material)
	{
		Logger.MethodCall("AddCarriedMaterial()");
		carriedmaterials.add(material);
		Logger.MethodReturn("void");
	}
	/**
	 * Explode metodus - A telepes felrobbantasa
	 * A robot eseteben eltero implementacioja van
	 */
	public void Explode() 
	{
		Logger.MethodCall("Explode()");
		Die();
		Logger.MethodReturn("void");
	}
	/**
	 * BuildRobot metodus - A robot epiteshez
	 * Az anyagok megletet szigoruan a BOM ellenorzi.
	 * A BOM-ok a prototipusban kerulnek rendesen feltoltesre
	 *  jelenleg az ellenorzest az input altal dontjuk el.
	 */
	public void BuildRobot() 
	{
		Logger.MethodCall("BuildRobot()");
		Boolean feltetel1 = bills.get(1).CheckInventory(null);
		if(feltetel1) 
		{
			location.RegisterBeing(new Robot());
			bills.get(1).DeleteFromInventory(null);
		}
		Logger.MethodReturn("void");
	}
	/**
	 * BuildTeleportGatePair metodus - A teleportkapuk epiteshez
	 * Az anyagok megletet szigoruan a BOM ellenorzi.
	 * A BOM-ok a prototipusban kerulnek rendesen feltoltesre
	 * jelenleg az ellenorzest az input altal dontjuk el.
	 * A metodus futasakor ketto teleportkapu kerul a telepeshez.
	 */
	public void BuildTeleportGatePair() 
	{
		Logger.MethodCall("BuildTeleportGatePair()");
		Logger.UserQuestion("Does the settler have any teleportgates?");
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		// Pont ez a sorszamu a billje, perpill ures.
		
		
		Boolean feltetel1 = bills.get(2).CheckInventory(null);
		if(feltetel1&&input.equals("0")) 
		{
			TeleportGate t1=new TeleportGate();
			TeleportGate t2=new TeleportGate();
			
			bills.get(2).DeleteFromInventory(null);
			
			t1.SetSibling(t2);
			t2.SetSibling(t1);
		}
		Logger.MethodReturn("void");
	}
	/**
	 * FillAsteroid metodus - Az anyag visszahelyezeshez
	 * A telepes a mar lefurt kopenyu, es belso anyaggal eppen nem rendelkezo
	 * aszteroidakba helyezhet vissza anyagot.
	 * @param material a visszahelyezendo anyag
	 */
	public void FillAsteroid(RawMaterial material) 
	{
		Logger.MethodCall("FillAsteroid()");

		Logger.UserQuestion("Can the settler fill the asteroid?");
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		if(input.equals("1")) {
			location.SetMaterial(material, this);
		}
		Logger.MethodReturn("void");
	}
	
	/**
	 * Move metodus - Az aszteroidak kozott mozgashoz
	 * Nem veletlenul, de ez akar teleportkapukon keresztul is tortenhet.
	 * @param place A mozgas iranyat ado masik objektum (aszteroida, teleportkapu).
	 */
	public void Move(Place place) 
	{
		Logger.MethodCall("Move(Place place)");
		location.DropBeing(this);
		place.RegisterBeing(this);
		Logger.MethodReturn("void");
	}
	/**
	 * PutTeleportGateOnAsteroid metodus - Teleportkapu lerakashoz
	 * Az aszteroidahoz, amin eppen all a telepes lerakja az egyik teleportkaput.
	 * Persze akkor ha a feltetelek ehhez adottak, vagyis nincs mar teleportkapu az aszteroidan.
	 */
	public void PutTeleportGateOnAsteroid() 
	{
		Logger.MethodCall("PutTeleportGateOnAsteroid()");

		Logger.UserQuestion("Does the settler have a teleportgate?");
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		if(input.equals("1")) {
			TeleportGate t=new TeleportGate();
			t.GetAsteroid();
			Logger.UserQuestion("Is the settler on the same asteroid as the teleportgate's sibling?");
			scanner = new Scanner(System.in);
			String input2 = scanner.nextLine();
			if (!(input2.equals("1"))) {
				Asteroid TestLocation=new Asteroid();
				TestLocation.AddNeighbor(t);
				t.SetAsteroid(TestLocation);
				t.DropOwner();
				DropCarriedTeleport(t);
			}
		}
		Logger.MethodReturn("void");
	}
	/**
	 * Step metodus - A telepes leptetesehez
	 * Itt donteheti el a jatekos, hogy mit fog tenni ebben a korben a telepes.
	 */
	public void Step() 
	{
		Logger.MethodCall("Step()");
		Logger.UserQuestionSettler("What will the settler do?");
		Scanner scanner = new Scanner(System.in);
		String input = "-1";
			input = scanner.nextLine();
			switch(Integer.parseInt(input))
			{
				case 1: Logger.Message("Refer to test 3"); break;
				case 2: Logger.Message("Refer to test 1"); break;
				case 3: Logger.Message("Refer to test 5"); break;
				case 4: Logger.Message("Refer to test 6"); break;
				case 5: Logger.Message("Refer to test 7"); break;
				case 6: Logger.Message("Refer to test 8"); break;
				case 7: Logger.Message("Refer to test 9"); break;
			}
		
		Logger.MethodReturn("void");
	}
	/**
	 * DropCarriedMaterial metodus - Egy anyag eldobashoz
	 * Amikor a telepes meghal, vagy epit valamit, akkor ezzel a metodussal
	 * szabadulhat meg az osszes, vagy az epiteshez szukseges anyagoktol.
	 * @param material Az eldobando anyag
	 */
	public void DropCarriedMaterial(RawMaterial material)
	{
		Logger.MethodCall("DropCarriedMaterial()");
		Logger.MethodReturn("void");
	}
	/**
	 * DropCarriedTeleport metodus - Egy teleportkapu eldobasahoz
	 * Ha a telepes lerak egy teleport kaput, vagy amikor a halalakor el kell
	 *  dobnia az osszeset, akkor hasznaljuk ezt a metodust.
	 * @param teleport Az eldobando teleportkapu objektum
	 */
	public void DropCarriedTeleport(TeleportGate teleport) 
	{
		Logger.MethodCall("DropCarriedTeleport()");
		Logger.MethodReturn("void");
	}
	
}
