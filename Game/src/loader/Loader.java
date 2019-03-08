package loader;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

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
}