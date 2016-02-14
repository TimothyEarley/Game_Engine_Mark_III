package de.earley.markIII.graphics;

import de.earley.markIII.utils.Vector2i;

import java.awt.*;
import java.util.function.Consumer;

/**
 * Created by timmy on 22/01/16.
 */
public class BlankViewport extends Viewport {


	public BlankViewport(Vector2i position, Vector2i size, Consumer<GraphicsHelper> renderAction) {
		super(position, size, renderAction);
	}

	@Override
	public void render(Graphics2D g, double stretchX, double stretchY) {
		// do nothing
	}
}
