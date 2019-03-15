package test_entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import display.GameWindow;
import toolkit.Maths;
import toolkit.Vec2;

public class TestEntity extends Entity implements MouseMotionListener, MouseListener{
	
	private static final String ID = "test_entity";

	private boolean floating = false;
	private static float rotation = 0;
	
	private ArrayList<TestProjectile> projectiles;
	
	public TestEntity(Vec2 position, Vec2 size) {
		super(ID, position, rotation, size, "test");
		projectiles = new ArrayList<TestProjectile>();
	}

	@Override
	public void update() {
		if(this.floating == true) {
			rotation += 0.01;
		} else {
			rotation = 0;
		}
	}

	@Override
	public void render(Graphics2D g2d, ImageObserver observer) {
		g2d.setColor(new Color(255, 255, 255));
		g2d.rotate(rotation, (int)(position.getX()+size.getX()/2), (int)(position.getY()+size.getY()/2));
		g2d.fillRect((int)position.getX(), (int)position.getY(), (int)size.getX(), (int)size.getY());
		renderProjectiles(g2d, observer);
		updateProjectiles();
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
	// Pick or place object
	public void mouseClicked(MouseEvent e) {
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
		} else {
			
			float dx = (float) Math.cos(e.getX());
			float dy = (float) Math.sin(e.getY());
			Vec2 dir = new Vec2(dx, dy);
			
			System.out.println("Dx: "+dx+" Dy: "+dy);
			
			projectiles.add(new TestProjectile(new Vec2(position.getX(), position.getY()), dir));
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
			
			if(Maths.dist(p.position, new Vec2(GameWindow.WINDOW_SIZE.getX()/2, GameWindow.WINDOW_SIZE.getY()/2))> 300) {
				projectiles.remove(p);
				return;
			} 
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

}
