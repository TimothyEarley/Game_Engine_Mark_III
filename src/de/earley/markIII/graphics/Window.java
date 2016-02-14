package de.earley.markIII.graphics;

import de.earley.markIII.game.Game;
import de.earley.markIII.input.Input;
import de.earley.markIII.utils.ComponentSubject;
import de.earley.markIII.utils.Logger;
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
	 * Which game are we supposed to be displaying
	 */
	private Game game;

	/**
	 * Original size
	 */
	private Vector2i size;

	/**
	 * Handles all the input
	 */
	private Input input = new Input();

	/**
	 * Stretch compared to original
	 */
	private double stretchX = 1, stretchY = 1;


	public Window(String title, Vector2i size, Game game) {
		this.game = game;
		this.size = size;

		setupFrame(title, size);
		addInput();
		// last but not least, add ourselves
		frame.add(this);
	}

	private void addInput() {
		Logger.log(Logger.TYPE.INPUT, "Input added");
		addMouseMotionListener(input.getMouse());
		addMouseListener(input.getMouse());
		addKeyListener(input.getKeyboard());

		ComponentSubject componentSubject = new ComponentSubject();
		addComponentListener(componentSubject);
		componentSubject.getResizeSubject().registerAction(this::resize);
	}

	private void resize() {
		this.stretchX = getWidth() / (float) size.x;
		this.stretchY = (getHeight() ) / (float) size.y;
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

	private int titlebar() {
		return frame.getInsets().top;
	}

	public void showFrame() {
		frame.setVisible(true);
		frame.setSize(size.x, size.y + titlebar());
	}

	@Override
	public void paint(Graphics g) {

		// clear
		g.clearRect(0, 0, getWidth(), getHeight());

		game.render((Graphics2D) g, stretchX, stretchY);
	}

	public Input pollInput() {
		input.poll();
		return input;
	}


	public Vector2i getOriginalSize() {
		return size;
	}
}
