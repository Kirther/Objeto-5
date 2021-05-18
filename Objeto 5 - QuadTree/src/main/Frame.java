package main;

import javax.swing.JFrame;

public class Frame extends JFrame{
	
	GraphicsPanel graphics = new GraphicsPanel();
	
	public Frame(int width, int height)
	{
		this.setSize(width, height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(graphics);
		this.setVisible(true);
	}

}