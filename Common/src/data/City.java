package data;
import Exeception.IncorrectDataException;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * City stored in the collection.
 */
public class City implements Comparable<City>, Serializable {
    private Long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long area; //Значение поля должно быть больше 0
    private Integer population; //Значение поля должно быть больше 0, Поле не может быть null
    private long metersAboveSeaLevel;
    private Double agglomeration;
    private Climate climate; //Поле может быть null
    private StandardOfLiving standardOfLiving; //Поле может быть null
    private Human governor; //Поле не может быть null

    public City(String name, Coordinates coordinates, Date creationDate, Long area, Integer population, long metersAboveseaLevel, Double agglomeration, Climate climate, StandardOfLiving standardOfLiving, Human governor) throws IncorrectDataException {
        setName(name);
        setCoordinates(coordinates);
        setCreationDate(creationDate);
        setArea(area);
        setPopulation(population);
        setMetersAboveSeaLevel(metersAboveseaLevel);
        setAgglomeration(agglomeration);
        setClimate(climate);
        setStandardOfLiving(standardOfLiving);
        setGovernor(governor);
    }
    public City(){

    }
    /**
     * @return city id.
     */
    public Long getID() {
        return id;
    }

    /**
     * @return city coordinate.
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * @return city area.
     */
    public Long getArea() {
        return area;
    }

    /**
     * @return city climate.
     */
    public Climate getClimate() {
        return climate;
    }

    /**
     * @return city name.
     */
    public String getName() {
        return name;
    }

    /**
     * @return city agglomeration.
     */
    public Double getAgglomeration() {
        return agglomeration;
    }

    /**
     * @return city meters above sea level.
     */
    public double getMetersAboveSeaLevel() {
        return metersAboveSeaLevel;
    }

    /**
     * @return city governor.
     */
    public Human getGovernor() {
        return governor;
    }

    /**
     * @return city population.
     */
    public Integer getPopulation() {
        return population;
    }

    /**
     * @return city standard of living.
     */
    public StandardOfLiving getStandardOfLiving() {
        return standardOfLiving;
    }

    /**
     * @return city date.
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * Set id of city.
     * @param id of city.
     * @throws IncorrectDataException
     */
    public void setId(Long id) throws IncorrectDataException {
        if (id <= 0) {
            throw new IncorrectDataException();
        }
        this.id = id;
    }

    /**
     * Set name of city.
     * @param name of city.
     * @throws IncorrectDataException
     */

    public void setName(String name) throws IncorrectDataException {
        if (name == null) {
            throw new IncorrectDataException();
        }
        this.name = name;
    }

    /**
     * Set coordinates of city.
     * @param coordinates of city.
     * @throws IncorrectDataException
     */
    public void setCoordinates(Coordinates coordinates) throws IncorrectDataException {
        if (coordinates == null) {
            throw new IncorrectDataException();
        }
        this.coordinates = coordinates;
    }

    /**
     * Set date of city.
     * @param time of city.
     * @throws IncorrectDataException
     */
    public void setCreationDate(Date time) throws IncorrectDataException {
        if (time == null) {
            throw new IncorrectDataException();
        }
        this.creationDate = time;

    }

    /**
     * Set area of city.
     * @param area of city.
     * @throws IncorrectDataException
     */

    public void setArea(Long area) throws IncorrectDataException {
        if (area <= 0) {
            throw new IncorrectDataException();
        }
        this.area = area;
    }

    /**
     * Set population of city.
     * @param population of city.
     * @throws IncorrectDataException
     */
    public void setPopulation(Integer population) throws IncorrectDataException {
        if ((population == null) || (population <= 0)) {
            throw new IncorrectDataException();
        }
        this.population = population;
    }

    /**
     * Set meters Above Sea Level of city.
     * @param metersAboveSeaLevel of city.
     * @throws IncorrectDataException
     */
    public void setMetersAboveSeaLevel(long metersAboveSeaLevel) {
        this.metersAboveSeaLevel = metersAboveSeaLevel;
    }

    /**
     * Set agglomeration of city.
     * @param agglomeration of city.
     * @throws IncorrectDataException
     */
    public void setAgglomeration(Double agglomeration) {
        this.agglomeration = agglomeration;
    }

    /**
     * Set climate of city.
     * @param climate of city.
     * @throws IncorrectDataException
     */
    public void setClimate(Climate climate) throws IncorrectDataException {
        if (climate == null) {
            throw new IncorrectDataException();
        }
        this.climate = climate;
    }

    /**
     * Set standard Of Living of city.
     * @param standardOfLiving of city.
     * @throws IncorrectDataException
     */
    public void setStandardOfLiving(StandardOfLiving standardOfLiving) throws IncorrectDataException {
        if (standardOfLiving == null) {
            throw new IncorrectDataException();
        }
        this.standardOfLiving = standardOfLiving;
    }

    /**
     * Set governor of city.
     * @param governor of city.
     * @throws IncorrectDataException
     */
    public void setGovernor(Human governor) throws IncorrectDataException {
        if (governor == null) {
            throw new IncorrectDataException();
        }
        this.governor = governor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, coordinates, creationDate, area, population, metersAboveSeaLevel, agglomeration,
                climate, standardOfLiving, governor);
    }

    /**
     * collection view override.
     * @return String view of collection.
     */

    @Override
    public String toString() throws NullPointerException {
        try {

            return "\n------------" +"\nId: " + id + "\nName: " + name + "\n" + coordinates.toString() +
                    "\nDate time: " + creationDate + "\nArea: " + area + "\nPopulation: " + population +
                    "\nMeters above sea level: " + metersAboveSeaLevel + "\n" + "Agglomeration: " + agglomeration +
                    "\nClimate: " + climate + "\nStandard of living: " + standardOfLiving + "\nGovernor: " + governor;
        }
        catch (NullPointerException e){
        throw new NullPointerException("NullPointerException in City.toString(): some field is null");}
    }
    /**
     * Compare city with city,witch input user.
     * @return int operation result.
     */
    @Override
    public int compareTo(City c) {
        int diffArea = (int) (area - c.getArea());
        if (diffArea == 0) {
            int diffPopulation = (population - c.getPopulation());
            if (diffPopulation == 0) {
                return (name.length() - c.getName().length());
            }else{
                return diffPopulation;
            }
        }else {
            return diffArea;
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        City city = (City) object;
        return (name.equals(city.getName())) && (coordinates.equals(city.getCoordinates())) &&
                (creationDate.equals(city.getCreationDate())) && (Objects.equals(area, city.getArea())) &&
                (population.equals(city.getPopulation())) && (agglomeration.equals(city.getAgglomeration())) &&
                (Objects.equals(metersAboveSeaLevel, city.getMetersAboveSeaLevel())) &&
                (climate.equals(city.getClimate())) && (standardOfLiving.equals(city.getStandardOfLiving())) &&
                (governor.equals(city.getGovernor()));
    }

}

