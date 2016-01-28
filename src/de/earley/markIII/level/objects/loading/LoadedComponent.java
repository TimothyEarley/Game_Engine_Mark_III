package de.earley.markIII.level.objects.loading;

import de.earley.markIII.level.objects.components.Component;
import de.earley.markIII.utils.CrashHandler;

import java.util.HashMap;

/**
 * Created by timmy on 28/01/16.
 */
class LoadedComponent {


	private String type;

	private HashMap<String, Object> values;

	public LoadedComponent(Component component) {
		this.type = component.getClass().getName();
		this.values = component.getValues();
	}

	public Component getComponent() {
		try {
			return ((Component) Class.forName(type).newInstance()).addValues(values);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			CrashHandler.handle(e);
		}
		return null;
	}

	@Override
	public String toString() {
		return type + ": " + values.toString();
	}
}
