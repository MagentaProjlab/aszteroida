package asteroid.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.geom.Arc2D;

import javax.swing.JPanel;

import asteroid.logic.Asteroid;
import asteroid.logic.Settler;
import asteroid.logic.Uranium;

public class Game extends JPanel
{
	private Settler settler;
	
	public Game(ActionListener al)
	{
		Asteroid a = new Asteroid("asteroid", new Uranium(0), 10, 5);
		Settler s = new Settler("settler", 0, 0, 0, 0, false);
		s.setAsteroid(a);
		a.RegisterBeing(s);
		
		this.ShowSettler(s);
	}
	
	public void ShowSettler(Settler s)
	{
		settler = s;
		this.repaint();
	}
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Color background = new Color(255, 255, 255);
		Color dirt = new Color(139, 69, 19);
		Color hole = new Color(0, 0, 0);
		
		int centerx = 300;
		int centery = 300;
		int diameter = 100;
		int holedepth = settler.getAsteroid().getHoleDepth() * 4;
		int crustthickness = settler.getAsteroid().getCrustThickness() * 4;
		
		//hatter kiszinezese
		g.setColor(background);
		g.fillRect(0, 0, 600, 600);
		
		//aszteroida rajzolas
		g.setColor(dirt);
		g.fillOval(centerx - (diameter + crustthickness)/2, centery - (diameter + crustthickness)/2, diameter + crustthickness, diameter + crustthickness);
		
		//lyuk rajzolas
		Graphics2D g2d = (Graphics2D) g.create();
		int holecenterx = centerx - (diameter + crustthickness)/2;
		int holecentery = centery - (diameter + crustthickness)/2;
		int degree = 15;
		Arc2D holearc = new Arc2D.Double(holecenterx, holecentery, diameter + crustthickness, diameter + crustthickness, 90 - degree, degree * 2, Arc2D.PIE);
		Arc2D dirtarc = new Arc2D.Double(holecenterx + holedepth/2, holecentery + holedepth/2, diameter + crustthickness - holedepth, diameter + crustthickness - holedepth, 90 - degree, degree * 2, Arc2D.PIE);
		
		g2d.setColor(hole);
		g2d.fill(holearc);
		g2d.setColor(dirt);
		g2d.fill(dirtarc);
        g2d.dispose();
		
		//mag rajzolas
		g.setColor(background);
		g.fillOval(centerx - diameter/2, centery - diameter/2, diameter, diameter);
	}
}
