package Manager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Serializator<T> {
    private final T t;

    public Serializator(T s) {
        this.t = s;
    }

    public byte[] serialize () {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] bytes = new byte[0];
        try {
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(t);
            oos.flush();
            oos.close();
            bytes = baos.toByteArray();
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
            return bytes;
        }
    }
}
