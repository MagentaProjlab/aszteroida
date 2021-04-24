package asteroid.view;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class View extends JFrame
{
	private int width = 600;
	private int height = 600;
	public JPanel cards;

	public View()
	{
		this.setTitle("Aszteroidabanyaszat");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setPreferredSize(new Dimension(width, height));
		this.setResizable(false);

		ActionListener al = new ActionListener()
		{	
			public void actionPerformed(ActionEvent e)
			{
				CardLayout cardLayout = (CardLayout) cards.getLayout();
				cardLayout.next(cards);
			}
		};
		
		cards = new JPanel(new CardLayout());

		MainMenu mainmenu = new MainMenu(al);
		cards.add(mainmenu, "Main menu");

		Settings settings = new Settings(al);
		cards.add(settings, "Settings");

		Game game = new Game();
		cards.add(game, "Game");
	}

	public void createAndShowGUI()
	{
		this.add(cards);
		this.pack();
		this.setVisible(true);
	}
}
