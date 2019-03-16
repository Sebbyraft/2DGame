package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.Random;

import display.GameWindow;
import loader.Loader;
import toolkit.Vec2;

public class ViewFinder extends Entity{
	
	private static final int NEW_POSITION_TIME = 1000;
	private static final String ID = "view_finder";
	
	private Random r = new Random();
	private int newPosition = 0;
	private BufferedImage img;
	
	public ViewFinder(Vec2 position) {
		super(ID, position, 0, new Vec2(0, 0), "viewFinder");
		img = Loader.loadImage("res/pointer.png");
	}
	
	public void update(Player p) {
		if(newPosition > NEW_POSITION_TIME * (1 + r.nextInt(3))) {
			switch (r.nextInt(4)) {
			case 0:
				position.setValue(new Vec2(p.getPosition().getX()+p.getSize().getX()/2-16, 0));
				break;
			case 1:
				position.setValue(new Vec2(p.getPosition().getX()+p.getSize().getX()/2-16, GameWindow.WINDOW_SIZE.getY()-60));
				break;
			case 2:
				position.setValue(new Vec2(0, p.getPosition().getY()+p.getSize().getY()/2-16));
				break;
			case 3:
				position.setValue(new Vec2(GameWindow.WINDOW_SIZE.getX()-40, p.getPosition().getY()+p.getSize().getY()/2-16));
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
		g2d.drawImage(img, (int)(position.getX()), (int)(position.getY()), 32, 32, observer);
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
