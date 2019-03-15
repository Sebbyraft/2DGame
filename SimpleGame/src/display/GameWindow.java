package display;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

import gameManager.GameMainLoop;
import toolkit.Vec2;

public class GameWindow extends Canvas{

	private static final long serialVersionUID = 1L;
	
	private boolean resizable = false;
	private boolean visible = true;
	private static JFrame frame;
	
	public static final Vec2 WINDOW_SIZE = new Vec2(1280, 720);
	
	private static final String TITLE = "Game";
	
	public GameWindow(GameMainLoop game) {
		frame = new JFrame(TITLE);
		Dimension dimension = new Dimension((int)WINDOW_SIZE.getX(),(int)WINDOW_SIZE.getY());
		frame.setPreferredSize(dimension);
		frame.setSize(dimension);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(resizable);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(visible);
		game.start();
	}
	
	public static Vec2 getFramePosition() {
		return new Vec2(frame.getLocation().x, frame.getLocation().y);
	}
	
}