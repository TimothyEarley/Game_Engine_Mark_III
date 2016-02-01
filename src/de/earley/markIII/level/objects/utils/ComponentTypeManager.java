package de.earley.markIII.level.objects.utils;

import de.earley.markIII.level.objects.components.Component;

import java.util.HashMap;

/**
 * Created by timmy on 28/01/16.
 */
public abstract class ComponentTypeManager {

	private static HashMap<Class<? extends Component>, Integer> types = new HashMap<>();

	private static int id = 0;

	public static Integer getType(Component component) {
		return getType(component.getClass());
	}

	public static Integer getType(Class<? extends Component> type) {
		if (types.containsKey(type)) {
			return types.get(type);
		} else {
			types.put(type, id++);
			return id - 1;
		}
	}
}
