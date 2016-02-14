package de.earley.markIII.utils;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created 13/02/16
 *
 * @author Timothy Earley
 */
public abstract class GraphicsUtils {

	public static BufferedImage createImage(int width, int height) {
		return GraphicsEnvironment
				.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice()
				.getDefaultConfiguration()
				.createCompatibleImage(width, height, BufferedImage.TYPE_INT_ARGB);
	}

}
