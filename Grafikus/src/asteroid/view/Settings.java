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
	public Settings(View v)
	{
		this.setLayout(new FlowLayout());
		JTextField namefield = new JTextField(5);
		JLabel namelabel = new JLabel("Number of settlers:");
		JButton okbutton = new JButton("OK");
		
		
		ActionListener a = new ActionListener()
		{	
			public void actionPerformed(ActionEvent e)
			{
				v.controller.GameLoop(Integer.parseInt(namefield.getText()));
				CardLayout cardLayout = (CardLayout) v.cards.getLayout();
				cardLayout.next(v.cards);
			}
		};
		okbutton.addActionListener(a);
		
		this.add(namelabel);
		this.add(namefield);
		this.add(okbutton);
	}
}
