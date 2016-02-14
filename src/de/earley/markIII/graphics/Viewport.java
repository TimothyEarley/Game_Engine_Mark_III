package de.earley.markIII.graphics;

import de.earley.markIII.input.Input;
import de.earley.markIII.utils.Vector2i;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.function.Consumer;

/**
 * Represents a view in the window
 * <p>
 * Created by timmy on 22/01/16.
 */
public class Viewport {


	/**
	 * Where to render at
	 */
	private Vector2i position, size, ghSize;

	/**
	 * What to render
	 */
	private Consumer<GraphicsHelper> renderAction;

	/**
	 * Current transform
	 */
	private AffineTransform at;

	public Viewport(Vector2i position, Vector2i size, Consumer<GraphicsHelper> renderAction) {
		this.position = position;
		this.size = size;
		this.renderAction = renderAction;
	}

	/**
	 * Call the render action
	 */
	public void render(Graphics2D g, double stretchX, double stretchY) {
		ghSize = new Vector2i(size.x * stretchX, size.y * stretchY);
		GraphicsHelper gh = new GraphicsHelper(g, size);
		renderAction.accept(gh);
		gh.show();
	}

	public Vector2i getPosition() {
		return position;
	}

	public Input adjust(Input input) {
		//TODO fix
		Input adjustedInput = input.clone();
		adjustedInput.getMouse().addOffset(position.mult(-1));
		return adjustedInput;
	}

	public Vector2i getRenderSize() {
		return ghSize;
	}
}
