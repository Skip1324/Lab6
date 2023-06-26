package Util;

import Manager.CollectionManager;
import com.google.gson.*;
import data.City;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
/**
 * Type adapter of CollectionManager for json deserialization.
 */
public class CollectionManagerDeserializer implements JsonDeserializer<CollectionManager> {
    @Override
    public CollectionManager deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        CollectionManager result = new CollectionManager();
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        List<Long> listID = new ArrayList<>();
        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            String st = entry.getKey();
            City city = jsonDeserializationContext.deserialize(entry.getValue(), City.class);
            if (Objects.equals(city, null)) {
                return null;
            } else if (listID.contains(city.getID())) {
                return null;
            }

            result.insertElement(Integer.parseInt(st), city);
            listID.add(city.getID());
        }
        return result;
    }
}




