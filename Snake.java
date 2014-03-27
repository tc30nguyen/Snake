import java.util.ArrayList;

public class Snake
{
	private ArrayList<Part> parts;
	private Part head;
	private int DOT_SIZE, HEIGHT, WIDTH;

	private class Part
	{
		int xC, yC;

		public Part(int xC, int yC)
		{
			this.xC = xC;
			this.yC = yC;
		}

		public void updateCoord(int xC, int yC)
		{
			this.xC = xC;
			this.yC = yC;
		}

		private int getX()
		{
			return xC;
		}

		private int getY()
		{
			return yC;
		}
	}

	public Snake(int dotSize, int height, int width)
	{
		DOT_SIZE = dotSize;
		HEIGHT = height;
		WIDTH = width;
		parts = new ArrayList<Part>();
		head = new Part(200,200);
		parts.add(head);
	}

	//returns coords of segment number index
	public int[] getCoords(int index)
	{
		int[] coords = new int[2];

		coords[0] = parts.get(index).getX();
		coords[1] = parts.get(index).getY();

		return coords;
	}

	public int getLength()
	{
		return parts.size();
	}
	
	//if hit apple, add tail after moving
	public void move(char dir, boolean apple)
	{
		Part newP = null;

		if(apple)
		{
			Part end = parts.get(parts.size() - 1);
			newP = new Part(end.getX(), end.getY());
		}

		for(int i = parts.size() - 1; i > 0; i--)
		{
			Part prev = parts.get(i-1);
			parts.get(i).updateCoord(prev.getX(), prev.getY());
		}

		if(apple)
			parts.add(newP);

		switch(dir)
		{
			case 'U':
				head.updateCoord(head.getX(), head.getY() - DOT_SIZE);
				break;
			case 'D':
				head.updateCoord(head.getX(), head.getY() + DOT_SIZE);
				break;
			case 'L':
				head.updateCoord	(head.getX() - DOT_SIZE, 
							head.getY());
				break;
			case 'R':
				head.updateCoord(head.getX() + DOT_SIZE, head.getY());
				break;
		}
	}

	//checks body + wall collision and returns true if hit
	public boolean checkCollision()
	{
		boolean collision = false;
		int x = head.getX();
		int y = head.getY();

		//wall collision
		if(x < 0 || x > WIDTH - DOT_SIZE || y < 0 || y > HEIGHT - DOT_SIZE)
			collision = true;

		//body collision
		else
		{
			for(int i = parts.size() - 1; i > 3; i--)
			{
				Part p = parts.get(i);
				if(x == p.getX() && y == p.getY())
					collision = true;
			}
		}
		
		return collision;
	}

	public boolean checkApple(int x, int y)
	{
		if(head.getX() == x && head.getY() == y)
			return true;
		else
			return false;
	}

	public boolean checkAppleCollision(int x, int y)
	{
		boolean collision = false;

		for(int i = 0; i < parts.size(); i++)
			{
				Part p = parts.get(i);
				if(x == p.getX() && y == p.getY())
					collision = true;
			}
		
		if(x < 0 || x > WIDTH - DOT_SIZE || y < 0 || y > HEIGHT - DOT_SIZE)
			collision = true;

		return collision;
	}
}
