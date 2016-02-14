package de.earley.markIII.utils;

import de.earley.markIII.input.Input;

/**
 *
 * Indicates that this is indeed updatable
 *
 * Created by timmy on 22/01/16.
 */
public interface Updatable {
	/**
	 * Update this
	 * @param input
	 */
	void update(Input input);
}
