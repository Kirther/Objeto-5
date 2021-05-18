package main;

public class QuadRectangle {
	public int x;
	public int y;
	public int width;
	public int height;
	
	public QuadRectangle(int x, int y, int w, int h)
	{
		this.x = x;
		this.y = y;
		width = w;
		height = h;
	}
	
	public boolean ContainsParticle(Particles particle)
	{
		boolean contains = (particle.posX + Main.circleSize > x && particle.posX < x + width && particle.posY + Main.circleSize > y && particle.posY < y + height);
		return contains;
	}
}