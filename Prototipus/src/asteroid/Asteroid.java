package asteroid;

import java.util.ArrayList;
import java.util.Random;

public class Asteroid extends Place 
{
	private Integer CrustThickness;
	private Integer HoleDepth;
	private RawMaterial corematerial;
	private ArrayList<SentientBeing> sentientbeings;
	private ArrayList<Place> neighbors;
	private Bill radBill;
	private String name;
	/**A prototipus miatt szukseges ez a cucc, de nagyon vaa
	 * 
	 */
	private boolean exploded = false;
	public boolean getExploded() {
		return exploded;
	}
	
	/**
	 * Aszteroida konstruktora
	 * Letrehoz ket listat, egyet a lenyek tarolasara, egyet a szomszedok tarolasara
	 */
	public Asteroid(String n, RawMaterial m, int c, int h)
	{
		name = n;
		corematerial = m;
		CrustThickness = c;
		HoleDepth = h;
		sentientbeings = new ArrayList<SentientBeing>();
		neighbors = new ArrayList<Place>();
		if(corematerial != null)
		{
			corematerial.SetAsteroid(this);
		}
		exploded = false;
	}
	
	/**
	 * Beallitja az aszteroida magjanak a tipusat
	 * @param rm - parameterkent kapott nyersanyag
	 */
	public void SetCore(RawMaterial rm)
	{
		this.corematerial = rm;
	}
	
	/**
	 * Beallitja az aszteroidahoz tartozo BillOfMaterials-t
	 * @param radbill - BOM
	 */
	public void SetBill(Bill radbill)
	{
		this.radBill = radbill;
	}
	
	/**
	 * Eldonti az aszteroidarol, a nyeranyag tipusatol fuggoen,
	 * hogy napkozelben mi tortenik vele
	 */
	public void CheckPerihelionReaction()
	{
		if (corematerial != null)
			corematerial.PerihelionReaction();
	}
	
	/**
	 * Csokkenti az aszteroida kergenek a vastagsagat 
	 */
	public boolean IncreaseHoleDepth() 
	{
		if(HoleDepth != 0)
		{
			HoleDepth--;
			CheckPerihelionReaction();
			return true;
		}
		CheckPerihelionReaction();
		return false;
	}
	
	/**
	 * Felrobbantja az aszteroidat ha teljesul az osszes feltetel
	 * Ha az aszteroida magja uran
	 * Ha Holedepth = CrustTickness
	 * Es napkozelben van az asztroida 
	 */
	public void Explode()
	{
		exploded = true;
		Boolean exp = false;
		if(corematerial != null)
		{
			ArrayList<ID> corelist = new ArrayList<ID>();
			corelist.add(corematerial);
			Bill radBill=new Bill();
			radBill.AddMaterialToBill(new Uranium(0));
			exp = radBill.CheckInventory(corelist);
		}
		
		if((exp == true) && neighbors.isEmpty())
		{
			for(SentientBeing sb : sentientbeings)
			{
				sb.Explode();
			}
			//corematerial.Perish();
		} else if ((exp == true) && !neighbors.isEmpty()) {
			int maxsize = sentientbeings.size();
			int _maxsize = sentientbeings.size();
			int iter = 0;
			while(iter < maxsize) {
				SentientBeing sb = sentientbeings.get(iter);
				sb.Explode();
				maxsize = sentientbeings.size();
				if(maxsize == _maxsize)
					iter++;
				
				}
			//corematerial.Perish();
		}for(int i = 0; i< this.neighbors.size(); i++) {
			if(this.neighbors.get(i).GetUniqueID().equals("Asteroid")) {
				Asteroid a = (Asteroid)this.neighbors.get(i);
				if(a.getNeighbors().size() <= 1 && a.getExploded() == false) {
					a.Explode();
				}
			}
		}
		for(int i = 0; i< this.neighbors.size(); i++) {
			if(this.neighbors.get(i).GetUniqueID().equals("teleportgate")) {
				TeleportGate a = (TeleportGate)this.neighbors.get(i);
					a.Explode();
			}
		}
	}
	
	/**
	 * Eltavolitja a parameterkent kapott elolenyt az elolenyek listajabol
	 * @param being - leny, amit eltunik az aszteroidarol
	 */
	public void DropBeing(SentientBeing being)
	{
		sentientbeings.removeIf(sb -> (sb.getName().equals(being.getName())));
	}
	
