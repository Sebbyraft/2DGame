package gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

import toolkit.Vec2;

public abstract class GuiElement {
	
	protected String id;
	protected Vec2 position;
	protected Vec2 size;
	
	public GuiElement(String id,Vec2 position, Vec2 size) {
		this.id = id;
		this.position = position;
		this.size = size;
	}
	
	public abstract void update();
	public abstract void render(Graphics2D g2d, ImageObserver observer);
	public abstract void changeColor(Color color);
	
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

	public void setPosition(Vec2 position) {
		this.position = position;
	}

	public Vec2 getSize() {
		return size;
	}

	public void setSize(Vec2 size) {
		this.size = size;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
