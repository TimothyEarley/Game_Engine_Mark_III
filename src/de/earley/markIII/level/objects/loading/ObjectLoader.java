package de.earley.markIII.level.objects.loading;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import de.earley.markIII.level.objects.GameObject;
import de.earley.markIII.level.objects.components.Component;
import throwing.stream.ThrowingStream;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by timmy on 28/01/16.
 *
 * @author Timothy Earley
 */
public class ObjectLoader {

	private static Type type = new TypeToken<ArrayList<LoadedObject>>() {
	}.getType();
	private static Gson gson = new GsonBuilder()
			.setPrettyPrinting()
			.registerTypeAdapter(Component.class, new ComponentAdapter())
			.create();

	public static Map<String, GameObject> loadAllFiles(String... filePaths) throws IOException {

		return loadJSON(ThrowingStream.of(Stream.of(filePaths), IOException.class).map(filePath -> Paths.get(filePath)).map(Files::readAllBytes).map(String::new).reduce(String::concat).get());

	}

	public static Map<String, GameObject> loadJSON(String... jsonString) {

		HashMap<String, GameObject> map = new HashMap<>();
		for (String s : jsonString) {
			loadJSON(s, map);
		}
		return map;
	}

	public static Map<String, GameObject> loadJSON(String jsonString, Map<String, GameObject> environment) {
		if (environment == null) environment = new HashMap<>();
		ArrayList<LoadedObject> objects = gson.fromJson(jsonString, type);
		for (LoadedObject loadedObject : objects) {
			environment.put(loadedObject.getName(), loadedObject.createGameObject(environment));
		}
		return environment;
	}


	public static void saveGameObjects(String filePath, HashMap<String, GameObject> map) throws IOException {
		FileWriter writer = new FileWriter(filePath);
		String json = toJSON(map);
		writer.write(json);
		writer.flush();
		writer.close();
	}

	public static String toJSON(HashMap<String,GameObject> map) {
		// convert to simple representation
		List<LoadedObject> loadedObjects = map.entrySet().stream().map(stringGameObjectEntry -> new LoadedObject(stringGameObjectEntry.getKey(), stringGameObjectEntry.getValue())).collect(Collectors.toList());
		return gson.toJson(loadedObjects);
	}


}
