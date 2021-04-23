package asteroid.view;

import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MainMenu extends JPanel
{
	public MainMenu()
	{
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JButton newgamebutton = new JButton("New game");
		newgamebutton.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(newgamebutton);
		
		JButton quitgamebutton = new JButton("Quit game");
		quitgamebutton.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(quitgamebutton);
	}
}
