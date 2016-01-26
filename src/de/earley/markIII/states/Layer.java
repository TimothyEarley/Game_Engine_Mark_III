package de.earley.markIII.states;

import de.earley.markIII.graphics.GraphicsHelper;
import de.earley.markIII.graphics.Viewport;
import de.earley.markIII.utils.BasicRenderable;
import de.earley.markIII.utils.Renderable;
import de.earley.markIII.utils.Updatable;
import de.earley.markIII.utils.Vector2i;

import java.awt.*;
import java.util.ArrayList;

/**
 *
 * Represents a Screen layer as well as a general state
 *
 * Created by timmy on 22/01/16.
 */
public class Layer implements Updatable, BasicRenderable, Renderable {

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
	 * @param position from top left of parent
	 * @param size of the layer
	 */
	public Layer(Vector2i position, Vector2i size) {
		this.viewport = new Viewport(position, size, this::render);
	}

	/**
	 * Update all sub-layers
	 * or override to only update this
	 */
	public void update() {
		children.forEach(Layer::update);
	}

	/**
	 * Render this
	 * @param gh
	 */
	public void render(GraphicsHelper gh) {}


	/**
	 * Render the contents of this layer and all sub-layers behind that
	 *
	 * @param g
	 * @param stretch
	 * @param offset
	 */
	public final void render(Graphics2D g, float stretch, Vector2i offset) {
		// First rendered is at the back
		for (Layer child : children) {
			child.render(g, stretch, offset);
		}
		// render this
		viewport.render(g, stretch, offset);
	}

	/**
	 * Adds a new child
	 * @param child an underlying layer
	 */
	protected void addChild(Layer child) {
		children.add(child);
	}

}
