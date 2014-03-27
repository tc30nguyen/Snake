import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GameBoard extends JPanel implements ActionListener
{
	private Timer timer;
	private Snake snake;
	private int DOT_SIZE, appleX, appleY, HEIGHT, WIDTH;
	private KeyBind kb;
	private boolean dead;
	
	public GameBoard(int dotSize, int height, int width)
	{
		dead = false;
		HEIGHT = height;
		WIDTH = width;
		DOT_SIZE = dotSize;
		appleX = appleY = 0;

		setBackground(Color.BLACK);
		setDoubleBuffered(true);
		setFocusable(true);
		kb = new KeyBind(this);

		snake = new Snake(dotSize, height, width);
		newApple();

		timer = new Timer(120, this);
		timer.start();
	}

	public void paint(Graphics g)
	{
		super.paint(g);

		if(!dead)		
			drawGame(g);
		else
			gameOver(g);
	}

	public void actionPerformed(ActionEvent e)
	{
		boolean apple = false;

		//snake reaches apple then new apple and notify snake
		if(snake.checkApple(appleX, appleY))
		{
			newApple();
			apple = true;
		}
		snake.move(kb.getDir(), apple);

		if(snake.checkCollision())
			dead = true;

		repaint();
	}

	private void drawGame(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;

		//cobalt green head
		g2d.setColor(new Color(61, 145, 64));
		int[] headCoord = snake.getCoords(0);
		g2d.fillOval(headCoord[0], headCoord[1], DOT_SIZE, DOT_SIZE);
			
		//emerald green body
		g2d.setColor(new Color(0, 201, 87));
		for(int i = 1; i < snake.getLength(); i++)
		{
			int[] coords = snake.getCoords(i);
			g2d.fillOval(coords[0], coords[1], DOT_SIZE, DOT_SIZE);
		}

		//khaki apple
		g2d.setColor(new Color(240, 230, 140));
		g2d.fillOval(appleX, appleY, DOT_SIZE, DOT_SIZE);
			
		g.dispose();
	}

	private void gameOver(Graphics g)
	{
		String msg = "Game Over";
		Font font = new Font("Helvetica", Font.BOLD, 14);
		FontMetrics metr = this.getFontMetrics(font);

		g.setColor(Color.white);
		g.setFont(font);
		g.drawString(msg, (WIDTH - metr.stringWidth(msg)) / 2, HEIGHT / 2);
		timer.stop();
	}

	private void newApple()
	{
		appleX = (int) (Math.random() * WIDTH/DOT_SIZE);
		appleX = appleX * DOT_SIZE;
		appleY = (int) (Math.random() * HEIGHT/DOT_SIZE);
		appleY = appleY * DOT_SIZE;
		
		appleY = 360;

		//if apple spawn on snake, respawn
		while(snake.checkAppleCollision(appleX, appleY))
			newApple();
		System.out.println("apple is at (" + appleX + ", " + appleY + ")");
	}
}
