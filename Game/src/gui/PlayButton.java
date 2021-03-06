package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

import toolkit.Vec2;

public class PlayButton extends GuiElement{
	
	private static final Vec2 SIZE = new Vec2(32, 32);

	private boolean selected;
	private Color color;
	
	public PlayButton(Vec2 position) {
		super("pause_button", position, SIZE, "pause");
		selected = false;
		color = GuiManager.GUI_COLOR_1;
	}
	
	public void render(Graphics2D g, ImageObserver observer) {
		g.setColor(color);
		g.setStroke(new BasicStroke(5));
		g.drawRect((int)position.getX()-4,(int)position.getY()-4, (int)SIZE.getX()+8, (int)SIZE.getY()+8);
		g.drawImage(image, (int)position.getX(), (int)position.getY(), (int)size.getX(), (int)size.getY(), observer);
	}

	@Override
	public void update() {	
		if(selected == false) {
			changeIDAndImage("play", "play_button");
			selected = true;
			return;
		} else {
			changeIDAndImage("pause","pause_button");
			selected = false;
			return;
		}
	}

	@Override
	public void changeColor(Color color) {
		this.color = color;
	}

}
