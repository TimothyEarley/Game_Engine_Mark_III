package de.earley.markIII.utils;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * Created by timmy on 26/01/16.
 */
public class ComponentSubject extends ComponentAdapter {

	private Subject resizeSubject = new Subject();

	@Override
	public void componentResized(ComponentEvent e) {
		resizeSubject.trigger();
	}

	public Subject getResizeSubject() {
		return resizeSubject;
	}
}
