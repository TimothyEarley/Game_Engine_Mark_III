package de.earley.markIII.utils;

import de.earley.markIII.graphics.GraphicsHelper;

/**
 *
 * This can render.
 *
 * Created by timmy on 22/01/16.
 */
public interface Renderable {

	/**
	 * render this thing
	 * @param gh use this to render
	 */
	void render(GraphicsHelper gh);

}
