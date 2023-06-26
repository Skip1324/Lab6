package Util;

import Exeception.IncorrectDataException;
import com.google.gson.*;
import data.Coordinates;


import java.lang.reflect.Type;
/**
 * Type adapter of coordinates for json deserialization.
 */
public class CoordinatesDeserializer implements JsonDeserializer<Coordinates> {

    @Override
    public Coordinates deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        Coordinates coordinates = new Coordinates();
        try {
            coordinates.setX((int)jsonObject.get("x").getAsInt());
            coordinates.setY(jsonObject.get("y").getAsFloat());
        } catch (IncorrectDataException e) {
            return null;
        }
        return coordinates;
    }

}
