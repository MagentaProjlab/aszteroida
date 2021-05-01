package asteroid.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.geom.Arc2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import asteroid.logic.Asteroid;
import asteroid.logic.Bill;
import asteroid.logic.Coal;
import asteroid.logic.ID;
import asteroid.logic.Ice;
import asteroid.logic.Iron;
import asteroid.logic.RawMaterial;
import asteroid.logic.Robot;
import asteroid.logic.SentientBeing;
import asteroid.logic.Settler;
import asteroid.logic.Ufo;
import asteroid.logic.Uranium;

public class Game extends JPanel
{
	private Settler settler;
	
	public Game(ActionListener al)
	{
		//gombok a parancsokhoz
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(2, 4));
		
		JButton move = new JButton("Move");
		buttons.add(move);
		
		JButton drill = new JButton("Drill");
		buttons.add(drill);
		
		JButton noaction = new JButton("No action");
		buttons.add(noaction);
		
		JButton mine = new JButton("Mine");
		buttons.add(mine);
		
		JButton buildteleport = new JButton("Build teleport");
		buttons.add(buildteleport);
		
		JButton putdownteleport = new JButton("Put down teleport");
		buttons.add(putdownteleport);
		
		JButton buildrobot = new JButton("Build robot");
		buttons.add(buildrobot);
		
		JButton putback = new JButton("Put back material");
		buttons.add(putback);
		
		this.setLayout(new BorderLayout());
		this.add(buttons, BorderLayout.SOUTH);
		//
		
		//teszthez cuccok
		Asteroid a = new Asteroid("asteroid", new Ice(), 10, 6);
		Settler s = new Settler("settler", 3, 3, 2, 2, false);
		s.setAsteroid(a);
		a.RegisterBeing(s);
		
		a.RegisterBeing(new Settler(null));
		a.RegisterBeing(new Robot(null, null));
		a.RegisterBeing(new Ufo(null, null));
		
		this.ShowSettler(s);
		//
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
		int centery = 225;
		int diameter = 100;
		int holedepth = settler.getAsteroid().getHoleDepth() * 4;
		int crustthickness = settler.getAsteroid().getCrustThickness() * 4;
		
		//hatter kiszinezese
		g.setColor(background);
		g.fillRect(0, 0, 600, 600);
		//
		
		//szoveges adatok kiirasa
		g.setColor(new Color(0, 0, 0));
		String nametext1 = "Settler name: " + settler.getName();
		String nametext2 = "Asteroid name: " + settler.getAsteroid().getName();
		String crusttext = "Crust thickness: " + crustthickness/4;
		String holetext = "Hole depth: " + holedepth/4;
		g.drawString(nametext1, 10, 20);
		g.drawString(nametext2, 10, 40);
		g.drawString(crusttext, 10, 60);
		g.drawString(holetext, 10, 80);
		//
		
		//aszteroida rajzolas
		g.setColor(dirt);
		g.fillOval(centerx - (diameter + crustthickness)/2, centery - (diameter + crustthickness)/2, diameter + crustthickness, diameter + crustthickness);
		//
		
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
		//
        
		//mag hatter rajzolas
		g.setColor(background);
		g.fillOval(centerx - diameter/2, centery - diameter/2, diameter, diameter);
		//
		
		//mag anyag rajzolas
		Bill uranium = new Bill();
		uranium.AddMaterialToBill(new Uranium(0));
		Bill ice = new Bill();
		ice.AddMaterialToBill(new Ice());
		Bill iron = new Bill();
		iron.AddMaterialToBill(new Iron());
		Bill coal = new Bill();
		coal.AddMaterialToBill(new Coal());
		
