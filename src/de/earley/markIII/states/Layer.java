package de.earley.markIII.states;

import de.earley.markIII.graphics.GraphicsHelper;
import de.earley.markIII.graphics.Viewport;
import de.earley.markIII.input.Input;
import de.earley.markIII.utils.*;

import java.awt.*;
import java.util.ArrayList;

/**
 * Represents a Screen layer as well as a general state
 * <p>
 * Created by timmy on 22/01/16.
 */
public class Layer implements Updatable, BasicRenderable, Renderable {

	/**
	 * If true, render children behind, if false, render children in front
	 */
	private boolean front;


	/**
	 * Child layers
	 */
	private ArrayList<Layer> children = new ArrayList<>();

	/**
	 * Where to render to
	 */
	protected Viewport viewport;


	/**
	 * Construct a new layer
	 *
	 * @param position from top left of parent
	 * @param size     of the layer
	 */
	public Layer(Vector2i position, Vector2i size) {
		this.viewport = new Viewport(position, size, this::render);
	}

	/**
	 * Update all sub-layers
	 * or override to only update this
	 *
	 * @param input
	 */
	public final void updateAll(Input input) {
		//todo INPUT
		Input in = viewport.adjust(input);
		update(in);
		children.forEach((layer) -> layer.updateAll(in));
	}

	public void update(Input input) {
	}

	/**
	 * Render this
	 *
	 * @param gh
	 */
	public void render(GraphicsHelper gh) {
	}


	/**
	 * Render the contents of this layer and all sub-layers behind that
	 */
	public final void render(Graphics2D g, double stretchX, double stretchY) {

		Logger.log(stretchX + "; " + stretchY);

		g.translate(viewport.getPosition().x, viewport.getPosition().y);

		// render this
		if (!front) viewport.render(g, stretchX, stretchY);
		// First rendered is at the back
		for (Layer child : children) {
			child.render(g, stretchX, stretchY);
		}
		// render this
		if (front) viewport.render(g, stretchX, stretchY);

		g.translate(- viewport.getPosition().x, - viewport.getPosition().y);


	}

	/**
	 * Adds a new child
	 *
	 * @param child an underlying layer
	 */
	protected void addChild(Layer child) {
		children.add(child);
	}


	public void setFront(boolean front) {
		this.front = front;
	}

	public int getWidth() {
		return viewport.getRenderSize().x;
	}

	public int getHeight() {
		return viewport.getRenderSize().y;
	}
}
