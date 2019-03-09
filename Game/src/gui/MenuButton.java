package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

import display.GameWindow;
import structures.Vec2;

public class MenuButton extends GuiElement{
	
	private static final String ID = "menu_button";
	private static final String IMAGE_NAME = "menu";
	private static final Vec2 SIZE = new Vec2(32*3, 32);
	
	private boolean menuOpened = false;

	public MenuButton(Vec2 position) {
		super(ID, position, SIZE, IMAGE_NAME);
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
	public void render(Graphics g, ImageObserver observer) {
		g.drawImage(image, (int)position.getX(), (int)position.getY(), (int)size.getX(), (int)size.getY(), observer);
		if (menuOpened) {
			g.setColor(new Color((int)GuiManager.GUI_COLOR.getX(),(int)GuiManager.GUI_COLOR.getY(),(int)GuiManager.GUI_COLOR.getZ()));
			g.fillRect(0, 10+(int) GuiManager.TOP_GUI_SIZE.getY(), GameWindow.WIDTH/2, GameWindow.HEIGHT);
		}
	}

}