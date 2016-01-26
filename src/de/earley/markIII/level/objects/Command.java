package de.earley.markIII.level.objects;

import de.earley.markIII.utils.Action;

/**
 *
 * Holds instructions for doing actions
 *
 * Created by timmy on 22/01/16.
 */
public interface Command extends Action {

	/**
	 * Do the action
	 */
	void execute();

	/**
	 * Undo the action
	 */
	void undo();

}
