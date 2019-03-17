package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.Random;

import display.GameWindow;
import loader.ImageLoader;
import toolkit.Vec2;

public class ViewFinder extends Entity{
	
	private static final String ID = "view_finder";
	
	private Random r = new Random();
	private int newPosition = 0;
	private BufferedImage img;
	private int level = 1000;
	
	public ViewFinder(Vec2 position) {
		super(ID, position, 0, new Vec2(64, 64), "viewFinder");
		img = ImageLoader.loadImage("res/pointer.png");
	}
	
	public void update(Player p) {
		if(newPosition > level) {
			switch (r.nextInt(4)) {
			case 0:
				position.setValue(new Vec2(p.getPosition().getX()+p.getSize().getX()/2-size.getX()/2, 0));
				break;
			case 1:
				position.setValue(new Vec2(p.getPosition().getX()+p.getSize().getX()/2-size.getX()/2, GameWindow.WINDOW_SIZE.getY()-60-size.getY()/2));
				break;
			case 2:
				position.setValue(new Vec2(0, p.getPosition().getY()+p.getSize().getY()/2-size.getY()/2));
				break;
			case 3:
				position.setValue(new Vec2(GameWindow.WINDOW_SIZE.getX()-40-size.getX()/2, p.getPosition().getY()+p.getSize().getY()/2-size.getY()/2));
				break;
			default:
				break;
			}
			newPosition = 0;
		} else {
			newPosition ++;
		}
	}

	@Override
	public void render(Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(img, (int)(position.getX()), (int)(position.getY()), (int)(size.getX()), (int)(size.getY()), observer);	
	}
	
	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public void update() {};

}
