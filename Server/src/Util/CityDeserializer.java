package Util;

import Exeception.IncorrectDataException;
import com.google.gson.*;
import data.*;

import java.lang.reflect.Type;
import java.util.Date;


/**
 * Type adapter of City for json deserialization.
 */
public class CityDeserializer implements JsonDeserializer<City> {
    @Override
    public City deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException{
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        City city = new City();
        try {
            city.setId((long) jsonObject.get("id").getAsLong());
            city.setName(jsonObject.get("name").getAsString());
            city.setCoordinates((Coordinates) jsonDeserializationContext.deserialize(jsonObject.get("coordinates"), Coordinates.class));
            city.setCreationDate((Date) jsonDeserializationContext.deserialize(jsonObject.get("creationDate"), Date.class));
            city.setArea(jsonObject.get("area").getAsLong());
            city.setPopulation(jsonObject.get("population").getAsInt());
            city.setMetersAboveSeaLevel((long) jsonObject.get("metersAboveSeaLevel").getAsInt());
            city.setAgglomeration(jsonObject.get("agglomeration").getAsDouble());
            city.setClimate((Climate) jsonDeserializationContext.deserialize(jsonObject.get("climate"), Climate.class));
            city.setStandardOfLiving((StandardOfLiving) jsonDeserializationContext.deserialize(jsonObject.get("standardOfLiving"), StandardOfLiving.class));
            city.setGovernor((Human) jsonDeserializationContext.deserialize(jsonObject.get("governor"), Human.class));
        } catch (IncorrectDataException e) {
            return null;
        }
        return city;
    }
}

