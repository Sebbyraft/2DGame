package entity;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import display.GameWindow;
import entity.Entity;
import loader.ImageLoader;
import toolkit.Maths;
import toolkit.Vec2;

public class Player extends Entity implements MouseListener, KeyListener{
	
	private static final String ID = "player";

	private static final int MAX_BULLETS_DISTANCE = 700;

	private static float rotation = 0;
	private Vec2 direction;
	
	private ArrayList<Bullet> bullets;
	private BufferedImage playerImg;
	private ViewFinder viewFinder;
	private int score = 0;
	private boolean fire = false;
	private int upgrade = 1;
	
	public Player(Vec2 position, Vec2 size) {
		super(ID, position, rotation, size, "test");
		
		bullets = new ArrayList<Bullet>();
		direction = new Vec2(1, 0);
		viewFinder = new ViewFinder(new Vec2(GameWindow.WINDOW_SIZE.getX()-64, position.getY()+size.getY()/2-32));
		playerImg = ImageLoader.loadImage("res/player.png");
	}

	@Override
	public void update() {
		fire();
		updatebullets();
		viewFinder.update(this);
		collider();
	}
	
	private void fire() {
		if(fire == true) {
			if(bullets.size() < upgrade) {
				float x = position.getX() + size.getX()/2 - 4;
				float y = position.getY() + size.getY()/2 - 4;
				bullets.add(new Bullet(new Vec2(x, y), direction));
				return;
			}
		}
	}

	private void collider() {
		for(int i = 0; i < bullets.size(); i++) {
			float d = Maths.dist(bullets.get(i).getPosition(), viewFinder.getPosition());
			if(d < (viewFinder.getSize().getX()/2+viewFinder.getSize().getY()/2)/2) {
				bullets.remove(i);
				score = score + 1;
			}
		}
	}

	@Override
	public void render(Graphics2D g2d, ImageObserver observer) {
		g2d.rotate(rotation, (int)(position.getX()+size.getX()/2), (int)(position.getY()+size.getY()/2));
		g2d.drawImage(playerImg, (int)position.getX(), (int)position.getY(), (int)size.getX(), (int)size.getY(), observer);
		
		renderbullets(g2d, observer);
		viewFinder.render(g2d, observer);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == 3) {
			if(fire == false) {
				fire = true;
			} else {
				fire = false;
			}
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == 1) { 
			fire = true;
		} 
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == 1) {
			if(fire == true)
				fire = false;
		}
	}
	
	private void renderbullets(Graphics2D g2d, ImageObserver observer) {
		for(Bullet p:bullets) {
			p.render(g2d, observer);
			p.update();
		}
	}

	private void updatebullets() {
		for(Bullet p:bullets) {
			if(Maths.dist(p.getPosition(), new Vec2(GameWindow.WINDOW_SIZE.getX()/2, GameWindow.WINDOW_SIZE.getY()/2)) >= MAX_BULLETS_DISTANCE) {
				bullets.remove(p);
				return;
			} 
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyChar() == 'w') {
			this.direction.setX(0);
			this.direction.setY(-1);
		} else if(e.getKeyChar() == 's') {
			this.direction.setX(0);
			this.direction.setY(1);
		} else if(e.getKeyChar() == 'd') {
			this.direction.setX(1);
			this.direction.setY(0);
		} else if(e.getKeyChar() == 'a') {
			this.direction.setX(-1);
			this.direction.setY(0);
		}
	}
	
	
	public int getScore() {
		return this.score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public void setUpgrade(int upgrade) {
		this.upgrade = upgrade;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

}
