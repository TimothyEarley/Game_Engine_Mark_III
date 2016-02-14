package de.earley.markIII.graphics.drawable;

/**
 * Created 13/02/16
 *
 * @author Timothy Earley
 */
public class Rectangle implements Drawable {

	private int width, height, colour;

	public Rectangle(int width, int height, int colour) {
		this.width = width;
		this.height = height;
		this.colour = colour;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getPixel(int x, int y) {
		return colour;
	}
}
