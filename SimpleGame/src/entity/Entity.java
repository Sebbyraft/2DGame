package entity;

import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

import toolkit.Vec2;

public abstract class Entity {
	
	protected Vec2 position;
	protected float rotation;
	protected Vec2 size;
	protected Vec2 velocity;
	
	protected String id, imageName;
	
	public Entity(String id, Vec2 position, float rotation, Vec2 size, String imageName) {
		this.position = position;
		this.rotation = rotation;
		this.size = size;
		this.id = id;
		this.imageName = imageName;
	}
	
	public abstract void update();
	public abstract void render(Graphics2D g2d, ImageObserver observer);
	
	public boolean mouseOver(float mouseX, float mouseY) {
		if(mouseX > position.getX() && mouseX < position.getX() + size.getX()) {
			if(mouseY > position.getY() && mouseY < position.getY() + size.getY()) {
				return true;
			} else return false;
		}else return false;
	}

	public Vec2 getPosition() {
		return position;
	}
	
	public float getX() {
		return position.getX();
	}
	
	public float getY() {
		return position.getY();
	}

	public void setPosition(Vec2 position) {
		this.position = position;
	}
	
	public void setX(float x) {
		this.position.setX(x);
	}
	
	public void setY(float y) {
		this.position.setY(y);
	}

	public Vec2 getSize() {
		return size;
	}
	
	public float getWidth() {
		return size.getX();
	}
	
	public float getHeight() {
		return size.getY();
	}

	public void setSize(Vec2 size) {
		this.size = size;
	}

	public void setWidth(float width) {
		this.size.setX(width);
	}
	
	public void setHeight(float height) {
		this.size.setY(height);
	}
	
	public Vec2 getVelocity() {
		return velocity;
	}

	public void setVelocity(Vec2 velocity) {
		this.velocity = velocity;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getimageName() {
		return imageName;
	}

	public void setimageName(String imageName) {
		this.imageName = imageName;
	}

	public float getRotation() {
		return rotation;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}
	
}
