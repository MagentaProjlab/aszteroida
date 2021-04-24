package asteroid.view;

import java.awt.Component;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MainMenu extends JPanel
{
	public MainMenu(ActionListener a)
	{
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JButton newgamebutton = new JButton("New game");
		newgamebutton.addActionListener(a);
		newgamebutton.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(newgamebutton);
		
		JButton quitgamebutton = new JButton("Quit game");
		quitgamebutton.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(quitgamebutton);
	}
}
