package asteroid.logic;

import java.util.ArrayList;

import asteroid.view.View;


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
	
	public ArrayList<TeleportGate> getteleports(){
		return this.carriedteleports;
	}
	/**
	 * Vissza adja a leny nevet, amit letrehozaskor kapott es a tipusat
	 * @return A leny neve
	 */
	public  String getFullName() {
		return "[Settler:"+name+"]";
	}
	
	/**
	 * A prototipusnal felmerult szuksege vegett, egy Stringban tarolt nev amivel a Settler kivalaszthato.
	 */
	private String name;
	/**
	 * Utobbi attributum getter-e
	 */
	public String getName() {
		return name;
	}
	
	public ArrayList<RawMaterial> getInventory()
	{
		return carriedmaterials;
	}
	
	/**
	 * A Settler konstruktora
	 * A tesztesetek miatt csupan ures BOM-ok vannak hozzaadva.
	 * A jo mukodes vegett vannak inicializalva a listak ezekben 
	 * egy adott teszteseten tul tarolast nem vegzunk.
	 */
	public Settler(String _name) 
	{
		carriedmaterials = new ArrayList<RawMaterial>();
		carriedteleports = new ArrayList<TeleportGate>();

		//FIXME Nincsenek neki bill-jei
		bills = new ArrayList<Bill>();
		
		name=_name;
	}
	
	public Settler(String name, int coal, int ice, int iron, int uranium, boolean tg)
	{
		carriedmaterials = new ArrayList<RawMaterial>();
		carriedteleports = new ArrayList<TeleportGate>();
		
		this.name = name;
		
		for(int i = 0; i < coal; i++)
		{
			carriedmaterials.add(new Coal());
		}
		
		for(int i = 0; i < ice; i++)
		{
			carriedmaterials.add(new Ice());
		}
		
		for(int i = 0; i < iron; i++)
		{
			carriedmaterials.add(new Iron());
		}
		
		for(int i = 0; i < uranium; i++)
		{
			carriedmaterials.add(new Uranium(0));
		}
		
		if(tg)
		{
			TeleportGate gate1 = new TeleportGate(null, this, "t1");
			TeleportGate gate2 = new TeleportGate(null, this, "t2");
			gate1.SetSibling(gate2);
			gate2.SetSibling(gate1);

			carriedteleports.add(gate1);
			carriedteleports.add(gate2);
		}
	}
	
	/**
	 * AddTeleport metodus- egy teleportkapu hozzadasara
	 * @param TeleportGate A hozzadni kivant teleportkapu
	 */
	public void AddTeleport(TeleportGate tg) 
	{
		this.carriedteleports.add(tg);
		//Masik metodus ir Logger-be elotte
	}
	/**
	 * Drill metodus - A telepes aszteroidajanak furasara.
	 * Meghivja az aktualis aszteroida kopeny csokkento metodusat.
	 */
	public void Drill() 
	{		
		if(!location.isDrilled()) {
			location.IncreaseHoleDepth();
			Logger.Message("[Settler: "+name+"] has drilled "+location.getName()+".");
		}
		else {
			Logger.Message("[Settler: "+name+"] has failed to drill "+location.getName()+".");
		}
	}
	/**
	 * Mine metodus - A mar lefurt aszteroida banyaszasahoz.
	 * Akkor hajtja vegre a folymatot ha az aszteroidaban van is anyag.
	 */
	public void Mine() 
	{
		if(carriedmaterials.size()<10 && !location.IsEmpty() && location.isDrilled()) {
			RawMaterial rm=location.GetMaterial();
			AddCarriedMaterial(rm);
			rm.SetAsteroid(null);
			location.DropMaterial();
			Logger.Message("[Settler: "+name+"] has mined "+location.getName()+""+".");
		}
		else {
			Logger.Message("[Settler: "+name+"] has failed to mine "+location.getName()+""+".");
		}
	}
	/**
	 * Die metodus - A telepes halalakor meghivodo metodus
	 * Minden nala tartolt objektumokat toroli	
	 * (anyagok, teleportkapuk). Es torli magat az aszteroidarol.
	 */
	public void Die() 
	{
		location.DropBeing(this);
		int max = carriedmaterials.size();
		int iter = 0;
		while(iter < max ) {
			this.carriedmaterials.get(iter).Perish();
			this.carriedmaterials.remove(iter);
			max = carriedmaterials.size();
		}
		iter=0;
		max = carriedteleports.size();
		while(iter < max ) {
			carriedteleports.get(iter).Explode();
			max = carriedteleports.size();
		}
		//Doku szerint nem irat ki
		//Logger.Message("[Settler: "+name+"] has died ");
	}
	/**
	 * AddCarriedMaterial metodus - Egy anyag hozzadasa a telepeshez.
	 * Fontos, a telepes zsebenek telitettseget, az ezt a metodust meghivo, mine metodusban ellenorizzuk!
	 */
	public void AddCarriedMaterial(RawMaterial material)
	{
		carriedmaterials.add(material);
		//ittnem kell kiirni, mert arrol az ezt meghivo metodus gondoskodnik
	}
	/**
	 * Explode metodus - A telepes felrobbantasa
	 * A robot eseteben eltero implementacioja van
	 */
	public void Explode() 
	{
		Logger.Message("[Settler: "+name+"] has exploded"+".");
		Die();
		
	}
	/**
	 * BuildRobot metodus - A robot epiteshez
	 * Az anyagok megletet szigoruan a BOM ellenorzi.
	 * A BOM-ok a prototipusban kerulnek rendesen feltoltesre
	 *  jelenleg az ellenorzest az input altal dontjuk el.
	 */
	public void BuildRobot(String robot_name) 
	{
		//FIXME Nincsenek neki bill-jei!!! Amig ez nincs megoldva hagyd false-on, 
		//ha meg van ird at a megfelelo bill check inventoryjara
		Bill b = new Bill();
		b.AddMaterialToBill(new Iron());
		b.AddMaterialToBill(new Uranium(0));
		b.AddMaterialToBill(new Coal());
		ArrayList<ID> l = new ArrayList<ID>();
		for(RawMaterial rm : carriedmaterials)
		{
			l.add(rm);
		}
		
		if(b.CheckInventory(l) == true) 
		{
			Robot r = new Robot(location, robot_name);
			location.RegisterBeing(r);
			carriedmaterials = b.DeleteFromInventory(carriedmaterials);
			Logger.Message("[Settler: "+name+"] has built robot"+".");
		}
		else {
			Logger.Message("[Settler: "+name+"] has failed to build a robot"+".");
		}
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
		//FIXME Nincsenek neki bill-jei!!! Amig ez nincs megoldva hagyd false-on, 
		//ha meg van ird at a megfelelo bill check inventoryjara
		Bill b = new Bill();
		b.AddMaterialToBill(new Iron());
		b.AddMaterialToBill(new Iron());
		b.AddMaterialToBill(new Uranium(0));
		b.AddMaterialToBill(new Ice());
		ArrayList<ID> l = new ArrayList<ID>();
		for(RawMaterial rm : carriedmaterials)
		{
			l.add(rm);
		}
		
		if(b.CheckInventory(l) == true) 
		{
			//itt meg a teleportgate nem tud semmit, mivel nincs lerakva.
			//Csak a tesojat
			TeleportGate t1=new TeleportGate(null,this,null);
			TeleportGate t2=new TeleportGate(null,this,null);
			carriedmaterials = b.DeleteFromInventory(carriedmaterials);
			t1.SetSibling(t2);
			t2.SetSibling(t1);
			AddTeleport(t1);
			AddTeleport(t2);
			Logger.Message("[Settler: "+name+"] has built a teleportgate pair.");

		}
		else {
			Logger.Message("[Settler: "+name+"] has failed to build a teleportgate pair"+".");
		}
		
	}
	/**
	 * FillAsteroid metodus - Az anyag visszahelyezeshez
	 * A telepes a mar lefurt kopenyu, es belso anyaggal eppen nem rendelkezo
	 * aszteroidakba helyezhet vissza anyagot.
	 * @param material a visszahelyezendo anyag
	 */
	public void FillAsteroid(RawMaterial material) 
	{
		if(location.isDrilled()&&location.IsEmpty()) {
			location.SetMaterial(material, this);
			Logger.Message("[Settler: "+name+"] has put material back to "+location.getName()+".");
		}
		else {
			Logger.Message("[Settler: "+name+"] has failed to put material back to "+location.getName()+".");
		}
	}
	
	/**
	 * Move metodus - Az aszteroidak kozott mozgashoz
	 * Nem veletlenul, de ez akar teleportkapukon keresztul is tortenhet.
	 * @param place A mozgas iranyat ado masik objektum (aszteroida, teleportkapu).
	 */
	public void Move(Place place) 
	{
		location.DropBeing(this);
		place.RegisterBeing(this);
		
	}
	/**
	 * PutTeleportGateOnAsteroid metodus - Teleportkapu lerakashoz
	 * Az aszteroidahoz, amin eppen all a telepes lerakja az egyik teleportkaput.
	 * Persze akkor ha a feltetelek ehhez adottak, vagyis nincs mar a teleportkapu parja az aszteroidan.
	 * @param idx Hanyadik teleportkaput rakjuk le, 0-tol kezdunk!
	 */
	public void PutTeleportGateOnAsteroid(String name,int idx) 
	{
		if(carriedteleports.size()>=idx+1)
		{
			TeleportGate t=carriedteleports.get(idx);
			//ezt a "t"-t csak azert  vettem fel, hogy a kovi sor ne legyen olyan 
			//hosszu, es hogy konzisztens maradjak az eredeti koddal
			if(t.GetSibling().GetAsteroid() ==null || !t.GetSibling().GetAsteroid().equals(location))
			{
				t.SetName(name);
				t.SetAsteroid(location);
				location.AddNeighbor(t);
				t.DropOwner();
				DropCarriedTeleport(t);
			}
			Logger.Message("[Settler: "+name+"] has put down teleport "+t.getName()+".");
		}
		else
		{
			Logger.Message("[Settler: "+name+"] has put down teleport "+".");
		}
	}
	/**
	 * Step metodus - A telepes leptetesehez
	 * Itt donteheti el a jatekos, hogy mit fog tenni ebben a korben a telepes.
	 */
	public void Step()
	{
		View.ShowSettler(this);
		this.setStepped(true);
		
	}
	/**
	 * DropCarriedMaterial metodus - Egy anyag eldobashoz
	 * Amikor a telepes meghal, vagy epit valamit, akkor ezzel a metodussal
	 * szabadulhat meg az osszes, vagy az epiteshez szukseges anyagoktol.
	 * @param material Az eldobando anyag
	 */
	public void DropCarriedMaterial(RawMaterial material)
	{
		carriedmaterials.remove(material);		
	}
	/**
	 * DropCarriedTeleport metodus - Egy teleportkapu eldobasahoz
	 * Ha a telepes lerak egy teleport kaput, vagy amikor a halalakor el kell
	 * dobni a az osszeset, akkor hasznaljuk ezt a metodust.
	 * @param teleport Az eldobando teleportkapu objektum
	 */
	public void DropCarriedTeleport(TeleportGate teleport) 
	{
		carriedteleports.remove(teleport);		
	}
	
	public void AddTeleportGate(TeleportGate gate)
	{
		carriedteleports.add(gate);
	}
	
	public String GetUniqueID()
	{
		return "Settler";
	}
}
