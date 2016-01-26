package de.earley.markIII.graphics;

import de.earley.markIII.graphics.drawable.Drawable;
import de.earley.markIII.utils.Vector2i;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 *
 * Combines AWT and Pixel graphics. Also offsets and stretch.
 *
 * Created by timmy on 22/01/16.
 */
public class GraphicsHelper {

	/**
	 * Where to draw on
	 */
	private Graphics g;

	/**
	 * Private image for the pixel array
	 */
	private BufferedImage image;

	/**
	 * Size of the pixel array
	 */
	private int width, height;

	/**
	 * Colour of each pixel
	 */
	private int[] pixel;

	public GraphicsHelper(Graphics graphics, Vector2i size) {
		this.g = graphics;
		this.width = size.x;
		this.height = size.y;
		this.pixel = new int[width * height];
		image = GraphicsEnvironment
				.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice()
				.getDefaultConfiguration()
				.createCompatibleImage(width, height, BufferedImage.TYPE_INT_ARGB);
	}

	/**
	 * Use this if you want to render directly
	 * @return the current Graphics
	 */
	public Graphics getGraphics() {
		return g;
	}

	public void render(Drawable drawable, Vector2i position) {
		//TODO: clipping

		for (int x  = 0; x < drawable.getWidth(); x++) {
			int xa = x + position.x;
			if (xa < 0 || xa >= width) continue;
			for (int y = 0; y < drawable.getHeight(); y++) {
				int ya = y + position.y;
				if ( ya < 0 || ya >= height) continue;
				setPixel(xa, ya, drawable.getPixel(x, y));
			}
		}


	}

	/**
	 * Set the pixel at the given position to the given colour. Potentially applying alpha.
	 * @param x in the image
	 * @param y in the image
	 * @param colour to be set
	 */
	private void setPixel(int x, int y, int colour) {
		image.setRGB(x, y, colour);
	}

	/**
	 * Flush all remaining graphics
	 */
	public void show() {
		g.drawImage(image, 0, 0, null);
	}

}
