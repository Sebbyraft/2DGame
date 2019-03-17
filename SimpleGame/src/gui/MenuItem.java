package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

import toolkit.Vec2;

public class MenuItem extends GuiElement{
	
	private static final Color COLOR_1 = new Color(255, 255, 255, 255);
	//private static final Color COLOR_2 = new Color(50, 100, 100, 255);
	
	private String text;
	private Color color;
	private Vec2 size;
	private int fontSize = 32;

	public MenuItem(String id, Vec2 position, String text) {
		super(id, position, null);
		this.text = text;
		this.color = COLOR_1;
		this.size = new Vec2(text.length()*fontSize*2/3, fontSize);
	}

	@Override
	public void update() {
	}

	@Override
	public void render(Graphics2D g2d, ImageObserver observer) {
		g2d.setColor(this.color);
		g2d.setFont(new Font("SimplyRounded", Font.BOLD, fontSize));
		g2d.drawString(text , position.getX(), position.getY()-2);
	}

	@Override
	public void changeColor(Color color) {
		this.color = color;
	}
	
	public Vec2 getSize() {
		return this.size;
	}
	
	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

}
