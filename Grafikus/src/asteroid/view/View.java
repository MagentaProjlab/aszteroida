package asteroid.view;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import asteroid.logic.ControllerClass;
import asteroid.logic.Settler;

public class View extends JFrame
{
	private int width = 600;
	private int height = 600;
	public JPanel cards;
	
	private MainMenu mainmenu;
	private Settings settings;
	private static Game game;
	public ControllerClass controller;

	public View()
	{
		this.setTitle("Aszteroidabanyaszat");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setPreferredSize(new Dimension(width, height));
		this.setResizable(false);

		ActionListener mainmenual = new ActionListener()
		{	
			public void actionPerformed(ActionEvent e)
			{
				CardLayout cardLayout = (CardLayout) cards.getLayout();
				cardLayout.next(cards);
			}
		};
		
		cards = new JPanel(new CardLayout());

		mainmenu = new MainMenu(mainmenual);
		cards.add(mainmenu, "Main menu");

		settings = new Settings(this);
		cards.add(settings, "Settings");

		//kell majd action listener, egyel�re null
		game = new Game(null);
		cards.add(game, "Game");	
	}
	
	private void Command(String s)
	{
		
	}
	
	public void SetController(ControllerClass c)
	{
		controller = c;
	}
	
	static public void ShowSettler(Settler s)
	{
		game.ShowSettler(s);
	}

	public void CreateAndShowGUI()
	{
		this.add(cards);
		this.pack();
		this.setVisible(true);
	}
}
