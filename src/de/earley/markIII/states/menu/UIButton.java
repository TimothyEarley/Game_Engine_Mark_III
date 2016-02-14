package de.earley.markIII.states.menu;

import de.earley.markIII.graphics.GraphicsHelper;
import de.earley.markIII.graphics.drawable.Drawable;
import de.earley.markIII.input.Input;
import de.earley.markIII.states.Layer;
import de.earley.markIII.utils.Logger;
import de.earley.markIII.utils.Subject;
import de.earley.markIII.utils.Vector2i;

/**
 * Created 13/02/16
 *
 * @author Timothy Earley
 */
public class UIButton extends Layer {

	private Drawable drawable, drawableHover;

	private boolean hover;

	private Subject click = new Subject();

	public UIButton(Vector2i position, Drawable image, Drawable imageHover) {
		super(position, new Vector2i(image.getWidth(), image.getHeight()));
		this.drawable = image;
		this.drawableHover = imageHover;
	}

	@Override
	public void render(GraphicsHelper gh) {
		gh.render(hover ? drawableHover : drawable);
	}

	public Subject getClickSubject() {
		return click;
	}

	@Override
	public void update(Input input) {

		Logger.log(Logger.TYPE.INPUT, input.getMouse().getPosition());

//		hover = (input.getMouse().getPosition().biggerThan(0, 0) && input.getMouse().getPosition().lessThan(viewport.getSize()));

		if (hover && input.getMouse().isButtonDown(1)) {
			click.trigger();
		}
	}
}
