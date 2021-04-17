package asteroid;

import java.util.ArrayList;
import java.util.Scanner;

public class Bill
{
	/**
	 * Tárolja a receptet
	 */
	private ArrayList<ID> check;
	private ArrayList<ID> remove;
	
	/**
	 * A bill konstruktora
	 */
	public Bill()
	{
		check = new ArrayList<>();
		remove = new ArrayList<>();
	}
	
	/**
	 * Megvizsgalja, hogy az adott inventoryban van-e eleg a bill anyagaibol
	 * @param inventory: a vizsgalni kivant inventory
	 * @return azzal ter vissza, hogy a parameterkent atadott inventoryban van-e eleg a billbeli anyagokbol
	 */
	public Boolean CheckInventory(ArrayList<ID> inventory) 
	{
		/*if (inventory != null) {
			if (inventory.get(0).GetUniqueID().equals("teleportgate"))
				return true;
			
			if (inventory.size() >= materials.size()) {
				int inCoal = 0;
				int inIce = 0;
				int inIron = 0;
				int inUranium = 0;
				for (int i = 0; i<inventory.size(); ++i) {
					if (inventory.get(i).GetUniqueID().equals("coal"))
						++inCoal;
					if (inventory.get(i).GetUniqueID().equals("ice"))
						++inIce;
					if (inventory.get(i).GetUniqueID().equals("iron"))
						++inIron;
					if (inventory.get(i).GetUniqueID().equals("uranium"))
						++inUranium;
				}
				int maCoal = 0;
				int maIce = 0;
				int maIron = 0;
				int maUranium = 0;
				for (int i = 0; i<materials.size(); ++i) {
					if (materials.get(i).GetUniqueID().equals("coal"))
						++maCoal;
					if (materials.get(i).GetUniqueID().equals("ice"))
						++maIce;
					if (materials.get(i).GetUniqueID().equals("iron"))
						++maIron;
					if (materials.get(i).GetUniqueID().equals("uranium"))
						++maUranium;
				}
				if (inCoal >= maCoal && inIce >= maIce && inIron >= maIron && inUranium >= maUranium)
					return true;
				else
					return false;
			}
			else return false;
		}
		else return false;*/
		
		for(ID i : inventory)
		{
			for(ID i2 : check)
			{
				if(i.GetUniqueID().compareTo(i2.GetUniqueID()) == 0)
				{
					check.remove(i2);
					break;
				}
			}
		}
		
		if(check.size() == 0)
		{
			return true;
		}
		
		return false;
	}
	
	/**
	 * Hozzaad egy anyagot a bill listajahoz
	 * @param material: a hozzaadni kivant anyag
	 */
	public void AddMaterialToBill(ID object) 
	{
		check.add(object);
		remove.add(object);
	}
	
	/**
	 * Kitorli a parameterkent atadott inventorybol a bill anyagait
	 * @param inventory: a modositani kivant inventory
	 * @return a modositott inventory
	 */
	public ArrayList<RawMaterial> DeleteFromInventory(ArrayList<RawMaterial> inventory) 
	{
		/*int inCoal = 0;
		int inIce = 0;
		int inIron = 0;
		int inUranium = 0;
		for (int i = 0; i<inventory.size(); ++i) {
			if (inventory.get(i).GetUniqueID().equals("coal"))
				++inCoal;
			if (inventory.get(i).GetUniqueID().equals("ice"))
				++inIce;
			if (inventory.get(i).GetUniqueID().equals("iron"))
				++inIron;
			if (inventory.get(i).GetUniqueID().equals("uranium"))
				++inUranium;
		}
		
		for (int i = inventory.size(); i>0; --i) {
			if (inventory.get(i).GetUniqueID().equals("coal") && inCoal > 0) {
				--inCoal;
				inventory.remove(i);
			}
			if (inventory.get(i).GetUniqueID().equals("ice") && inIce > 0) {
				--inIce;
				inventory.remove(i);
			}
			if (inventory.get(i).GetUniqueID().equals("iron") && inIron > 0) {
				--inIron;
				inventory.remove(i);
			}
			if (inventory.get(i).GetUniqueID().equals("uranium") && inUranium > 0) {
				--inUranium;
				inventory.remove(i);
			}
		}
		return inventory;*/
		
		int i, size = inventory.size();
		for(i = 0; i < size; i++)
		{
			for(ID i2 : remove)
			{
				if(i2.GetUniqueID().compareTo(inventory.get(i).GetUniqueID()) == 0)
				{
					inventory.remove(i);
					remove.remove(i2);
					i--;
					size--;
				}
			}
		}
		
		return inventory;
	}
	
}
