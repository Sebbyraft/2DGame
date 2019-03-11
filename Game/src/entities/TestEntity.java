package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import loader.Loader;
import toolkit.Vec2;

public class TestEntity extends Entity{
	
	private BufferedImage image;
	private static final String ID = "test_entity";

	public TestEntity(Vec2 position, Vec2 size) {
		super(ID, position, size, "test");
		image = Loader.loadImage("res/test.png");
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g, ImageObserver observer) {
		g.drawImage(image, (int)position.getX(), (int)position.getY(), (int)size.getX(), (int)size.getY(), observer);
		
	}
}
