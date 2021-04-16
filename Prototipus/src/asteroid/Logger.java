package asteroid;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Kiiratasert felelos osztaly
 */
public class Logger {
	
	private String output;
	private static Scanner input;
	
	Logger(int teszteset)
	{
		String filename = String.valueOf(teszteset) + ".txt";
		SetFile(filename);
	}
	
	public void Message(String message)
	{
		System.out.println(message);
		output += message + "\n";
	}
	
	private void SetFile(String filename)
	{
		try
		{
		      File file = new File(filename);
		      input = new Scanner(file);
		} catch (FileNotFoundException e)
		{
		      
		}
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
