package de.earley.markIII.states.menu;

import de.earley.markIII.graphics.drawable.Drawable;
import de.earley.markIII.level.UILabel;
import de.earley.markIII.utils.Vector2i;

import java.awt.*;

/**
 * Created 13/02/16
 *
 * @author Timothy Earley
 */
public class UILabelButton extends UIButton {

	public UILabelButton(Vector2i position, String text, Color colour, Drawable image, Drawable imageHover) {
		super(position, image, imageHover);
		setFront(false);
		//TODO center
		addChild(new UILabel(position, text, colour));
	}
}
