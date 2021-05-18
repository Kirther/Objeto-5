package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Particles {
	
	public int posX;
	public int posY;
	public int size;
	
	public int velocityX;
	public int velocityY;
	
	public boolean collision = false;
	public int timecounter = 0;
	
	public boolean stopcollision = false;
	
	//boolean particle0 = false;
	
	public Particles(int startX, int startY, int diameter)
	{
		posX = startX;
		posY = startY;
		size = diameter;
		
		velocityX = (int) ((Math.random() * (2 - 0) + 1) + 0);
		if (velocityX == 2)
			velocityX = -1;
			
		velocityY = (int) ((Math.random() * (2 - 0) + 1) + 0);
		if (velocityY == 2)
			velocityY = -1;
	}
	
	public void paint(Graphics g)
	{   
        Graphics2D g2D = (Graphics2D) g;
        
        if (collision)
        	g2D.setColor(Color.red);
        else
        g2D.setColor(Color.black);
        
        /*if (particle0)
        	 g2D.setColor(Color.green);*/
        
        g2D.fillOval(posX, posY, size, size);
	}
	
	public void UpdatePosition()
	{
		if (posX >= Main.canvasWidth - size || posX <= 0)
		{
			collision = true;
			if (posX >= Main.canvasWidth - size)
				velocityX = -1;
			
			if (posX <= 0)
				velocityX = 1;
		}
		
		if (posY >= Main.canvasHeight - size || posY <= 0)
		{
			collision = true;
			if (posY >= Main.canvasHeight - size)
				velocityY = -1;
			
			if (posY <= 0)
				velocityY = 1;
		}
		
		posX += velocityX;
		posY += velocityY;
	}
	
	public void InvertVelocityX()
	{
		velocityX *= -1;
	}
	
	public void InvertVelocityY()
	{
		velocityY *= -1;
	}

}