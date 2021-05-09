package asteroid.view;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * A beallitasok kezelo osztaly
 *
 */
public class Settings extends JPanel
{
	/**
	 * Az osztaly konstruktora
	 * @param al: az OK gomb logikaja
	 * @param namefield: a textbox, ahova be kell irni a telepesek szamat
	 */
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
