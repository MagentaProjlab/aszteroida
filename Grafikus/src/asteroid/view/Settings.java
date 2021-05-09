package asteroid.view;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Settings extends JPanel
{
	public Settings(ActionListener al, JTextField namefield)
	{
		this.setLayout(new FlowLayout());
		JLabel namelabel = new JLabel("Number of settlers:");
		JButton okbutton = new JButton("OK");
		okbutton.addActionListener(al);
		
		this.add(namelabel);
		this.add(namefield);
		this.add(okbutton);
	}
}
