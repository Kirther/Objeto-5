package main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class QuadTree {

	QuadRectangle quad;
	int quadCapacity;
	ArrayList<Particles> allocatedParticles;
	//ArrayList<Particles> particles;
	boolean isDivided;
	
	QuadTree upperRight;
	QuadTree upperLeft;
	QuadTree lowerRight;
	QuadTree lowerLeft;
	
	public QuadTree(QuadRectangle boundary, int cap)
	{
		quad = boundary;
		quadCapacity = cap;
		allocatedParticles = new ArrayList<Particles>();
		isDivided = false;
	}
	
	void Subdivide (ArrayList<Particles> particles)
	{
		int x = quad.x;
		int y = quad.y;
		int w = quad.width;
		int h = quad.height;
		
		//System.out.println(particles.size());
		
		ArrayList<Particles> uRParticles = new ArrayList<Particles>();
		ArrayList<Particles> uLParticles = new ArrayList<Particles>();
		ArrayList<Particles> lRParticles = new ArrayList<Particles>();
		ArrayList<Particles> lLParticles = new ArrayList<Particles>();
		
		QuadRectangle uR = new QuadRectangle (x + w/2, y, w / 2, h / 2);
		upperRight = new QuadTree(uR, quadCapacity);
		
		QuadRectangle uL = new QuadRectangle (x, y, w / 2, h / 2);
		upperLeft = new QuadTree(uL, quadCapacity);
		
		QuadRectangle lR = new QuadRectangle (x + w/2, y + h/2, w / 2, h / 2);
		lowerRight = new QuadTree(lR, quadCapacity);
		
		QuadRectangle lL = new QuadRectangle (x, y + h/2, w / 2, h / 2);
		lowerLeft = new QuadTree(lL, quadCapacity);
		
		for (int i = 0; i < particles.size(); i++)
		{
			if (upperRight.quad.ContainsParticle(particles.get(i)))
			{
				uRParticles.add(particles.get(i));
			}
			if (upperLeft.quad.ContainsParticle(particles.get(i)))
			{
				uLParticles.add(particles.get(i));
			} 
			if (lowerRight.quad.ContainsParticle(particles.get(i)))
			{
				lRParticles.add(particles.get(i));
			}
			if (lowerLeft.quad.ContainsParticle(particles.get(i)))
			{
				lLParticles.add(particles.get(i));
			} 	
		}	
			upperRight.Insert(uRParticles);
			
			upperLeft.Insert(uLParticles);
			
			lowerRight.Insert(lRParticles);

			lowerLeft.Insert(lLParticles);

		
		isDivided = true;
	}
	
	public void Insert (ArrayList<Particles> particles)
	{
		
		 /*if (!quad.ContainsParticle(particle))
		 {
			 return false;
		 }*/
		 
			 if (particles.size() <= quadCapacity)
			 {
					 allocatedParticles = particles;		 
			 }
			 else {
				 if (isDivided == false)
					 Subdivide (particles);
				 
				 /*if (upperRight.Insert(particle) == true)
					 return true;
				 
				 else if (upperLeft.Insert(particle) == true)
					 return true;
				 
				 else if (lowerRight.Insert(particle) == true)
					 return true;
				 
				 else if (lowerLeft.Insert(particle) == true)
					 return true;*/
				 
			 }
	}
	
	public void paint(Graphics g)
	{
		g.setColor(Color.black);
		g.drawRect(quad.x, quad.y, quad.width, quad.height);
		
		if (isDivided)
		{
			upperRight.paint(g);
			upperLeft.paint(g);
			lowerRight.paint(g);
			lowerLeft.paint(g);
		}
	}
	
	public void CheckQuadTreeCollision()
	{
		if (allocatedParticles.size() > 0)
		{
			for (int i = 0; i < allocatedParticles.size(); i++)
			{
				for (int j = i + 1; j < allocatedParticles.size(); j++)
				{	
							if ((allocatedParticles.get(j).posX - allocatedParticles.get(i).posX) * (allocatedParticles.get(j).posX - allocatedParticles.get(i).posX) +
							(allocatedParticles.get(j).posY - allocatedParticles.get(i).posY) * (allocatedParticles.get(j).posY - allocatedParticles.get(i).posY) <=
							Main.circleSize * Main.circleSize)
							{
								allocatedParticles.get(i).collision = true;
								allocatedParticles.get(j).collision = true;
							    
							    allocatedParticles.get(i).stopcollision = true;
								
								if (allocatedParticles.get(i).velocityX != allocatedParticles.get(j).velocityX)
								{
									allocatedParticles.get(i).InvertVelocityX();
									allocatedParticles.get(j).InvertVelocityX();
								}
								
								if (allocatedParticles.get(i).velocityY != allocatedParticles.get(j).velocityY)
								{
									allocatedParticles.get(i).InvertVelocityY();
									allocatedParticles.get(j).InvertVelocityY();
								}
							}
				}

			}
		}
		else
		{
			if (isDivided)
			{
				upperRight.CheckQuadTreeCollision();
				upperLeft.CheckQuadTreeCollision();
				lowerRight.CheckQuadTreeCollision();
				lowerLeft.CheckQuadTreeCollision();
			}
		}
	}
}

