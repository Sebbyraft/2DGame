package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import display.GameWindow;
import loader.Loader;
import toolkit.Vec2;

public class MenuButton extends GuiElement implements MouseListener, MouseMotionListener{
	
	private static final String ID = "menu_button";
	private static final Vec2 SIZE = new Vec2(32*3, 32);
	
	private static final Color COLOR_1 = new Color(255, 255, 255, 255);
	private static final Color COLOR_2 = new Color(50, 50, 205, 150);
	
	private boolean menuOpened = false;
	private BufferedImage image;
	private Color color;

	public MenuButton(Vec2 position) {
		super(ID, position, SIZE);
		color = COLOR_2;
		image = Loader.loadImage("res/gui/menu.png");
	}

	@Override
	public void update() {

	}

	@Override
	public void render(Graphics2D g2d, ImageObserver observer) {
		g2d.setColor(color);
		g2d.setStroke(new BasicStroke(5));
		g2d.drawRect((int)position.getX()-4,(int)position.getY()-4, (int)SIZE.getX()+8, (int)SIZE.getY()+8);
		g2d.drawImage(image, (int)position.getX(), (int)position.getY(), (int)size.getX(), (int)size.getY(), observer);
		if (menuOpened) {
			g2d.setColor(COLOR_2);
			g2d.fillRect((int)position.getX()-6, (int)position.getY() + 50, (int)GameWindow.WINDOW_SIZE.getX()/2-100, (int)GameWindow.WINDOW_SIZE.getY()-150);
		}
	}

	@Override
	public void changeColor(Color color) {
		this.color = color;
	}
	
	public boolean getMenuStatus() {
		return this.menuOpened;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == 1) {
			if(mouseOver(e.getX(), e.getY())) {
				if(menuOpened == false) {
					menuOpened = true;
				} else {
					menuOpened = false;
				}
			}	
		}
	}
	
	

	@Override
	public void mouseMoved(MouseEvent e) {
		if(mouseOver(e.getX(), e.getY())) {
			changeColor(COLOR_1);
		} else {
			changeColor(COLOR_2);
		}
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
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}




}
