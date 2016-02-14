package de.earley.markIII.utils;

import java.awt.*;

/**
 * Created 13/02/16
 *
 * @author Timothy Earley
 */
public abstract class StringUtils {

	public static Vector2i getRenderSize(String text) {

		Graphics g = GraphicsEnvironment.getLocalGraphicsEnvironment().createGraphics(GraphicsUtils.createImage(1,1));
		FontMetrics fontMetrics = g.getFontMetrics();
		return new Vector2i(fontMetrics.stringWidth(text), fontMetrics.getHeight());
	}

}
