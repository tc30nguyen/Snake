import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;


public class Frame
{
	private static Starter al;
	private static JFrame frame;
	private static StartScreen start;
	private static final int DOT_SIZE = 20;
	private static final int HEIGHT = 410;
	private static final int WIDTH = 405;

	public static void main(String[] args)
	{
		makeFrame();
		//makeStart();
		frame.add(new GameBoard(DOT_SIZE, HEIGHT - 10, WIDTH - 5));
	}

	private static void makeFrame()
	{
		al = new Starter();
		frame = new JFrame();
		frame.setLocationRelativeTo(null);
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	private static void makeStart()
	{
		start = new StartScreen(al);
		frame.add(start);
	}

	private static class Starter implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			System.out.println("START");
			frame.getContentPane().removeAll();
			frame.add(new GameBoard(DOT_SIZE, HEIGHT - 10, WIDTH - 5));
			frame.repaint();
		}
	}
		
}