	/**
	 * Hozzaad egy elolenyt az elolenyek listajahoz
	 */
	public void RegisterBeing(SentientBeing being)
	{
		sentientbeings.add(being);
		being.setAsteroid(this);
	}
	
	/**
	 * A kibanyaszott nyersanyagot, vagyis a magjat eltavolitja
	 */
	public void DropMaterial() 
	{
		corematerial = null;
	}
	
	/**
	 * A mag tipusanak a gettere
	 * @return - nyersanyag
	 */
	public RawMaterial GetMaterial()
	{
		return this.corematerial;
	}
	
	/**
	 * Lekerdezi, hogy az adott aszteroida magja ures-e
	 * @return - bool
	 */
	public Boolean IsEmpty()
	{
		if(corematerial == null) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Beallitja az aszteroida magjanak tipusat
	 * @param material - a nyersanyag ami az aszteroidaba kerul
	 * @param settler - a telepes aki visszahelyezi a nyersanyagot
	 */
	public void SetMaterial(RawMaterial material, Settler settler)
	{
		material.SetAsteroid(this);
		settler.DropCarriedMaterial(material);
		SetCore(material);
		CheckPerihelionReaction();
	}
	
	/**
	 * Szomszed eltavolasa a listabol.
	 * @param neighbor
	 */
	public void DropNeighbor(Place neighbor)
	{
		neighbors.removeIf(n -> (n.getName().equals(neighbor.getName())));
	}
		
	/**
	 * Aszteroida szomszedjanak hozzaadasa
	 * @param neighbor - aszteroida szomszedja
	 */
	public void AddNeighbor(Place neighbor)
	{
		neighbors.add(neighbor);
	}
	
	/**
	 * Napvihar minden feltetel teljesulese eseten
	 * Ha Holedepth = CrustTickness
	 * Es az aszteroida nem ures
	 * Akkor az aszteroidan levo telepesek meghalnak
	 * A robotok vagy atkerulnek egy masik aszteroidara vagy felrobbanak
	 */
	public void SolarWindDeath()
	{
		if(this.GetMaterial() != null)
		{
			int iter = 0;
			int maxb = sentientbeings.size();
			while(iter < maxb)
			{
				SentientBeing sb = sentientbeings.get(iter);
				sb.Die();
				maxb = sentientbeings.size();
			}
		}
	}
	
	/**
	 * Lenyek leptetese
	 */
	public void StepBeings()
	{
		Logger.Message("[Asteroid: "+this.name+"] has been selected to step.");
		int maxnumber = sentientbeings.size();
		int iter = 0;
		while(iter < maxnumber) {
			if(sentientbeings.get(iter).getStepped() == false)
			{
				sentientbeings.get(iter).Step();
			}
			int nmaxnumber = sentientbeings.size();
			if(nmaxnumber < maxnumber) {
				maxnumber = nmaxnumber;
			}else {
				iter++;
			}
		}
		this.resetStepped();
	}
	
	public void resetStepped()
	{
		for(SentientBeing sb : sentientbeings)
		{
			sb.setStepped(false);
		}
	}

	/**
	 * Szomszedok gettere
	 * @return - szomszed
	 */
	public Place GetNeighbour()
	{
		return this.neighbors.get(0);
	}

	/**
	 * a lyuk melysegenek a gettere
	 * @return - melyseg
	 */
	public Integer getHoleDepth() 
	{
		return HoleDepth;
	}
	
	/**
	 * ki van-e furva az aszteroida
	 * @return - boolean
	 */
	public boolean isDrilled() 
	{
		if(HoleDepth == CrustThickness) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * aszteroida kopenyvastagsag getter
	 * @return - kopeny vastagsag
	 */
	public Integer getCrustThickness() 
	{
		return CrustThickness;
	}
	
	/**
	 * aszteroida szomszedainak listajanak gettere
	 * @return - list
	 */
	public ArrayList<Place> getNeighbors() {
		return neighbors;
	}
	
	/**
	 * aszteroida nevenek gettere
	 * @return - nev
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * aszteroidan tartozkodo lenyek listajanak getter
	 * @return - lenyek list
	 */
	public ArrayList<SentientBeing> getBeings()
	{
		return sentientbeings;
	}
	
	/**
	 * aszteroida ID-nak gettere
	 * @return - Astreoid ID
	 */
	public String GetUniqueID()
	{
		return "Asteroid";
	}
}
