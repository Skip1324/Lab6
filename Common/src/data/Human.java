package data;

import Exeception.IncorrectDataException;

import java.io.Serializable;
import java.util.Objects;
/**
 * Human is a governor of city.
 */
public class Human implements Serializable {
    private String name; //Поле не может быть null, Строка не может быть пустой

    public Human(){
    }

    public Human(String name){
        this.name = name;
    }
    /**
     * @return name of Human.
     */
    public String getName() {
        return name;
    }
    /**
     * Set name of Human.
     * @param name A name of Human.
     * @throws IncorrectDataException if name = null.
     */

    public void setName(String name)throws IncorrectDataException {
        if(name == null){
            throw new IncorrectDataException();
        }
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return   name;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }
        Human comHum = (Human) obj;
        return name.equals(comHum.getName());
    }
}