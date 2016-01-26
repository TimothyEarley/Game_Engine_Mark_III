package de.earley.markIII.graphics;

import de.earley.markIII.game.Game;
import de.earley.markIII.input.Input;
import de.earley.markIII.utils.ComponentSubject;
import de.earley.markIII.utils.Vector2i;

import javax.swing.*;
import java.awt.*;

/**
 * Created by timmy on 22/01/16.
 */
public class Window extends JPanel {

	/**
	 * The actual window
	 */
	private JFrame frame;

	/**
	 * Key and Mouse
	 */
	private Input input;

	/**
	 * The desired size
	 */
	private Vector2i size;

	/**
	 * Stretch the display
	 */
	private float stretch = 1;

	/**
	 * Offset the display
	 */
	private Vector2i offset = new Vector2i(0,0);

	/**
	 * Which game are we supposed to be displaying
	 */
	private Game game;

	public Window(String title, Vector2i size, Game game) {
		this.game = game;
		this.size = size;

		setupFrame(title, size);
		addInput();
		// last but not least, add ourselves
		frame.add(this);
	}

	private void addInput() {
		Input input = new Input();
		frame.addMouseMotionListener(input.getMouse());
		frame.addMouseListener(input.getMouse());
		frame.addKeyListener(input.getKeyboard());

		ComponentSubject componentSubject = new ComponentSubject();
		componentSubject.getResizeSubject().registerAction(this::checkSize);
		frame.addComponentListener(componentSubject);
	}

	/**
	 * Recalculate the stretch and offset
	 */
	public void checkSize() {
		Vector2i newSize = new Vector2i(getWidth(), getHeight());
		float stretchX = newSize.x / (float) size.x;
		float stretchY = newSize.y / (float) size.y;
		stretch = Math.min(stretchX, stretchY);
		offset = new Vector2i((newSize.x - size.x * stretch) / 2, (newSize.y - size.y * stretch) / 2);
	}


	private void setupFrame(String title, Vector2i size) {
		frame = new JFrame(title);
		// Set size and resizable
		frame.setSize(size.x, size.y);
		frame.setResizable(true);
		// Center
		frame.setLocationRelativeTo(null);
		// Close program on close
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void showFrame() {
		frame.setVisible(true);
	}

	@Override
	public void paint(Graphics g) {
		game.render((Graphics2D) g, stretch, offset);
	}
}
