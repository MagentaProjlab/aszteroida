package asteroid;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Kiiratasert felelos osztaly
 */
public class Logger
{
	
	private static String output;
	private static String prewrittenoutput;
	private static Scanner input;
	private static Scanner input2;
	
	Logger(int teszteset)
	{
		String filename = "tests\\in" + String.valueOf(teszteset) + "input.txt";
		try
		{
		      File file = new File(filename);
		      input = new Scanner(file);
		} catch (FileNotFoundException e)
		{
		      
		}
		output = "";
		
		filename = "tests\\out" + String.valueOf(teszteset) + "output.txt";
		try
		{
		      File file = new File(filename);
		      input2 = new Scanner(file);
		      while(input2.hasNextLine())
		      {
		    	  prewrittenoutput += input2.nextLine();
		      }
		      input2.close();
		} catch (FileNotFoundException e)
		{
		      
		}
	}
	
	public static void Message(String message)
	{
		System.out.println(message);
		output += message;
	}
	
	/*private void SetFile(String filename)
	{
		try
		{
		      File file = new File(filename);
		      input = new Scanner(file);
		} catch (FileNotFoundException e)
		{
		      
		}
	}*/
	
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
