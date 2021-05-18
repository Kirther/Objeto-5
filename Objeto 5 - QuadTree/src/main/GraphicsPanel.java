package main;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class GraphicsPanel extends JPanel implements ActionListener{
	
	Timer timer = new Timer(10, this);
	
	Particles[] particles;
	ArrayList<Particles> particlesList;
	int count;
	
	QuadTree quadTree;
	QuadRectangle quad;
	
	boolean quadTreeMethod = true;
	
	long startTime = 0;
	long totalTime = 0;
    int processCounter = 0;
	
	public GraphicsPanel()
	{
		timer.start();
		particles = Main.particles;
		count = Main.particlesCount;
	}
	
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (startTime != 0)
        {
        	long processTime = System.currentTimeMillis() - startTime;
        	totalTime += processTime;
        	processCounter++;
        	
        	if (processCounter == 100)
        	{
        		long averageTime = totalTime / 100;
            	System.out.println("O tempo médio de processamento das últimas 100 iterações foi de " + averageTime + " mili-segundos.");
        	}
        }
        
        startTime = System.currentTimeMillis();
        
        //g.drawRect(0, 0, Main.canvasWidth, Main.canvasHeight);
    	quad = new QuadRectangle(0, 0, Main.canvasWidth, Main.canvasHeight);  
    	particlesList = new ArrayList<Particles>();
        
        for (int i = 0; i < count; i++)
		{
        	particles[i].paint(g);
        	particlesList.add(particles[i]);
        	
        	if (particles[i].collision)
        	{
        		particles[i].timecounter++;
        		if (particles[i].timecounter == 30)
        		{
        			particles[i].collision = false;
        			particles[i].timecounter = 0;
        		}
        			
        	}
        }
        //System.out.println(particlesList.size());
    	if (quadTreeMethod)
    	{
	        quadTree = new QuadTree(quad, 10);
	    	quadTree.Insert(particlesList);
	        
	        quadTree.paint(g);
	        
	        quadTree.CheckQuadTreeCollision();
    	}
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (!quadTreeMethod)
		{
			CheckParticleCollision();
		}
		
		
		
		 for (int i = 0; i < count; i++)
			{
	        	particles[i].UpdatePosition();
	        	repaint();
			}
		
		 
	}

	private void CheckParticleCollision() {
		
		for (int i = 0; i < particles.length; i++)
		{
			for (int j = i + 1; j < particles.length; j++)
			{
				//a gente verifica a colisão
				if ((particles[j].posX - particles[i].posX) * (particles[j].posX - particles[i].posX) +
						(particles[j].posY - particles[i].posY) * (particles[j].posY - particles[i].posY) <=
						Main.circleSize * Main.circleSize)
				{
					particles[i].collision = true;
				    particles[j].collision = true;
					    
				    //particles[i].stopcollision = true;
							
					if (particles[i].velocityX != particles[j].velocityX)
					{
						particles[i].InvertVelocityX();
						particles[j].InvertVelocityX();
					}
							
					if (particles[i].velocityY != particles[j].velocityY)
					{
						particles[i].InvertVelocityY();
						particles[j].InvertVelocityY();
					}
				}
	
			}
			
			/*if (i == particles.length - 1)
			{
				for (int z = 0; z < particles.length; z++)
				{
					particles[z].stopcollision = false;
				}
			}*/
		}
		
	}

}