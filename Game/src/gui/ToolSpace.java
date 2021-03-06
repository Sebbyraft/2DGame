package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import loader.Loader;
import toolkit.Vec2;

public class ToolSpace extends GuiElement{
	
	private Color color;
	private static final Vec2 SIZE = new Vec2(64, 64);
	private BufferedImage image;
	
	public ToolSpace(String id, Vec2 position,String imageName) {
		super(id, position, SIZE, imageName);
		color = GuiManager.GUI_COLOR_1;
		image = Loader.loadImage("res/gui/"+imageName+".png");
	}

	@Override
	public void render(Graphics2D g, ImageObserver observer) {
		g.setColor(color);
		g.setStroke(new BasicStroke(5));
		g.drawRect((int)position.getX()-4,(int)position.getY()-4, (int)SIZE.getX()+8, (int)SIZE.getY()+8);
		g.drawImage(image, (int)position.getX(), (int)position.getY(), (int)SIZE.getX(), (int)SIZE.getY(), observer);
	}

	@Override
	public void changeColor(Color color) {
		this.color = color;
	}

	@Override
	public void update() {
	}

}
