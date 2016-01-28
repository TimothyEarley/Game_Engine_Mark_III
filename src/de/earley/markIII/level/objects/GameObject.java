package de.earley.markIII.level.objects;

import de.earley.markIII.graphics.GraphicsHelper;
import de.earley.markIII.level.objects.components.Component;
import de.earley.markIII.level.objects.utils.ComponentTypeManager;
import de.earley.markIII.utils.Renderable;
import de.earley.markIII.utils.Updatable;

import java.util.Collection;
import java.util.HashMap;

/**
 *
 * Created by timmy on 22/01/16.
 */
public class GameObject implements Updatable, Renderable {


	/**
	 * components here will be updated
	 */
	private HashMap<Integer, Component> components;

	public GameObject() {
		components = new HashMap<>();
	}


	@Override
	public void update() {
		components.forEach((id, component) -> component.update());
	}

	@Override
	public void render(GraphicsHelper gh) {
		components.forEach((id, renderComponent) -> {
			renderComponent.render(gh);
		});
	}

	/**
	 * Add component to be updated
	 *
	 * @param components updatable component(s)
	 */
	public void addComponents(Component ... components) {
		for (Component component : components) {
			this.components.put(ComponentTypeManager.getType(component), component);
		}
	}


	public void removeComponent(Component component) {
		components.remove(ComponentTypeManager.getType(component));
	}

	@Override
	public GameObject clone() {
		GameObject go = new GameObject();
		go.components.putAll(this.components);
		return go;
	}

	public Collection<Component> getComponents() {
		return components.values();
	}
}
