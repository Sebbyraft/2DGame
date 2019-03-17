package gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;

import loader.StringLoader;
import toolkit.Vec2;


public class MenuItems implements MouseMotionListener, MouseListener{
	
	private List<GuiElement> menuItems;
	private Menu m;

	public MenuItems(Menu m) {
		menuItems = new ArrayList<GuiElement>();
		
		Vec2 pos = new Vec2(m.getPosition().getX(), m.getPosition().getY() + 100);
		menuItems.add(new MenuItem("menu_item_0", pos , "RESET"));
		pos = new Vec2(m.getPosition().getX(), m.getPosition().getY() + 200);
		menuItems.add(new MenuItem("command", pos , "COMMAND"));
		
		this.m = m;
	}
	
	public void update() {

	}
	
	public void render(Graphics2D g2d, ImageObserver observer) {
		if(m.getMenuStatus() == true) {
			for(GuiElement  menuItem: menuItems) {
				menuItem.render(g2d, observer);
			}
		}
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		if(m.getMenuStatus() == true) {
			Vec2 mouse = new Vec2(e.getX(), e.getY());
			for(GuiElement  menuItem: menuItems) {
				float posY = menuItem.getPosition().getY() -  menuItem.getSize().getY();
				if(mouseOver(mouse, new Vec2(menuItem.getPosition().getX(), posY), menuItem.getSize())){
					menuItem.changeColor(new Color(0, 0, 200));
				} else {
					menuItem.changeColor(new Color(255, 255, 255));
				}
			}
		}
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == 1) {
			if(m.getMenuStatus() == true) {
				Vec2 mouse = new Vec2(e.getX(), e.getY());
				
				for(GuiElement  menuItem: menuItems) {
					float posY = menuItem.getPosition().getY() -  menuItem.getSize().getY();
					
					if(mouseOver(mouse, new Vec2(menuItem.getPosition().getX(), posY), menuItem.getSize())){
						System.out.println(menuItem.getId());
						if(menuItem.getId().equalsIgnoreCase("menu_item_0")) {
							
						} else if(menuItem.getId().equalsIgnoreCase("command")) {
							ArrayList<String> text = StringLoader.loadStrings("text/"+menuItem.getId()+".txt");
							for(int i = 0; i < text.size(); i++) {
								MenuItem cmds = new MenuItem(menuItem.getId()+"_text", new Vec2(m.getPosition().getX(), 300+i*30), text.get(i));
								cmds.setFontSize(24);
								menuItems.add(cmds);
							}
							
							return;
						}
					}
				}
			}
		}
	}

	public boolean mouseOver(Vec2 mouse, Vec2 position, Vec2 size) {
		if(mouse.getX() > position.getX() && mouse.getX() < position.getX() + size.getX()) {
			if(mouse.getY() > position.getY() && mouse.getY() < position.getY() + size.getY()) {
				return true;
			} else return false;
		}else return false;
	}
	
	public void cleanUp() {
		for(GuiElement  menuItem: menuItems) {
			menuItems.remove(menuItem);
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
