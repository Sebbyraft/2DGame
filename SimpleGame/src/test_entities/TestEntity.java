package test_entities;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Random;

import display.GameWindow;
import loader.Loader;
import toolkit.Maths;
import toolkit.Vec2;

public class TestEntity extends Entity implements MouseMotionListener, MouseListener, KeyListener{
	
	private static final String ID = "test_entity";
	
	private static final int MAX_N_PROJECTILES = 5;
	private static final int MAX_PROJECTILES_DISTANCE = 700;
	private static final int NEW_POSITION_TIME = 1000;

	private boolean floating = false;
	private static float rotation = 0;
	private Vec2 direction;
	private Vec2 viewfinder;
	
	private ArrayList<TestProjectile> projectiles;
	private BufferedImage playerImg, playerViewfinder;
	
	private Random r = new Random();
	private int newPosition = 0;
	
	public TestEntity(Vec2 position, Vec2 size) {
		super(ID, position, rotation, size, "test");
		
		projectiles = new ArrayList<TestProjectile>();
		direction = new Vec2(1, 0);
		viewfinder = new Vec2(0, 0);
		this.viewfinder.setValue(new Vec2(GameWindow.WINDOW_SIZE.getX()-40, position.getY()+size.getY()/2-16));
		playerImg = Loader.loadImage("res/player.png");
		playerViewfinder = Loader.loadImage("res/pointer.png");
	}

	@Override
	public void update() {
		if(this.floating == true) {
			rotation += 0.01;
		} else {
			rotation = 0;
			updateViewfinder();
			updateProjectiles();
		}
	}

	@Override
	public void render(Graphics2D g2d, ImageObserver observer) {
		g2d.rotate(rotation, (int)(position.getX()+size.getX()/2), (int)(position.getY()+size.getY()/2));
		g2d.drawImage(playerImg, (int)position.getX(), (int)position.getY(), (int)size.getX(), (int)size.getY(), observer);
		
		renderViewfinder(g2d, observer);
		renderProjectiles(g2d, observer);
	}

	private void renderViewfinder(Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(playerViewfinder, (int)(viewfinder.getX()), (int)(viewfinder.getY()), 32, 32, observer);
	}

	private void updateViewfinder() {
		if(newPosition > NEW_POSITION_TIME * (1 + r.nextInt(3))) {
			switch (r.nextInt(4)) {
			case 0:
				this.viewfinder.setValue(new Vec2(position.getX()+size.getX()/2-16, 0));
				break;
			case 1:
				this.viewfinder.setValue(new Vec2(position.getX()+size.getX()/2-16, GameWindow.WINDOW_SIZE.getY()-60));
				break;
			case 2:
				this.viewfinder.setValue(new Vec2(0, position.getY()+size.getY()/2-16));
				break;
			case 3:
				this.viewfinder.setValue(new Vec2(GameWindow.WINDOW_SIZE.getX()-40, position.getY()+size.getY()/2-16));
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
	public void mouseMoved(MouseEvent e) {
		if (floating == true) {
			float x = e.getX() - this.size.getX()/2;
			float y = e.getY() - this.size.getY()/2;
			
			this.position = new Vec2(x, y);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// Pick or place object
		if(e.getButton() == 1) {
			if(this.floating == true) {
				this.floating = false;
			} else if(this.floating == false) {
				if(mouseOver(e.getX(), e.getY())) {
					this.floating = true;
				} else {
					this.floating = false;
				}
			}
		} else { // Fire
			if(projectiles.size() < MAX_N_PROJECTILES) {
				if(this.floating == false) {
					float x = position.getX() + size.getX()/2 - 4;
					float y = position.getY() + size.getY()/2 - 4;
					projectiles.add(new TestProjectile(new Vec2(x, y), direction));
				}
			}
		}
	}
	
	private void renderProjectiles(Graphics2D g2d, ImageObserver observer) {
		for(TestProjectile p:projectiles) {
			p.render(g2d, observer);
			p.update();
		}
	}

	private void updateProjectiles() {
		for(TestProjectile p:projectiles) {
			if(Maths.dist(p.getPosition(), new Vec2(GameWindow.WINDOW_SIZE.getX()/2, GameWindow.WINDOW_SIZE.getY()/2)) >= MAX_PROJECTILES_DISTANCE) {
				projectiles.remove(p);
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

	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
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
