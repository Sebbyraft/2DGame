package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.Random;

import display.GameWindow;
import loader.ImageLoader;
import toolkit.Vec2;

public class Enemy extends Entity{
	
	private static final Vec2 SIZE = new Vec2(32, 32);
	private static final String ID = "enemy";
	private static final float VELOCITY = 1f;
	private static final float ALPHA = (GameWindow.WINDOW_SIZE.getY()/2)/(GameWindow.WINDOW_SIZE.getX()/2);


	private BufferedImage enemyImg;
	private Random r = new Random();
	private Vec2 direction;
	
	public Enemy(Vec2 position) {
		super(ID, position, 0, SIZE, "enemy");
		enemyImg = ImageLoader.loadImage("res/enemy.png");
		direction = new Vec2(0, 0);
		spawn();
	}

	private void spawn() {
		switch (r.nextInt(4)) {
		case 0:
			position.setValue(new Vec2(0, 0));
			direction.setValue(new Vec2(1, 1));
			break;
		case 1:
			position.setValue(new Vec2(GameWindow.WINDOW_SIZE.getX(), 0));
			direction.setValue(new Vec2(-1, 1));
			break;
		case 2:
			position.setValue(new Vec2(0, GameWindow.WINDOW_SIZE.getY()));
			direction.setValue(new Vec2(1, -1));
			break;
		case 3:
			position.setValue(new Vec2(GameWindow.WINDOW_SIZE.getX(), GameWindow.WINDOW_SIZE.getY()));
			direction.setValue(new Vec2(-1, -1));
			break;
		default:
			break;
		}
	}

	@Override
	public void update() {
		float x = position.getX() + direction.getX() * VELOCITY;
		float y = position.getY() + direction.getY() * VELOCITY * ALPHA;
		position.setValue(new Vec2(x, y));
	}

	@Override
	public void render(Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(enemyImg, (int)position.getX(), (int)position.getY(), (int)size.getX(), (int)size.getY(), observer);
		//g2d.drawRect((int)position.getX(), (int)position.getY(), (int)size.getX(), (int)size.getY());
	}

}
