package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

import toolkit.Vec2;

public class SimpleText extends GuiElement{
	
	private static final Color COLOR_1 = new Color(255, 255, 255, 255);
	private static final int FONT_SIZE = 32;
	
	private String text;

	public SimpleText(String id, Vec2 position, String text) {
		super(id, position, null);
		this.text = text;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics2D g2d, ImageObserver observer) {
		g2d.setColor(COLOR_1);
		g2d.setFont(new Font("SimplyRounded", Font.BOLD, FONT_SIZE));
		g2d.drawString(text , position.getX(), position.getY()-2);
	}

	@Override
	public void changeColor(Color color) {
	}

}
