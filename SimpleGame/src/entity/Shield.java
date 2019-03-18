package entity;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import display.GameWindow;
import loader.ImageLoader;
import toolkit.Vec2;

public class Shield extends Entity implements MouseMotionListener, MouseWheelListener{
	
	private static final String ID = "shield";
	private static final Vec2 size = new Vec2(48, 48);
	private static final float MIN_RADIUS = 130;
	private static final float MAX_RADIUS = 500;
	
	private BufferedImage shield;
	private float angle = 0f;
	private Vec2 offset;
	private Vec2 updatedPosition;
	private float radius = MIN_RADIUS;

	public Shield(Vec2 position) {
		super(ID, position, 0, size, "shield");
		shield = ImageLoader.loadImage("res/shield.png");
		offset = new Vec2(0, 0);
		updatedPosition = new Vec2(0, 0);
	}

	@Override
	public void update() {
		offset.setValue(getCoordinates(angle));
	}
	
	public void update(float mouseX, float mouseY) {
		float x = (mouseX / GameWindow.WINDOW_SIZE.getX() - 0.5f) * 2f;
		float y = (mouseY / GameWindow.WINDOW_SIZE.getY() - 0.5f) * 2f;
		angle = getAngle(x, y, -90);
		offset.setValue(getCoordinates(angle));
		updatedPosition.setValue(new Vec2(position.getX()+offset.getX()-18, position.getY()+offset.getY()-18));
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		float x = (e.getX() / GameWindow.WINDOW_SIZE.getX() - 0.5f) * 2f;
		float y = (e.getY() / GameWindow.WINDOW_SIZE.getY() - 0.5f) * 2f;
		angle = getAngle(x, y, -90);
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		float mouseRot = e.getWheelRotation();
		System.out.println(radius);
		
		if(radius >= MIN_RADIUS && radius <= MAX_RADIUS) {
			radius += mouseRot;
		}
		
	}
	
	@Override
	public void render(Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(shield, (int)(updatedPosition.getX()), (int)(updatedPosition.getY()), (int)size.getX(), (int)size.getY(), observer);
	}
	
	private float getAngle(float x, float y, float offset) {
		return (float) (Math.atan2(x, y) + offset);
	}
	
	private Vec2 getCoordinates(float angle) {
		float x = (float) (radius * Math.cos(angle));
		float y = (float) (-radius * Math.sin(angle));
		return new Vec2(x, y);
	}
	
	public Vec2 getUpdatedPosition() {
		return updatedPosition;
	}

	@Override
	public void mouseDragged(MouseEvent e) {}

}
