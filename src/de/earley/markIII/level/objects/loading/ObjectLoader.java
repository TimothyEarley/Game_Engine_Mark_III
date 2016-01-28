package de.earley.markIII.level.objects.loading;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import de.earley.markIII.level.objects.GameObject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by timmy on 28/01/16.
 */
public class ObjectLoader {

	private static Type type = new TypeToken<ArrayList<LoadedObject>>() {}.getType();
	private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

	public static HashMap<String, GameObject> loadAll(String ... filePaths) throws IOException {

		HashMap<String, GameObject> map = new HashMap<>();

		for (String filePath : filePaths) {
			FileReader fileReader = new FileReader(filePath);
			JsonReader reader = new JsonReader(fileReader);
			ArrayList<LoadedObject> objects = gson.fromJson(reader, type);
			fileReader.close();
			for (LoadedObject loadedObject : objects) {
				map.put(loadedObject.getName(), loadedObject.createGameObject(map));
			}
		}

		return map;
	}


	public static void saveGameObjects(String filePath, HashMap<String, GameObject> map) throws IOException {
		// convert to simple representation
		ArrayList<LoadedObject> loadedObjects = new ArrayList<>();
		for (Map.Entry<String, GameObject> stringGameObjectEntry : map.entrySet()) {
			loadedObjects.add(new LoadedObject(stringGameObjectEntry.getKey(), stringGameObjectEntry.getValue()));
		}

		FileWriter writer = new FileWriter(filePath);
		String json = gson.toJson(loadedObjects);
		writer.write(json);
		writer.flush();
		writer.close();
	}


}
