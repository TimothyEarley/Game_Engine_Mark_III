package de.earley.markIII.level;

import de.earley.markIII.graphics.GraphicsHelper;
import de.earley.markIII.states.Layer;
import de.earley.markIII.utils.StringUtils;
import de.earley.markIII.utils.Vector2i;

import java.awt.*;

/**
 * Created 13/02/16
 *
 * @author Timothy Earley
 */
public class UILabel extends Layer {

	private String text;
	private Color colour;

	public UILabel(Vector2i position, String text, Color colour) {
		super(position, StringUtils.getRenderSize(text));
		this.text = text;
		this.colour = colour;
	}

	@Override
	public void render(GraphicsHelper gh) {
		gh.getGraphics().setColor(colour);
		gh.getGraphics().drawString(text, 0, 0);
	}
}
