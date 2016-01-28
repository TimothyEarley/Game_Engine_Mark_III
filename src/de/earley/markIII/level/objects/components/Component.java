package de.earley.markIII.level.objects.components;

import de.earley.markIII.graphics.GraphicsHelper;
import de.earley.markIII.utils.Renderable;
import de.earley.markIII.utils.Updatable;

import java.util.HashMap;

/**
 * Created by timmy on 22/01/16.
 */
public abstract class Component implements Updatable, Renderable {

	@Override
	public void update() {
		//default
	}

	@Override
	public void render(GraphicsHelper gh) {
		// default
	}

	/**
	 * add all values that apply to this object
	 * @param values list of key - value pairs
	 * @return this
	 */
	public Component addValues(HashMap<String, Object> values) {
		return this;
	}

	/**
	 * gather all values this component holds
	 * @return a list of all values with keys
	 */
	public HashMap<String, Object> getValues() {
		return new HashMap<>();
	}
}
