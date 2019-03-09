package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.util.List;

import display.GameWindow;
import structures.Vec2;
import structures.Vec4;


public class GuiManager implements MouseListener{
	
	public static final Vec2 TOP_GUI_POSITION = new Vec2(0, 0);
	public static final Vec2 TOP_GUI_SIZE = new Vec2(GameWindow.WIDTH, 40);
	public static final Vec4 GUI_COLOR = new Vec4(25, 25, 112, 100);
	
	private List<GuiElement> guiElements;
	
	
	public GuiManager(List<GuiElement> guiElements) {
		this.guiElements = guiElements;
	}
	
	public void render(Graphics g, ImageObserver observer) {
		g.setColor(new Color((int)GUI_COLOR.getX(),(int)GUI_COLOR.getY(),(int)GUI_COLOR.getZ(),(int)GUI_COLOR.getW()));
		g.fillRect((int)TOP_GUI_POSITION.getX(),(int)TOP_GUI_POSITION.getY(),(int)TOP_GUI_SIZE.getX(),(int)TOP_GUI_SIZE.getY());
		for(GuiElement guiElement:guiElements) {
			guiElement.render(g, observer);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		for(GuiElement guiElement:guiElements) {
			
				if(guiElement.mouseOver(e.getX(), e.getY())) {
					if(e.getButton() == MouseEvent.BUTTON1) {
					guiElement.update();
					System.out.println(guiElement.id);
				}
			}
			
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
	
	
	public void addGuiElement(GuiElement guiElement) {
		this.guiElements.add(guiElement);
	}
	
	public GuiElement searchGuiElementByID(String id) {
		GuiElement guiElement = null;
		for(GuiElement guiEle:guiElements) {
			if(guiEle.getId().equalsIgnoreCase(id)) {
				guiElement = guiEle;
			}
		}
		return guiElement;
	}
	
	public void removeGuiElement(GuiElement guiElement) {
		this.guiElements.remove(guiElement);
	}

	public void removeAllElements() {
		this.guiElements.clear();
	}


}
