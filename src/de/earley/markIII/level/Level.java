package de.earley.markIII.level;

import de.earley.markIII.graphics.GraphicsHelper;
import de.earley.markIII.level.map.Map;
import de.earley.markIII.level.objects.GameObject;
import de.earley.markIII.utils.Renderable;
import de.earley.markIII.utils.Updatable;

import java.util.ArrayList;

/**
 *
 * Combines map and game objects
 *
 * Created by timmy on 22/01/16.
 */
public class Level implements Updatable, Renderable{

	/**
	 * Holds all objects in the level
	 * TODO add inactive array?
	 */
	private ArrayList<GameObject> objects = new ArrayList<>();

	/**
	 * The map being played on
	 */
	private Map map;


	@Override
	public void update() {

		map.update();

		for (GameObject object : objects) {
			object.update();
		}
		//TODO remove objects

	}

	@Override
	public void render(GraphicsHelper gh) {

		map.render(gh);

		// TODO layers?

		for (GameObject object : objects) {
			object.render(gh);
		}

	}

	public void addObject(GameObject go) {
		go.setLevel(this);
		objects.add(go);
	}

	public void remove(GameObject go) {
		go.setLevel(null);
		objects.remove(go);
	}
}
