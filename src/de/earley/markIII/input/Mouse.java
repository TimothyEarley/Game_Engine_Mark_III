package de.earley.markIII.input;

import de.earley.markIII.utils.Logger;
import de.earley.markIII.utils.Vector2i;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

/**
 * Created by timmy on 22/01/16.
 */
public class Mouse extends MouseAdapter {

	private Vector2i position = new Vector2i();

	private HashMap<Integer, Boolean> down = new HashMap<>();

	@Override
	protected Mouse clone() {
		Mouse newMouse = new Mouse();
		newMouse.position = position.clone();
		newMouse.down = down; // trusting the user
		return newMouse;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		position.x = e.getX();
		position.y = e.getY();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		down.put(e.getButton(), true);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		down.put(e.getButton(), false);
	}

	public void addOffset(Vector2i offset) {
		position.add(offset);
	}

	public Vector2i getPosition() {
		return position;
	}

	public boolean isButtonDown(int btn) {
		return down.getOrDefault(btn, false);
	}


	@Override
	public String toString() {
		return "Position: " + position + "; Down: " + down;
	}

	public void poll() {
		Logger.log(Logger.TYPE.INPUT, "Mouse = " + this);

	}
}
