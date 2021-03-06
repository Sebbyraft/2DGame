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
import entities.Planet;
import entities.PlanetsManager;
import entities.TestEntity;
import gui.GuiElement;
import gui.GuiManager;
import gui.MapButton;
import gui.MenuButton;
import gui.PlayButton;
import gui.ToolSpace;
import toolkit.Vec2;

public class GameMainLoop extends Canvas implements Runnable{

	private static final long serialVersionUID = -2786430757538994538L;

	private boolean running = true;
	private Thread thread;
	public int deltaMultiplier = 1;
	public float systemTime = 0;
	public String selection;
	
	public GuiManager guiManager;
	
	
	public List<Planet> planets;	
	public List<Planet> planets2;	
	public PlanetsManager planetsManager;	
	
	public MapButton mapButton;
	
	
	public static void main(String[] args) throws IOException{
		new GameMainLoop();
	}
	
	public GameMainLoop() {
		
		selection = "";
		
		guiManager = new GuiManager();
		this.addMouseListener(guiManager);
		this.addMouseMotionListener(guiManager);
		

		guiManager.addGuiElement(new PlayButton(new Vec2(140, 20)));
		guiManager.addGuiElement(new MenuButton(new Vec2(20, 20)));
		mapButton = new MapButton(new Vec2(20,  GameWindow.HEIGHT-120));
		guiManager.addGuiElement(new ToolSpace("tool_space_0", new Vec2(GameWindow.WIDTH-90, 30), "test"));
		guiManager.addGuiElement(new ToolSpace("tool_space_1", new Vec2(GameWindow.WIDTH-90, 30+80), "test"));
		guiManager.addGuiElement(new ToolSpace("tool_space_2", new Vec2(GameWindow.WIDTH-90, 30+80*2), "test"));
		guiManager.addGuiElement(new ToolSpace("tool_space_3", new Vec2(GameWindow.WIDTH-90, 30+80*3), "test"));
		guiManager.addGuiElement(new ToolSpace("tool_space_4", new Vec2(GameWindow.WIDTH-90, 30+80*4), "test"));
		guiManager.addGuiElement(new ToolSpace("tool_space_5", new Vec2(GameWindow.WIDTH-90, 30+80*5), "test"));
		guiManager.addGuiElement(new ToolSpace("tool_space_6", new Vec2(GameWindow.WIDTH-90, 30+80*6), "test"));
		guiManager.addGuiElement(new ToolSpace("tool_space_7", new Vec2(GameWindow.WIDTH-90, 30+80*7), "test"));
		guiManager.addGuiElement(mapButton);
		
		planets = new ArrayList<Planet>();
		planets2 = new ArrayList<Planet>();
		Planet startPlanet = new Planet("p1", new Vec2(GameWindow.WIDTH/2-512/2, GameWindow.HEIGHT/2-512/2), new Vec2(512, 512), "planet_5");
		startPlanet.setLocked(false);
		planets.add(startPlanet);
		planets.add(new Planet("p2", new Vec2(GameWindow.WIDTH/2-512/2, GameWindow.HEIGHT/2-512/2), new Vec2(512, 512), "planet_2"));
		planets.add(new Planet("p3", new Vec2(GameWindow.WIDTH/2-512/2, GameWindow.HEIGHT/2-512/2), new Vec2(512, 512), "planet_3"));
		planets.add(new Planet("p4", new Vec2(GameWindow.WIDTH/2-512/2, GameWindow.HEIGHT/2-512/2), new Vec2(512, 512), "planet_4"));
		
		
		
		planetsManager = new PlanetsManager(planets);
		
		this.addMouseListener(planetsManager);
		this.addMouseWheelListener(planetsManager);
		
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
		g.setColor(new Color(22, 22, 22));
		g.fillRect(0, 0, GameWindow.WIDTH, GameWindow.HEIGHT);
		//*************************************************************************************************
		
		if(mapButton.getSelected()) {
			planetsManager.render(g2d, this);
		}
		
		guiManager.render(g2d, this);
		
		for(Planet p:planets2) {
			p.render(g2d, this);
		}

		
			selection = guiManager.getClicked();
		if(selection.equalsIgnoreCase("tool_space_0")) {
			g.setColor(new Color(255, 255, 255));
			//g.fillRect((int)GuiManager.getMousePosition().getX(),(int) GuiManager.getMousePosition().getY(), 300,300);
			planets2.add(new Planet("p0", new Vec2(200, 200), new Vec2(100, 100),"planet_1"));
			selection = " ";
		}
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
		guiManager.removeAllElements();
		planetsManager.removeAllElements();
	}
	
	public float getSystemTime() {
		return systemTime;
	}
	
	public void setDeltaMultiplier(int multiplier) {
		this.deltaMultiplier = multiplier;
	}

}
