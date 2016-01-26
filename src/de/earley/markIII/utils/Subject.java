package de.earley.markIII.utils;

import java.util.ArrayList;

/**
 *
 * Lets observers register commands to be executed once the notify function is called
 *
 * Created by timmy on 22/01/16.
 */
public class Subject {

	/**
	 * Holds all active actions
	*/
	private ArrayList<Action> actions = new ArrayList<>();

	/**
	 * Add a new command
	 * @param command the command to be executed if the subject triggers
	 */
	public void registerAction(Action command) {
		actions.add(command);
	}

	/**
	 * Remove a command
	 * @param command a command that was already added
	 */
	public void unregisterAction(Action command) {
		actions.remove(command);
	}

	/**
	 * Remove all Commands
	 */
	public void clearActions() {
		actions.clear();
	}

	/**
	 * Notify all Observers that IT happened
	 * TODO arguments?
	 */
	public void trigger() {
		actions.forEach(Action::execute);
	}

}
