package Manager;


import data.City;

import java.io.Serializable;

public class SenderClient implements Serializable {
    private String arg;
    private String name;
    private City city;
    private String end;
    public SenderClient(String name, String arg){
        this.arg = arg;
        this.name = name;
    }

    public String getArg(){
        return arg;
    }
    public String getName(){
        return name;
    }
    public City getCity(){
        return city;
    }
    public String getEnd(){
        return end;
    }
    public void setCity(City city){
        this.city = city;
    }
    public void setEnd(String end){
        this.end = end;
    }

}
