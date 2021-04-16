package asteroid;

import java.util.ArrayList;
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
	
	/**
	 * Aszteroida konstruktora
	 * Letrehoz ket listat, egyet a lenyek tarolasara, egyet a szomszedok tarolasara
	 */
	public Asteroid()
	{
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
	public void IncreaseHoleDepth() 
	{
		if(HoleDepth != 0)
		{
			HoleDepth--;
		}
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
		Boolean feltetel1 = false;
		if(corematerial != null)
		{
			ArrayList<RawMaterial> corelist = new ArrayList<RawMaterial>();
			corelist.add(corematerial);
			feltetel1 = radBill.CheckInventory(corelist);
		}
		Boolean feltetel2 = false;
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		if(input.equals("1"))
		{
			feltetel2 = true;
		}else if(input.equals("2"))
		{
			feltetel2 = false;
		}
		
		Boolean feltetel3 = this.AtPerihelion();
		
		if((feltetel1 == true && feltetel2 == true && feltetel3 == true) || neighbors.isEmpty())
		{
			for(SentientBeing sb : sentientbeings)
			{
				sb.Explode();
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
		sentientbeings.removeIf(n -> n.)
	}
	
	/**
	 * Hozzaad egy elolenyt az elolenyek listajahoz
	 */
	public void RegisterBeing(SentientBeing being)
	{
		sentientbeings.add(being);
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
		Scanner scanner = new Scanner(System.in);
		Boolean feltetel1 = false;
		String input = scanner.nextLine();
		if(input.equals("1"))
		{
			feltetel1 = true;
		}else if(input.equals("2"))
		{
			feltetel1 = false;
		}
		
		if(this.IsEmpty() == false || feltetel1 == false)
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

	public Integer getHoleDepth() 
	{
		return HoleDepth;
	}
	
	public boolean isMined() 
	{
		if(HoleDepth == CrustThickness) {
			return true;
		}else {
			return false;
		}
	}
}
