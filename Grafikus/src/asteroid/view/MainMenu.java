package asteroid.view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MainMenu extends JPanel
{
	/**
	 * Az osztaly konstruktora
	 * @param newgame: a New game gomb logikaja
	 * @param quitgame: a Quit game gomb logikaja
	 */
	public MainMenu(ActionListener newgame, ActionListener quitgame)
	{
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JButton newgamebutton = new JButton("New game");
		newgamebutton.addActionListener(newgame);
		newgamebutton.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(newgamebutton);
		
		JButton quitgamebutton = new JButton("Quit game");
		quitgamebutton.addActionListener(quitgame);
		quitgamebutton.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(quitgamebutton);
	}
}
