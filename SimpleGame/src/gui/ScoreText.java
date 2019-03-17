package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

import toolkit.Vec2;

public class ScoreText extends GuiElement{
	
	private static final Color COLOR_1 = new Color(255, 255, 255, 255);
	private static final Color COLOR_2 = new Color(50, 50, 205, 150);
	private static final int FONT_SIZE = 32;
	private String scoreStr;

	public ScoreText(String id, Vec2 position) {
		super(id, position, null);
		scoreStr = "SCORE   " + 0;
	}

	@Override
	public void update() {
	}
	
	public void update(int score) {
		scoreStr = "SCORE   " + score;
	}

	@Override
	public void render(Graphics2D g2d, ImageObserver observer) {
		g2d.setColor(COLOR_2);
		g2d.setStroke(new BasicStroke(5));
		g2d.drawRect((int)position.getX()-8,(int)position.getY()-FONT_SIZE-4, (int)scoreStr.length()*FONT_SIZE*2/3, FONT_SIZE + 10);
		g2d.setColor(COLOR_1);
		g2d.setFont(new Font("SimplyRounded", Font.BOLD, FONT_SIZE));
		g2d.drawString(scoreStr , position.getX(), position.getY()-2);
	}

	@Override
	public void changeColor(Color color) {
		// TODO Auto-generated method stub
	}

	public int getUpdate(int score) {
		return 1 + score / 30;
	} 

}
