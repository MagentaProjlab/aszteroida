package asteroid.view;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import asteroid.logic.Asteroid;
import asteroid.logic.ControllerClass;
import asteroid.logic.Ice;
import asteroid.logic.Settler;

/**
 * A jatek kirajzolasaert felelos osztaly
 *
 */
public class View extends JFrame
{
	/**
	 * A kepernyo szelessege
	 */
	private final int width = 600;
	/**
	 * A kepernyo magassaga
	 */
	private final int height = 600;
	/**
	 * Megjelenithez szukseges kartyak
	 */
	public JPanel cards;
	
	/**
	 * Tarolt fomenu
	 */
	private MainMenu mainmenu;
	/**
	 * Tarolt beallitasok
	 */
	private Settings settings;
	/**
	 * Tarolt jatek
	 */
	private static Game game;
	/**
	 * Tarolt kontroller
	 */
	public ControllerClass controller;

	/**
	 * Az osztaly konstruktora, letrehozza az egyes kepernyokhoz tartozo kartyakat
	 * @param c: a view-hoz tartozo kontroller
	 */
	public View(ControllerClass c)
	{
		SetController(c);
		this.setTitle("Aszteroidabanyaszat");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setPreferredSize(new Dimension(width, height));
		this.setResizable(false);
		
		ActionListener newgame = new ActionListener()
		{	
			public void actionPerformed(ActionEvent e)
			{
				CardLayout cardLayout = (CardLayout) cards.getLayout();
				cardLayout.next(cards);
			}
		};
		
		JFrame window = this;
		ActionListener quitgame = new ActionListener()
		{	
			public void actionPerformed(ActionEvent e)
			{
				window.dispose();
			}
		};
		
		JTextField namefield = new JTextField(5);
		ActionListener settingsal = new ActionListener()
		{	
			public void actionPerformed(ActionEvent e)
			{
				CardLayout cardLayout = (CardLayout) cards.getLayout();
				cardLayout.next(cards);
				controller.Init(Integer.parseInt(namefield.getText()));
				
				ControllerClass.StartLoop();
			}
		};
		
		cards = new JPanel(new CardLayout());

		mainmenu = new MainMenu(newgame, quitgame);
		cards.add(mainmenu, "Main menu");

		settings = new Settings(settingsal, namefield);
		cards.add(settings, "Settings");

		game = new Game();
		cards.add(game, "Game");	
	}
	
	/**
	 * Setter a controller mezohoz
	 * @param c: a controller mezo kivant erteke
	 */
	public void SetController(ControllerClass c)
	{
		controller = c;
	}
	
	/**
	 * Masik settlert jelenit meg a kepernyon
	 * @param a megjelenitendo settler
	 */
	static public void ShowSettler(Settler s)
	{
		game.ShowSettler(s);
	}

	/**
	 * Lathatova teszi a grafikus feluletet
	 */
	public void CreateAndShowGUI()
	{
		this.add(cards);
		this.pack();
		this.setVisible(true);
	}
}
