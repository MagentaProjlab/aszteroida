package asteroid;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Kiiratasert felelos osztaly
 */
public class Logger
{
	
	private static Scanner input;
	private static Scanner input2;
	private static ArrayList<String> output = new ArrayList<String>();
	private static ArrayList<String> prewrittenoutput = new ArrayList<String>();
	
	Logger(int teszteset)
	{
		String filename = "tests\\in\\" + String.valueOf(teszteset) + "input.txt";
		try
		{
		      File file = new File(filename);
		      input = new Scanner(file);
		} catch (FileNotFoundException e)
		{
		      
		}
		
		filename = "tests\\out\\" + String.valueOf(teszteset) + "output.txt";
		try
		{
		      File file = new File(filename);
		      input2 = new Scanner(file);
		      while(input2.hasNextLine())
		      {
		    	  prewrittenoutput.add(input2.nextLine());
		      }
		      input2.close();
		} catch (FileNotFoundException e)
		{
		      
		}
	}
	
	public static boolean CompareOutputs()
	{
		if(output.size() != prewrittenoutput.size())
		{
			return false;
		}
		
		for(int i = 0; i < output.size(); i++)
		{
			if(output.get(i).compareTo(prewrittenoutput.get(i)) != 0)
			{
				return false;
			}
		}
		
		return true;
	}
	
	public static void Message(String message)
	{
		System.out.println(message);
		output.add(message);
	}
	
	public static boolean hasNextLine()
	{
		return input.hasNextLine();
	}
	public static String NextLine()
	{	
		if(input.hasNextLine())
		{
			return input.nextLine();
		}else
		{
			input.close();
		}
		
		return null;
	}
}
