package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

import entity.Player;
import toolkit.Vec2;

public class LifeIndicator extends GuiElement{
	
	private static final String ID = "life_indicator";
	private static final Vec2 SIZE = new Vec2(32, 32);
	private static final Color COLOR_1 = new Color(30, 180, 30);
	private static final Color COLOR_2 = new Color(50, 50, 205, 150);
	private static final int MAX_LIFE = 10;
	
	private int life = 10;
	private Player player;
	

	public LifeIndicator(Vec2 position, Player player) {
		super(ID, position, null);
		this.player = player;
	}

	@Override
	public void render(Graphics2D g2d, ImageObserver observer) {
		g2d.setColor(COLOR_2);
		g2d.setStroke(new BasicStroke(5));
		g2d.drawRect((int)position.getX()-4,(int)position.getY()-4, (int) SIZE.getX()*MAX_LIFE + 8, (int)SIZE.getY()+8);
		
		g2d.setColor(COLOR_1);
		for(int i = 0; i < life; i++) {
			g2d.fillRect((int)(position.getX()+i*(SIZE.getX())), (int)position.getY(), (int)SIZE.getX(), (int)SIZE.getY());
		}
	}
	
	@Override
	public void update() {
		this.life = player.getLife();
	}

	@Override
	public void changeColor(Color color) {};

}
