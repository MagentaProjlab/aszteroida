package asteroid.view;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Settings extends JPanel
{
	public Settings(ActionListener a)
	{
		this.setLayout(new FlowLayout());
		JTextField namefield = new JTextField(5);
		JLabel namelabel = new JLabel("Number of settlers:");
		JButton okbutton = new JButton("OK");
		okbutton.addActionListener(a);
		
		this.add(namelabel);
		this.add(namefield);
		this.add(okbutton);
	}
}
