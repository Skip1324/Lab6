package Util;


import Manager.CollectionManager;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import data.City;


import java.lang.reflect.Type;
import java.util.Map;

/**
 * Type adapter of CollectionManager for json serialization.
 */

public class CollectionManagerSerializer implements JsonSerializer<CollectionManager> {
    @Override
    public JsonElement serialize(CollectionManager collectionManager, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject result = new JsonObject();
        for (Map.Entry<Integer, City> ct : collectionManager.getCollection().entrySet()) {
            Integer key = ct.getKey();
            City city = ct.getValue();
            result.add(key.toString(), jsonSerializationContext.serialize(city));
        }
        return result;
    }
}
