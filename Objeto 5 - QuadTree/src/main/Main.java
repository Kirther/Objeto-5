package main;

public class Main {
	
	public static int canvasWidth = 700;
	public static int canvasHeight = 700;
	
	public static Particles[] particles;
	public static int particlesCount =500;
	static int circleSize = 5;

	public static void main(String[] args) {

		particles = new Particles[particlesCount];
		
		for (int i = 0; i < particlesCount; i++)
		{
			int startX = (int) (Math.random() * (((canvasWidth - circleSize) - 10) + 1) + 10);
			int startY = (int) (Math.random() * (((canvasHeight - circleSize) - 10) + 1) + 10);
			particles[i] = new Particles(startX, startY, circleSize);
		}
		
		//particles[0].particle0 = true;
			
		Frame frame = new Frame(canvasWidth, canvasHeight);
	}
}
