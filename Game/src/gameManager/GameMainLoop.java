package gameManager;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import display.GameWindow;
import gui.GuiElement;
import gui.GuiManager;
import gui.PlayButton;
import structures.Vec2;

public class GameMainLoop extends Canvas implements Runnable{

	private static final long serialVersionUID = -2786430757538994538L;

	private boolean running = true;
	private Thread thread;
	
	public List<GuiElement> guis;	
	public GuiManager guiManager;
	
	public static void main(String[] args) throws IOException{
		new GameMainLoop();
	}
	
	public GameMainLoop() {
		
		
		guis = new ArrayList<GuiElement>();
		guis.add(new PlayButton(new Vec2(120, 5), new Vec2(32, 32)));
		
		guiManager = new GuiManager(guis);
		this.addMouseListener(guiManager);;
		
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
		
	
		
		//*******************************************************
		g.setColor(new Color(204, 204, 255));
		g.fillRect(0, 0, GameWindow.WIDTH, GameWindow.HEIGHT);
		
		g.setColor(new Color(0, 150, 50));
		g.fillRect(0, 0, GameWindow.WIDTH, 40);
		//*******************************************************
		
		guiManager.render(g2d, this);
		//*******************************************************

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
			delta += (now-lastTime)/ns;
			lastTime = now;
			
			if (running) {
				render();
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

}