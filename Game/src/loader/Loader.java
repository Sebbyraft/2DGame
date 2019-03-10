package loader;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import toolkit.Vec2;

public class Loader {
	
	public static BufferedImage loadImage(String path) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
			System.err.println("Could not load image: " + path);
			e.printStackTrace();
		}
		
		return image;
	}
	
	public static BufferedImage getTile(BufferedImage image, Vec2 position, Vec2 size) {
		return image.getSubimage((int)position.getX(), (int)position.getY(), (int)size.getX(), (int)size.getY());
	}
}