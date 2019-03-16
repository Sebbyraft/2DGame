package gameManager;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import display.GameWindow;
import entity.Player;
import entity.Shield;
import loader.Loader;
import toolkit.Vec2;

public class GameMainLoop extends Canvas implements Runnable{

	private static final long serialVersionUID = -2786430757538994538L;

	private boolean running = true;
	private Thread thread;
	public int deltaMultiplier = 4;
	public float systemTime = 0;
	
	public BufferedImage background;
	public Player player;
	public Shield shield;
	
	public static void main(String[] args) throws IOException{
		new GameMainLoop();
	}
	
	public GameMainLoop() {
		player = new Player(new Vec2(GameWindow.WINDOW_SIZE.getX()/2-50, GameWindow.WINDOW_SIZE.getY()/2-50), new Vec2(112, 112));
		this.addMouseListener(player);
		this.addKeyListener(player);
		
		shield = new Shield(new Vec2(GameWindow.WINDOW_SIZE.getX()/2, GameWindow.WINDOW_SIZE.getY()/2));
		this.addMouseMotionListener(shield);
		
		background = Loader.loadImage("res/background.png");
	
		//****************************************************************************
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
		//g2d.setColor(new Color(22, 22, 22));
		//g2d.fillRect(0, 0, (int)GameWindow.WINDOW_SIZE.getX(), (int)GameWindow.WINDOW_SIZE.getY());
		g2d.drawImage(background, 0, 0, (int)GameWindow.WINDOW_SIZE.getX(), (int)GameWindow.WINDOW_SIZE.getY(), this);
		//*************************************************************************************************
		
		
		player.render(g2d, this);
		player.update();
		
		shield.render(g2d, this);
		shield.update();
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