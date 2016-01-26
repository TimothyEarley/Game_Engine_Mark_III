package de.earley.markIII.utils;

/**
 *
 * Holds a single point
 *
 * Created by timmy on 22/01/16.
 */
public class Vector2i {

	/**
	 * The coordinates
	 */
	public int x, y;

	public Vector2i() {
		this(0, 0);
	}

	public Vector2i(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Cast
	 * @param x -> x
	 * @param y -> y
	 */
	public Vector2i(float x, float y) {
		this.x = (int) x;
		this.y = (int) y;
	}

	@Override
	public String toString() {
		return "[" + x + "/" + y + "]";
	}
}
