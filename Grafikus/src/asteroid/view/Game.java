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
	
	public void ShowSettler(Settler s)
	{
		//nem rajzol�s string kiirat�s logika helye
		
		///////////////////////////////////////////
		
		settler = s;
		this.repaint();
	}
	
	protected void PaintComponent(Graphics g)
	{
		super.paintComponent(g);
	}
}
