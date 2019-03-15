package test_entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

import toolkit.Vec2;

public class TestProjectile extends Entity{
	
	private static final String ID = "test_projectile";
	private static final float VELOCITY = 3f;
	private Vec2 direction;

	public TestProjectile(Vec2 position, Vec2 direction) {
		super(ID, position, 0, new Vec2(32, 32), "test");
		this.direction = direction;
	}

	@Override
	public void update() {
		this.position.setX(this.position.getX() + VELOCITY * direction.getX());
		this.position.setY(this.position.getY() + VELOCITY * direction.getY());
	}

	@Override
	public void render(Graphics2D g2d, ImageObserver observer) {
		g2d.setColor(new Color(255, 30, 30));
		g2d.rotate(rotation, (int)(position.getX()+size.getX()/2), (int)(position.getY()+size.getY()/2));
		g2d.fillRect((int)this.position.getX(), (int)this.position.getY(), (int)size.getX(), (int)size.getY());
	}

}
