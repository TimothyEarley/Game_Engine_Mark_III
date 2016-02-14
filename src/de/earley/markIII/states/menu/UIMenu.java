package de.earley.markIII.states.menu;

import de.earley.markIII.states.Layer;
import de.earley.markIII.utils.Vector2i;

/**
 * Created 13/02/16
 *
 * @author Timothy Earley
 */
public class UIMenu extends Layer {

	/**
	 * Construct a new menu
	 *
	 * @param position from top left of parent
	 * @param size     of the menu
	 */
	public UIMenu(Vector2i position, Vector2i size) {
		super(position, size);
	}

	public void addButton(UIButton btn) {
		addChild(btn);
	}
}
