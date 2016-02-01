package de.earley.markIII.level.objects.loading;

import com.google.gson.*;
import de.earley.markIII.level.objects.components.Component;

import java.lang.reflect.Type;

/**
 * Created by timmy on 01/02/16.
 */
public class ComponentAdapter implements JsonSerializer<Component>, JsonDeserializer<Component> {

	private static final String CLASSNAME = "type";
	private static final String INSTANCE = "data";

	@Override
	public JsonElement serialize(Component src, Type typeOfSrc, JsonSerializationContext context) {

		JsonObject retValue = new JsonObject();
		String className = src.getClass().getName();
		retValue.addProperty(CLASSNAME, className);
		JsonElement elem = context.serialize(src);
		retValue.add(INSTANCE, elem);
		return retValue;
	}

	@Override
	public Component deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

		JsonObject jsonObject = json.getAsJsonObject();
		JsonPrimitive prim = (JsonPrimitive) jsonObject.get(CLASSNAME);
		String className = prim.getAsString();

		Class<?> clazz = null;
		try {
			clazz = Class.forName(className);
		} catch (ClassNotFoundException e) {
			throw new JsonParseException(e.getMessage());
		}
		return context.deserialize(jsonObject.get(INSTANCE), clazz);

	}

}
