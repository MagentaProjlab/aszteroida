package asteroid.view;

import java.awt.Graphics;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import asteroid.logic.Settler;

public class Game extends JPanel
{
	private Settler settler;
	
	public Game(ActionListener a)
	{
		
	}
	
	public void showSettler(Settler s)
	{
		//nem rajzolós string kiiratós logika helye
		
		///////////////////////////////////////////
		
		settler = s;
		this.repaint();
	}
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
	}
}
