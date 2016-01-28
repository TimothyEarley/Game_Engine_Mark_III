package de.earley.markIII.level.objects;

import de.earley.markIII.level.objects.components.PhysicsComponent;
import de.earley.markIII.utils.Vector2i;

/**
 * Created by timmy on 26/01/16.
 */
public class GameEntity extends GameObject {

	//TODO change to 2f
	private Vector2i position;

	private PhysicsComponent physicsComponent;

	public PhysicsComponent getPhysicsComponent() {
		return physicsComponent;
	}
}
