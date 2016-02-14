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


	public Vector2i clone() {
		return new Vector2i(x, y);
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

	public Vector2i(double x, double y) {
		this.x = (int) x;
		this.y = (int) y;
	}

	@Override
	public String toString() {
		return "[" + x + "/" + y + "]";
	}

	public void add(Vector2i offset) {
		this.x += offset.x;
		this.y += offset.y;
	}

	/**
	 *
	 * @param size
	 * @return true, if x and y are less than given
	 */
	public boolean lessThan(Vector2i size) {
		return x < size.x && y < size.y;
	}

	public boolean biggerThan(int x1, int y1) {
		return x > x1 && y > y1;
	}

	public Vector2i sub(Vector2i b) {
		x -= b.x;
		y -= b.y;
		return this;
	}

	public Vector2i negative() {
		return new Vector2i(-x,-y);
	}

	public Vector2i mult(float a) {
		x *= a;
		y *= a;
		return this;
	}
}
