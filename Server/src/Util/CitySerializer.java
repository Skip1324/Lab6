package Util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import data.City;

import java.lang.reflect.Type;
/**
 * Type adapter of City for json serialization.
 */
public class CitySerializer implements JsonSerializer<City> {
    @Override
    public JsonElement serialize(City city, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject result = new JsonObject();
        result.addProperty("id", city.getID());
        result.addProperty("name", city.getName());
        result.add("coordinates", jsonSerializationContext.serialize(city.getCoordinates()));
        result.add("creationDate", jsonSerializationContext.serialize(city.getCreationDate()));
        result.addProperty("area", city.getArea());
        result.addProperty("population", city.getPopulation());
        result.addProperty("metersAboveSeaLevel", city.getMetersAboveSeaLevel());
        result.addProperty("agglomeration", city.getAgglomeration());
        result.add("climate", jsonSerializationContext.serialize(city.getClimate()));
        result.add("standardOfLiving", jsonSerializationContext.serialize(city.getStandardOfLiving()));
        result.add("governor", jsonSerializationContext.serialize(city.getGovernor()));
        return result;
    }
}
