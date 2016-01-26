package de.earley.markIII.graphics;

import de.earley.markIII.utils.CrashHandler;
import de.earley.markIII.utils.Vector2i;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.util.function.Consumer;

/**
 *
 * Represents a view in the window
 *
 * Created by timmy on 22/01/16.
 */
public class Viewport {

	/**
	 * Where to render at
	 */
	private Vector2i position, size;

	/**
	 * What to render
	 */
	private Consumer<GraphicsHelper> renderAction;

	public Viewport(Vector2i position, Vector2i size, Consumer<GraphicsHelper> renderAction) {
		this.position = position;
		this.size = size;
		this.renderAction = renderAction;
	}

	/**
	 * Call the render action
	 * @param g
	 * @param stretch
	 * @param offset
	 */
	public void render(Graphics2D g, float stretch, Vector2i offset) {
		try {
			AffineTransform at = new AffineTransform();
			at.translate(offset.x + position.x, offset.y + position.y);
			at.scale(stretch, stretch);
			g.transform(at);
			GraphicsHelper gh = new GraphicsHelper(g, size);
			renderAction.accept(gh);
			gh.show();
			g.transform(at.createInverse());
		} catch (NoninvertibleTransformException e) {
			CrashHandler.handle(e);
		}
	}

	public Vector2i getPosition() {
		return position;
	}

	public Vector2i getSize() {
		return size;
	}
}
