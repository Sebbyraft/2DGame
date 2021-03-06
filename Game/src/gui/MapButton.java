package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

import toolkit.Vec2;

public class MapButton extends GuiElement{
	private static final Vec2 SIZE = new Vec2(72, 72);

	private boolean selected;
	private Color color;
	
	public MapButton(Vec2 position) {
		super("map_button", position, SIZE, "map");
		selected = false;
		color = GuiManager.GUI_COLOR_1;
	}
	
	public void render(Graphics2D g, ImageObserver observer) {
		g.setColor(color);
		g.setStroke(new BasicStroke(5));
		g.drawRoundRect((int)position.getX()-3, (int)position.getY()-3, (int)SIZE.getX()+6, (int)SIZE.getY()+6, 30, 30);
		g.drawImage(image, (int)position.getX(), (int)position.getY(), (int)size.getX(), (int)size.getY(), observer);
	}

	@Override
	public void update() {	
		if(selected == false) {
			selected = true;
			return;
		} else {
			selected = false;
			return;
		}
	}
	
	public void changeColor(Color newColor) {
		color = newColor;
	}
	
	public boolean getSelected() {
		return selected;
	}
}
