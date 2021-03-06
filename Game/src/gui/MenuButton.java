package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

import display.GameWindow;
import toolkit.Vec2;

public class MenuButton extends GuiElement{
	
	private static final String ID = "menu_button";
	private static final String IMAGE_NAME = "menu";
	private static final Vec2 SIZE = new Vec2(32*3, 32);
	
	private boolean menuOpened = false;
	private Color color;

	public MenuButton(Vec2 position) {
		super(ID, position, SIZE, IMAGE_NAME);
		color = GuiManager.GUI_COLOR_1;
	}

	@Override
	public void update() {
		if(menuOpened == false) {
			menuOpened = true;
		} else {
			menuOpened = false;
		}
	}

	@Override
	public void render(Graphics2D g, ImageObserver observer) {
		g.setColor(color);
		g.setStroke(new BasicStroke(5));
		g.drawRect((int)position.getX()-4,(int)position.getY()-4, (int)SIZE.getX()+8, (int)SIZE.getY()+8);
		g.drawImage(image, (int)position.getX(), (int)position.getY(), (int)size.getX(), (int)size.getY(), observer);
		if (menuOpened) {
			g.setColor(GuiManager.GUI_COLOR_1);
			g.fillRect(15, 30+(int) GuiManager.TOP_GUI_SIZE.getY(), GameWindow.WIDTH/2-100, GameWindow.HEIGHT-205);
		}
	}

	@Override
	public void changeColor(Color color) {
		this.color = color;
	}



}