		RawMaterial core = settler.getAsteroid().GetMaterial();
		if(core != null)
		{
			ArrayList<ID> list = new ArrayList<ID>();
			list.add(core);
			
			Toolkit t = Toolkit.getDefaultToolkit();
			int size = 75;
			
			if(uranium.CheckInventory(list))
			{
				Image i = t.getImage("icons//uranium.png"); 
		        g.drawImage(i, centerx - size/2, centery - size/2, size, size, this);
			}else if(ice.CheckInventory(list))
			{
				Image i = t.getImage("icons//ice.png"); 
		        g.drawImage(i, centerx - size/2, centery - size/2, size, size, this);
			}else if(iron.CheckInventory(list))
			{
				Image i = t.getImage("icons//iron.png"); 
		        g.drawImage(i, centerx - size/2, centery - size/2, size, size, this);
			}else if(coal.CheckInventory(list))
			{
				Image i = t.getImage("icons//coal.png"); 
		        g.drawImage(i, centerx - size/2, centery - size/2, size, size, this);
			}
		}
		//
		
		//inventory rajzolas
		g.setColor(new Color(0, 0, 0));
		int invx = 10;
		int invy = 360;
		int invsize = 40;
		g.drawString("inventory: ", invx, invy);
		invx += 10;
		invy -= invsize;
		
		ArrayList<RawMaterial> inventory = settler.getInventory();
		for(int i = 0; i < inventory.size(); i++)
		{
			Bill u = new Bill();
			u.AddMaterialToBill(new Uranium(0));
			Bill ic = new Bill();
			ic.AddMaterialToBill(new Ice());
			Bill ir = new Bill();
			ir.AddMaterialToBill(new Iron());
			Bill c = new Bill();
			c.AddMaterialToBill(new Coal());
			
			ArrayList<ID> list = new ArrayList<ID>();
			list.add(inventory.get(i));
				
			Toolkit t = Toolkit.getDefaultToolkit();
			invx += (int)(4.0 / 3.0 * ((float)invsize));
			
			if(u.CheckInventory(list))
			{
				Image im = t.getImage("icons//uranium.png"); 
			    g.drawImage(im, invx, invy + invsize/2, invsize, invsize, this);
			}else if(ic.CheckInventory(list))
			{
				Image im = t.getImage("icons//ice.png"); 
			    g.drawImage(im, invx, invy + invsize/2, invsize, invsize, this);
			}else if(ir.CheckInventory(list))
			{
				Image im = t.getImage("icons//iron.png"); 
			    g.drawImage(im, invx, invy + invsize/2, invsize, invsize, this);
			}else if(c.CheckInventory(list))
			{
				Image im = t.getImage("icons//coal.png"); 
			    g.drawImage(im, invx, invy + invsize/2, invsize, invsize, this);
			}
		}
		//
		
		//being rajzolas
		g.setColor(new Color(0, 0, 0));
		int beingx = 10;
		int beingy = 420;
		int beingsize = 50;
		g.drawString("beings: ", beingx, beingy);
		beingx -= 10;
		beingy -= beingsize;
		
		ArrayList<SentientBeing> beings = settler.getAsteroid().getBeings();
		for(int i = 0; i < beings.size(); i++)
		{
			Bill settler = new Bill();
			settler.AddMaterialToBill(new Settler(null));
			Bill robot = new Bill();
			robot.AddMaterialToBill(new Robot(null, null));
			Bill ufo = new Bill();
			ufo.AddMaterialToBill(new Ufo(null, null));
			
			ArrayList<ID> list = new ArrayList<ID>();
			list.add(beings.get(i));
				
			Toolkit t = Toolkit.getDefaultToolkit();
			beingx += (int)(4.0 / 3.0 * ((float)beingsize));
			
			if(settler.CheckInventory(list))
			{
				Image im = t.getImage("icons//settler.png"); 
			    g.drawImage(im, beingx, beingy + beingsize/2, beingsize, beingsize, this);
			}else if(robot.CheckInventory(list))
			{
				Image im = t.getImage("icons//robot.png"); 
			    g.drawImage(im, beingx, beingy + beingsize/2, beingsize, beingsize, this);
			}else if(ufo.CheckInventory(list))
			{
				Image im = t.getImage("icons//ufo.png"); 
			    g.drawImage(im, beingx, beingy + beingsize/2, beingsize, beingsize, this);
			}
		}
		//
	}
}
