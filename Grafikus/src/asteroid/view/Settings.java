package asteroid.view;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Settings extends JPanel
{
	public Settings()
	{
		this.setLayout(new FlowLayout());
		JTextField namefield = new JTextField(5);
		JLabel namelabel = new JLabel("Number of settlers:");
		JButton okbutton = new JButton("OK");
		this.add(namelabel);
		this.add(namefield);
		this.add(okbutton);
	}
}
