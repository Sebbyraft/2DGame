package gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;

import display.GameWindow;
import toolkit.Vec2;

public class ToolBar extends GuiElement{
	
	private List<ToolSpace> toolSpaces;
	private String[] imagesName;
	
	public ToolBar(String id, Vec2 position, Vec2 size, String[] imagesName) {
		super(id, position, size, imagesName[0]);
		this.imagesName = imagesName;
		toolSpaces = new ArrayList<ToolSpace>();
		fillBar();
	}

	@Override
	public void update() {
		for(int i = 0; i < 8; i++) {
			if(toolSpaces.get(i).mouseOver(GuiManager.getMousePosition().getX(), GuiManager.getMousePosition().getY())) {
				setId(toolSpaces.get(i).getId());
			}
		}
		
	}

	@Override
	public void render(Graphics2D g, ImageObserver observer) {
		for(int i = 0; i < 8; i++) {
			toolSpaces.get(i).render(g, observer);
		}
	}

	@Override
	public void changeColor(Color color) {
		for(int i = 0; i < 8; i++) {
			if(toolSpaces.get(i).mouseOver(GuiManager.getMousePosition().getX(), GuiManager.getMousePosition().getY())) {
				toolSpaces.get(i).changeColor(color);
			} else {
				toolSpaces.get(i).changeColor(GuiManager.GUI_COLOR_1);
			}
		}
	}
	
	private void fillBar() {
		for(int i = 0; i < 8; i++) {
			toolSpaces.add(new ToolSpace("tool_space_"+i, new Vec2(GameWindow.WIDTH-90, 30+i*80),imagesName[i]));
		}
	}
	

}