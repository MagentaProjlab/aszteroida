package asteroid;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Asteroid extends Place 
{
	private Integer CrustThickness;
	private Integer HoleDepth;
	private Boolean CoreIsEmpty;
	private RawMaterial corematerial;
	private ArrayList<SentientBeing> sentientbeings;
	private ArrayList<Place> neighbors;
	private BillOfMaterials radBill;
	private String name;
	
	

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
	public void SetBill(BillOfMaterials radbill)
	{
		this.radBill = radbill;
	}
	
	/**
	 * Csokkenti az aszteroida kergenek a vastagsagat 
	 */
	public boolean IncreaseHoleDepth() 
	{
		if(HoleDepth != 0)
		{
			HoleDepth--;
			return true;
		}
		return false;
		CheckPerihelionReaction();
	}
	
	/**
	 * Felrobbantja az aszteroidat ha teljesul az osszes feltetel
	 * Ha az aszteroida magja uran
	 * Ha Holedepth = CrustTickness
	 * Es napkozelben van az asztroida 
	 */
	public void Explode()
	{
		Boolean exp = false;
		if(corematerial != null)
		{
			ArrayList<RawMaterial> corelist = new ArrayList<RawMaterial>();
			corelist.add(corematerial);
			exp = radBill.CheckInventory(corelist);
		}
		
		if((exp == true) && neighbors.isEmpty())
		{
			for(SentientBeing sb : sentientbeings)
			{
				sb.Explode();
			}
			corematerial.Perish();
		} else if ((exp == true) && !neighbors.isEmpty()) {
			for(SentientBeing sb : sentientbeings)
			{
				if(!sb.getName().equals("robot"))
					sb.Explode();
				else if(sb.getName().equals("robot")) {
					Random rand = new Rand();
					sb.setAsteroid(this.neighbors[rand.nextInt()]);
				}
			}
			corematerial.Perish();
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
	 * Lekerdezi az aszteroidatol, hogy napkozelben van-e
	 * @return - bool
	 */
	public Boolean AtPerihelion()
	{
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		if(input.equals("1"))
		{
			return true;
		}else if(input.equals("2"))
		{
			return false;
		}
		return false;
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
		if(this.IsEmpty() == false)
		{
			for(SentientBeing sb : sentientbeings)
			{
				sb.Die();
			}
		}
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
	 * Lenyek leptetese
	 */
	public void StepBeings()
	{
		for(SentientBeing sb : sentientbeings)
		{
			sb.Step();
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
}
