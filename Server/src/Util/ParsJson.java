package Util;

import Manager.CollectionManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import data.City;
import data.Coordinates;
import data.Human;

import java.io.*;
import java.util.Objects;
/**
 * Class which parse json.
 */
public class ParsJson {
    public boolean serialize(CollectionManager collection, File file) throws IOException {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(CollectionManager.class, new CollectionManagerSerializer())
                .registerTypeAdapter(City.class, new CitySerializer())
                .registerTypeAdapter(Coordinates.class, new CoordinatesDeserializer())
                .registerTypeAdapter(Human.class, new HumanDeserializer())
                .create();
        OutputStream  os = new FileOutputStream(file);
        Writer osr = new OutputStreamWriter(os);
        osr.write(gson.toJson(collection));
        osr.close();
        return true;
    }
    public CollectionManager deSerialize(String str) throws JsonSyntaxException {
        Gson g = new GsonBuilder()
                .registerTypeAdapter(CollectionManager.class, new CollectionManagerDeserializer())
                .registerTypeAdapter(City.class, new CityDeserializer())
                .registerTypeAdapter(Coordinates.class, new CoordinatesDeserializer())
                .registerTypeAdapter(Human.class, new HumanDeserializer())
                .create();
        if ("".equals(str)) {
            return new CollectionManager();
        }
        CollectionManager deserCollection = g.fromJson(str, CollectionManager.class);
        if (Objects.equals(deserCollection, null)) {
            return null;
        }
        return deserCollection;
    }
}

