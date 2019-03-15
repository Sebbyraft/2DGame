package gameManager;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.io.IOException;

import display.GameWindow;
import test_entities.TestEntity;
import toolkit.Vec2;

public class GameMainLoop extends Canvas implements Runnable{

	private static final long serialVersionUID = -2786430757538994538L;

	private boolean running = true;
	private Thread thread;
	public int deltaMultiplier = 1;
	public float systemTime = 0;
	
	
	public TestEntity entity;

	
	public static void main(String[] args) throws IOException{
		new GameMainLoop();
	}
	
	public GameMainLoop() {
		entity = new TestEntity(new Vec2(WIDTH/2, HEIGHT/2), new Vec2(100, 100));
		
		this.addMouseMotionListener(entity);
		this.addMouseListener(entity);
		
		
		new GameWindow(this);
	}

	private void render() {

		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D)g;
		
		//*************************************************************************************************
		g2d.setColor(new Color(22, 22, 22));
		g2d.fillRect(0, 0, (int)GameWindow.WINDOW_SIZE.getX(), (int)GameWindow.WINDOW_SIZE.getY());
		//*************************************************************************************************
		
		
		entity.render(g2d, this);
		entity.update();
		
		
		//*************************************************************************************************

		g.dispose();
		bs.show();
	}

	@SuppressWarnings("unused")
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000/amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		
		while(running) {
			long now = System.nanoTime();
			delta = delta + (now-lastTime)/ns;
			lastTime = now;
			
			if (running) {
				render();
				systemTime=(float)delta*deltaMultiplier;
			} else {
				cleanUp();
			}
			
			frames++;
			
			if(System.currentTimeMillis()-timer>1000) {
				timer += 1000;
				frames = 0;
			}
		}
		stop();
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch(Exception e) {
			System.err.println("Could not join thread!!");
			e.printStackTrace();
		}
	}
	
	public void cleanUp() {
	}
	
	public float getSystemTime() {
		return systemTime;
	}
	
	public void setDeltaMultiplier(int multiplier) {
		this.deltaMultiplier = multiplier;
	}

}