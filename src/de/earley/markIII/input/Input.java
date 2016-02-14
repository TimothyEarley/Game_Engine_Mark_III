package de.earley.markIII.input;

/**
 * Created by timmy on 22/01/16.
 */
public class Input {


	private Mouse mouse = new Mouse();
	private Keyboard keyboard = new Keyboard();

	public Mouse getMouse() {
		return mouse;
	}

	public Keyboard getKeyboard() {
		return keyboard;
	}

	public void poll() {
		keyboard.poll();
		mouse.poll();
	}

	@Override
	public Input clone() {
		Input input = new Input();
		input.mouse = mouse.clone();
		input.keyboard = keyboard;
		return input;
	}

}
