package data;

import Exeception.IncorrectDataException;

import java.io.Serializable;
import java.util.Objects;
/**
 * X-Y coordinates of city.
 */
public class Coordinates  implements Serializable {
    private int x;
    private Float y; //Поле не может быть null


    public Coordinates(int x, Float y) throws IncorrectDataException {
        setX(x);
        setY(y);
    }

    public Coordinates() {
    }
    /**
     * @return X-coordinate.
     */
    public float getX() {
        return x;
    }
    /**
     * Set X-coordinate.
     * @param x X-coordinate.
     */
    public void setX(int x) {
        this.x = x;
    }
    /**
     * @return Y-coordinate.
     */
    public Float getY() {
        return y;
    }
    /**
     * Set Y-coordinate.
     * @param y X-coordinate.
     * @throws  IncorrectDataException
     */
    public void setY(Float y) throws IncorrectDataException {
        if (y == null) {
            throw new IncorrectDataException();
        }
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinates: X = " + x + " and Y = " + y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Coordinates compCoor = (Coordinates) object;
        return (Objects.equals(x, compCoor.getX())) && (Objects.equals(y, compCoor.getY()));
    }
}

