package asteroid.logic;

import java.util.ArrayList;

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
		/*int i, size = inventory.size();
		for(i = 0; i < size; i++)
		{
			int j, size2 = remove.size();
			for(j = 0; j < size2; j++)
			{
				if(remove.get(j).GetUniqueID().compareTo(inventory.get(i).GetUniqueID()) == 0)
				{
					inventory.remove(i);
					remove.remove(j);
					i--;
					j--;
					size--;
					size2--;
				}
			}
		}*/
		
		for(ID item : remove)
		{
			for(int i = 0; i < inventory.size(); i++)
			{
				if(item.GetUniqueID().compareTo(inventory.get(i).GetUniqueID()) == 0)
				{
					inventory.remove(i);
					break;
				}
			}
		}
		
		return inventory;
	}
	
}
