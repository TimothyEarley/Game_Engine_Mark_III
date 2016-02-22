package de.earley.markIII.level.objects.loading;

import de.earley.markIII.level.objects.GameObject;
import de.earley.markIII.level.objects.components.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * Created by timmy on 28/01/16.
 *
 * @author Timothy Earley
 */
class LoadedObject {

	private String name;

	private String prototype;

	private ArrayList<Component> components;

	public LoadedObject(String name, GameObject go) {
		this.name = name;
		components = new ArrayList<>();
		Collection<Component> componentCollection = go.getComponents();
		components.addAll(componentCollection);
	}

	public String getName() {
		return name;
	}

	/**
	 * @param map existing objects
	 * @return a fresh gameobject
	 */
	public GameObject createGameObject(Map<String, GameObject> map) {
		GameObject prototypeObject = map.get(prototype);

		GameObject go;
		if (prototypeObject != null) go = prototypeObject.clone();
		else go = new GameObject();

		if (components != null) {
			components.forEach(go::addComponents);
		}

		return go;
	}

	@Override
	public String toString() {
		return name + " (" + prototype + "): " + components.toString();
	}
}
