package de.earley.markIII.level.objects;

import de.earley.markIII.graphics.GraphicsHelper;
import de.earley.markIII.level.objects.components.Component;
import de.earley.markIII.level.objects.components.RenderComponent;
import de.earley.markIII.utils.Renderable;
import de.earley.markIII.utils.Updatable;

import java.util.HashMap;
import java.util.Optional;

/**
 * Created by timmy on 22/01/16.
 */
public class GameObject implements Updatable, Renderable {


	/**
	 * components here will be updated
	 */
	HashMap<String, Component> components;
	/**
	 * components added here will be rendered
	 */
	HashMap<String, RenderComponent> renderComponents;

	public GameObject() {
		components = new HashMap<>();
		renderComponents = new HashMap<>();
	}


	@Override
	public void update() {
		components.forEach((id, component) -> {component.update();});
	}

	@Override
	public void render(GraphicsHelper gh) {
		renderComponents.forEach((id, renderComponent) -> {renderComponent.render(gh);});
	}

	/**
	 * Add component to be updated
	 * @param id the id for this component
	 * @param component an updatable component
	 */
	public void addComponent(String id, Component component) {
		components.put(id, component);
	}

	/**
	 * Add component to be rendered
	 * @param renderComponent must be renderable
	 */
	public void addRenderComponent(String id, RenderComponent renderComponent) {
		renderComponents.put(id, renderComponent);
	}

	/**
	 * Tries to get the component
	 * @param id the id under which the component is stored
	 * @return maybe a component
	 */
	public Optional<Component> getComponent(String id) {
		return Optional.ofNullable(components.get(id));
	}

	/**
	 * Tries to get the renderable component
	 * @param id the id under which the component is stored
	 * @return maybe a renderable component
	 */
	public Optional<RenderComponent> getRenderComponent(String id) {
		return Optional.ofNullable(renderComponents.get(id));
	}

}
