package asteroid.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Arc2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import asteroid.logic.Asteroid;
import asteroid.logic.Bill;
import asteroid.logic.Coal;
import asteroid.logic.ControllerClass;
import asteroid.logic.ID;
import asteroid.logic.Ice;
import asteroid.logic.Iron;
import asteroid.logic.Place;
import asteroid.logic.RawMaterial;
import asteroid.logic.Robot;
import asteroid.logic.SentientBeing;
import asteroid.logic.Settler;
import asteroid.logic.TeleportGate;
import asteroid.logic.Ufo;
import asteroid.logic.Uranium;

public class Game extends JPanel
{
	private Settler settler;
	
	public Game()
	{
		settler = null;
		
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
		ActionListener al_drill = new ActionListener()
		{
			
			public void actionPerformed(ActionEvent e)
			{
				settler.Drill();
				ControllerClass.NotifyLoop();
			}
		};
		
		drill.addActionListener(al_drill);
		ActionListener al_mine = new ActionListener()
		{
			
			public void actionPerformed(ActionEvent e)
			{
				settler.Mine();
				ControllerClass.NotifyLoop();
			}
		};
		
		ActionListener al_noaction = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				ControllerClass.NotifyLoop();
			}
		};
		
		ActionListener buildtel = new ActionListener()
		{
			
			public void actionPerformed(ActionEvent e)
			{
				settler.BuildTeleportGatePair();
				ControllerClass.NotifyLoop();
			}
		};
		
		ActionListener al_buildrobot = new ActionListener()
		{
			
			public void actionPerformed(ActionEvent e)
			{
				settler.BuildRobot("Robot");
				ControllerClass.NotifyLoop();
			}
		};
		ActionListener al_putteleport = new ActionListener()
		{
			
			public void actionPerformed(ActionEvent e)
			{
				JFrame f = new JFrame();
				JButton button = new JButton("Finalize Selection");
				int index;
				JPanel panel = new JPanel();
				ArrayList<String> names = new ArrayList<String>();
				for ( int i = 0; i < settler.getteleports().size();i++) {
					TeleportGate tg = settler.getteleports().get(i);
					names.add(tg.GetUniqueID());
				}
				JComboBox AsteroidList = new JComboBox(names.toArray());
				
				panel.setBounds(40,80,200,200);    
		        panel.setBackground(Color.gray); 
				panel.add(AsteroidList);
				panel.add(button);
				f.add(panel);
	            f.setSize(400,400);
	            f.setLayout(null);    
	            f.setVisible(true);
				ActionListener al_move_fin = new ActionListener()
				{int index = 0;
					public void actionPerformed(ActionEvent e)
					{
						index = AsteroidList.getSelectedIndex();
						settler.PutTeleportGateOnAsteroid("teleport", index);
						f.dispose();
						ControllerClass.NotifyLoop();
					}
				};
				button.addActionListener(al_move_fin);
			}
			
		};
		
		putdownteleport.addActionListener(al_putteleport);
		ActionListener al_putmaterial = new ActionListener()
		{
			
			public void actionPerformed(ActionEvent e)
			{
				JFrame f = new JFrame();
				JButton button = new JButton("Finalize Selection");
				int index;
				JPanel panel = new JPanel();
				ArrayList<String> names = new ArrayList<String>();
				for ( int i = 0; i < settler.getInventory().size();i++) {
					RawMaterial rm = settler.getInventory().get(i);
					names.add(rm.GetUniqueID());
				}
				JComboBox AsteroidList = new JComboBox(names.toArray());
				
				panel.setBounds(40,80,200,200);    
		        panel.setBackground(Color.gray); 
				panel.add(AsteroidList);
				panel.add(button);
				f.add(panel);
	            f.setSize(400,400);
	            f.setLayout(null);    
	            f.setVisible(true);
				ActionListener al_move_fin = new ActionListener()
				{
					int index = 0;
					public void actionPerformed(ActionEvent e)
					{
					
						index = AsteroidList.getSelectedIndex();
						settler.FillAsteroid(settler.getInventory().get(index));
						f.dispose();
						ControllerClass.NotifyLoop();
					}
				};
				button.addActionListener(al_move_fin);
			}
		};
		
		putback.addActionListener(al_putmaterial);
		buildrobot.addActionListener(al_buildrobot);
		buildteleport.addActionListener(buildtel);
		noaction.addActionListener(al_noaction);
		mine.addActionListener(al_mine);
		ActionListener al_move = new ActionListener()
		{
			
			public void actionPerformed(ActionEvent e)
			{
				JFrame f = new JFrame();
				JButton button = new JButton("Finalize Selection");
				int index;
				JPanel panel = new JPanel();
				ArrayList<String> names = new ArrayList<String>();
				Bill teleBill=new Bill();
				teleBill.AddMaterialToBill(new TeleportGate(null, null, null));
				ArrayList<ID> teleList=new ArrayList<ID>();
				
				for ( int i = 0; i < settler.getAsteroid().getNeighbors().size();i++) {
					Place actual = settler.getAsteroid().getNeighbors().get(i);
					teleList.add(settler.getAsteroid().getNeighbors().get(i));
					if(teleBill.CheckInventory(teleList)) {
						TeleportGate tg = (TeleportGate)settler.getAsteroid().getNeighbors().get(i);
						if(tg.GetSibling().GetAsteroid() != null && tg.GetAsteroid() != null) {
							names.add(tg.GetSibling().GetAsteroid().getName());
						}
						
					}else {
						names.add(actual.getName());
					}
					
					
				}
				JComboBox AsteroidList = new JComboBox(names.toArray());
				
				panel.setBounds(40,80,200,200);    
		        panel.setBackground(Color.gray); 
				panel.add(AsteroidList);
				panel.add(button);
				f.add(panel);
	            f.setSize(400,400);
	            f.setLayout(null);    
	            f.setVisible(true);
	            
				ActionListener al_move_fin = new ActionListener()
				{
					int index = 0;
				
					public void actionPerformed(ActionEvent e)
					{
						index = AsteroidList.getSelectedIndex();
						settler.Move(settler.getAsteroid().getNeighbors().get(index));
						f.dispose();
						ControllerClass.NotifyLoop();
					}
				};
				button.addActionListener(al_move_fin);
			}
		};
		
		move.addActionListener(al_move);
	}
	
	public void ShowSettler(Settler s)
	{
		settler = s;
		this.repaint();
	}
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		if(this.settler == null)
		{
			return;
		}
		
		Color background = settler.getAsteroid().GetPerihelion() ? new Color(255, 0, 0) : new Color(255, 255, 255);
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
				beingx += beingsize;
				
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
