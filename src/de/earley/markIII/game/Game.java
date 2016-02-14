package de.earley.markIII.game;

import de.earley.markIII.graphics.Window;
import de.earley.markIII.states.Layer;
import de.earley.markIII.utils.CrashHandler;
import de.earley.markIII.utils.Logger;
import de.earley.markIII.utils.Subject;
import de.earley.markIII.utils.Vector2i;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;

/**
 * The main component of the game ties together the display and layers.
 * Also included is the main loop
 * <p>
 * Created by timmy on 22/01/16.
 */
public abstract class Game {

	private GameSettings settings;

	private Window window;

	private Layer state;

	private Subject shutdownHook;

	private boolean running;

	public Game(String title, int width, int height) {
		this(title, width, height, null);
	}

	public Game(String title, int width, int height, Layer state) {

		this.settings = new GameSettings();
		this.window = new Window(title, new Vector2i(width, height), this);
		this.state = state;

		shutdownHook = new Subject();
		Runtime.getRuntime().addShutdownHook(new Thread(shutdownHook::trigger));

	}

	public void start() {
		init();
		running = true;
		window.showFrame();

		try {
			run();
		} catch (InterruptedException e) {
			CrashHandler.handle(e);
		}
	}

	public void run() throws InterruptedException {
		long previous = System.nanoTime();
		long lag = 0;
		long current;
		long delta;

		boolean updated = false;

		//Debug
		int frames = 0, updates = 0;
		long timeSinceLastSecond = 0;

		while (running) {
			current = System.nanoTime();
			delta = current - previous;
			previous = current;
			lag += delta;
			timeSinceLastSecond += delta; // Debug
			updated = false;
			while (lag >= settings.getNSPerUpdate()) {
				updates++; // DEBUG
				state.updateAll(window.pollInput());
				lag -= settings.getNSPerUpdate();
				updated = true;
			}

			if (updated) {
				frames++; //Debug
				window.repaint();
			} else {
				//TODO sleep
//				Thread.sleep(settings.getNSPerUpdate() / 2000L);
			}

			//Debug
			if (timeSinceLastSecond >= 1E9) {
				timeSinceLastSecond -= 1E9;
				Logger.log(Logger.TYPE.FPS, "FPS: " + frames + ", UPS: " + updates);
				frames = updates = 0;
			}
		}
	}

	public void render(Graphics2D g, double stretchX, double stretchY) {

		AffineTransform at = new AffineTransform();

		double stretch = Math.min(stretchX, stretchY);

		if (settings.keepProportions) {
			Vector2i offset = new Vector2i((window.getOriginalSize().x * (stretchX - stretch)) / 2, (window.getOriginalSize().y * (stretchY - stretch) / 2));
			at.translate(offset.x, offset.y);
			stretchX = stretchY = stretch;
			at.scale(stretch, stretch);
		}


		g.transform(at);

		state.render(g, stretchX, stretchY);

		try {
			g.transform(at.createInverse());
		} catch (NoninvertibleTransformException e) {
			CrashHandler.handle(e);
		}

	}

	protected abstract void init();

	// Setters

	public void setState(Layer state) {
		this.state = state;
	}


	// Getters

	public Subject getShutdownHook() {
		return shutdownHook;
	}

	public GameSettings getSettings() {
		return settings;
	}
}
