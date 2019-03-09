package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import loader.Loader;
import structures.Vec2;

public class Planet extends Entity {
	
	private BufferedImage image;
	private BufferedImage lock;
	
	private boolean isLocked = true;

	public Planet(String id, Vec2 position, Vec2 size, String imageName) {
		super(id, position, size, imageName);
		image = Loader.loadImage("res/planet/"+imageName+".png");
		lock = Loader.loadImage("res/planet/lock.png");
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g, ImageObserver observer) {
		if(isLocked) {
			g.drawImage(image, (int)position.getX(), (int)position.getY(), (int)size.getX(), (int)size.getY(), observer);
			g.drawImage(lock, (int)(position.getX()+size.getX()/2-64), (int)(size.getY()+64), 64, 64, observer);	
		} else {
			g.drawImage(image, (int)position.getX(), (int)position.getY(), (int)size.getX(), (int)size.getY(), observer);	
		}
		
	}

}