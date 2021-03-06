package gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import loader.Loader;
import toolkit.Vec2;

public abstract class GuiElement {
	
	protected String id;
	protected Vec2 position;
	protected Vec2 size;
	protected BufferedImage image;
	protected String imageName;
	
	public GuiElement(String id,Vec2 position, Vec2 size, String imageName) {
		this.id = id;
		this.position = position;
		this.size = size;
		this.imageName = imageName;
		this.image = Loader.loadImage("res/gui/"+imageName+".png");
	}
	
	public abstract void update();
	public abstract void render(Graphics2D g, ImageObserver observer);
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

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	public void changeIDAndImage(String imageName, String id) {
		this.id = id;
		this.image = Loader.loadImage("res/gui/"+imageName+".png");
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	
}
