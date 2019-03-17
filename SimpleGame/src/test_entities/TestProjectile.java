package test_entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import entity.Entity;
import loader.ImageLoader;
import toolkit.Vec2;

public class TestProjectile extends Entity{
	
	private static final String ID = "test_projectile";
	private static final float VELOCITY = 3f;
	private Vec2 direction;
	private BufferedImage projectileImage;

	public TestProjectile(Vec2 position, Vec2 direction) {
		super(ID, position, 0, new Vec2(8, 8), "test");
		this.direction = direction;
		projectileImage = ImageLoader.loadImage("res/bullet.png");
	}

	@Override
	public void update() {
		position.setX(position.getX()  + VELOCITY * direction.getX());
		position.setY(position.getY()  + VELOCITY * direction.getY());
	}

	@Override
	public void render(Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(projectileImage, (int)position.getX(), (int)position.getY(), (int)size.getX(), (int)size.getY(), observer);
	}

}
