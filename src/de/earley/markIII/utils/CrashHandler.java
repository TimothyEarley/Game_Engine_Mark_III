package de.earley.markIII.utils;

/**
 * Created by timmy on 26/01/16.
 */
public class CrashHandler {

	public static void handle(Exception e) {
		e.printStackTrace();
		System.exit(1);
	}

}
