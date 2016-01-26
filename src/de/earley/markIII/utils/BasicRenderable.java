package de.earley.markIII.utils;

import java.awt.*;

/**
 *
 * This can be rendered provided the raw input
 *
 * Created by timmy on 22/01/16.
 */
public interface BasicRenderable {

	void render(Graphics2D g, float stretch, Vector2i offset);
}
