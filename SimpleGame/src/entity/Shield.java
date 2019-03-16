package entity;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import display.GameWindow;
import loader.Loader;
import toolkit.Vec2;

public class Shield extends Entity implements MouseMotionListener{
	
	private static final String ID = "shield";
	private static final Vec2 size = new Vec2(48, 48);
	private static final float RADIUS = 130;
	
	private BufferedImage shield;
	private float angle = 0f;
	private Vec2 offset;

	public Shield(Vec2 position) {
		super(ID, position, 0, size, "shield");
		shield = Loader.loadImage("res/shield.png");
		offset = new Vec2(0, 0);
	}

	@Override
	public void update() {
		float x = (float) (RADIUS * Math.cos(angle));
		float y = (float) (-RADIUS * Math.sin(angle));
		
		offset.setValue(new Vec2(x, y));
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		

		float x = (e.getX() / GameWindow.WINDOW_SIZE.getX() - 0.5f) * 2f;
		float y = (e.getY() / GameWindow.WINDOW_SIZE.getY() - 0.5f) * 2f;
		
//		System.out.println("angle: "+angle+" x: "+ x + " y: "+ y);
	
		angle = (float) Math.atan2(x, y);
	}

	@Override
	public void render(Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(shield, (int)(position.getX()+offset.getX()-18), (int)(position.getY()+offset.getY()-18), (int)size.getX(), (int)size.getY(), observer);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	

}