package de.earley.markIII.utils;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created 13/02/16
 *
 * @author Timothy Earley
 */
public abstract class Logger {

	public enum TYPE {
		GENERAL, INPUT, FPS, FINE
	}

	public static boolean ignoreDuplicates = true;

	private static HashMap<TYPE, Boolean> activeTypes = new HashMap<>();

	private static LinkedList<String> lastMsg = new LinkedList<>();

	static {
		setDupliacteDepth(2);
	}


	public static void log(Object msg) {
		log(TYPE.GENERAL, msg);
	}

	public static void log(TYPE type, Object msg) {
		log(type, msg.toString());
	}

	public static void log(TYPE type, String msg) {
		if (!activeTypes.getOrDefault(type, false) || duplicate(msg)) return;
		lastMsg.poll();
		lastMsg.add(msg);
		System.out.println("[" + getCaller() + "]: " + msg);
	}

	private static boolean duplicate(String msg) {
		return ignoreDuplicates && lastMsg.contains(msg);
	}

	public static String getCaller() {
		StackTraceElement[] stElements = Thread.currentThread().getStackTrace();

		for (int i = 1; i < stElements.length; i++) {
			StackTraceElement ste = stElements[i];
			if (!ste.getClassName().equals(Logger.class.getName()) && ste.getClassName().indexOf("java.lang.Thread") != 0) {
				return ste.getFileName();
			}
		}
		return null;
	}

	public static void setActive(TYPE type, boolean active) {
		activeTypes.put(type, active);
	}

	public static void setDupliacteDepth(int depth) {
		if (depth >= 0) {
			lastMsg.clear();
			for (int i = 0; i < depth; i++) {
				lastMsg.add("");
			}
		}
	}

}
