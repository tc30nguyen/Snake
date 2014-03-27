import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartScreen extends JPanel implements ActionListener
{
	ActionListener al;
	JButton start;
	JButton exit;
	boolean ready;

	public StartScreen(ActionListener al)
	{
		this.al = al;
		ready = false;
		start = new JButton("Start Game");
		exit = new JButton ("Exit");
		start.addActionListener(al);
		exit.addActionListener(this);

		setBackground(Color.BLACK);
		setFocusable(true);
		add(start);
		add(exit);
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource().equals(start))
			ready = true;

		else
			System.exit(0);
	}

	public JButton getStart()
	{
		return start;
	}
}
