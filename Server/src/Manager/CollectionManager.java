package Manager;
import Exeception.IncorrectDataException;
import data.City;
import data.StandardOfLiving;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Class operates the collection itself.
 */
public class CollectionManager {
    private final TreeMap<Integer, City> cityTreeMap;
    private final LocalDate date;
    private final TreeSet<Long> usedID;
    private String s;
    private int colSize;
    private List list;
    private int removedCount = 0;

    public CollectionManager() {
        cityTreeMap = new TreeMap<>();
        date = LocalDate.now();
        usedID = new TreeSet<>();
    }

    public CollectionManager(TreeMap<Integer, City> city) {
        this.cityTreeMap = city;
        date = LocalDate.now();
        usedID = new TreeSet<>();
    }

    /**
     * Adds a new city to collection and set his id.
     * And add id in usedId on automatic generate id.
     * @param element A City to add.
     * @param key A number of City to add.
     */
    public void insertElement(Integer key, City element) {
        try {
            if (Objects.equals(element.getID(), null)) {
                long newId = usedID.isEmpty() ? 1L : usedID.last() + 1L;
                element.setId(newId);
                usedID.add(newId);
            } else if (usedID.contains(element.getID())) {
                long newId = usedID.stream().mapToLong(Long::longValue).max().orElse(0L) + 1L;
                element.setId(newId);
                usedID.add(newId);
            } else {
                usedID.add(element.getID());
            }
        } catch (IncorrectDataException e) {
            e.printStackTrace();
        }
        cityTreeMap.put(key, element);
    }
    /**
     * Removes a City to collection on user input key.
     * @param val A key to remove.
     */
    public String removeKeyElement(TreeMap<Integer, City> cityTreeMap, int val) {
        long cnt = cityTreeMap.entrySet().stream()
                .filter(entry -> entry.getKey() == val)
                .count();
        if (cnt == 1) {
            cityTreeMap.remove(val);
            return "Элемент удален.";
        } else {
            return "Элемента с этим ключом нет.";
        }
    }
    /**
     * Update id of element in collection.
     * @param val A id to update.
     */
    public String updId(TreeMap<Integer, City> cityTreeMap, Long val, City element) throws IOException {
        Optional<Map.Entry<Integer, City>> optionalEntry = cityTreeMap.entrySet().stream()
                .filter(entry -> entry.getValue().getID().equals(val))
                .findFirst();
        if (optionalEntry.isPresent()) {
            Map.Entry<Integer, City> entry = optionalEntry.get();
            Integer key = entry.getKey();
            element.setId(val);
            cityTreeMap.put(key, element);
            return "Элемент обновлен.";
        } else {
            return "Элемента с таким id нет, поэтому его нельзя обновить.";
        }
    }

    /**
     * Clear all element in collection.
     */

    public void clearCollection() {
        usedID.clear();
        cityTreeMap.clear();
    }

    /**
     * Show all element in collection.
     */

    public String show() {
        String message = cityTreeMap.values().stream()
                .map(City::toString)
                .collect(Collectors.joining());

        return message;
    }

    /**
     * @return Initialization time.
     */
    public LocalDate getTime() {
        return date;
    }

    /**
     * @return city Collection.
     */
    public TreeMap<Integer, City> getCollection() {
        return cityTreeMap;
    }

    /**
     * @return Size of Collection.
     */
    public int getSize() {
        return cityTreeMap.size();
    }


    /**
     * Deletes all elements that greater
     * @throws IncorrectDataException if data of input city not correct.
     */
    public String removeGreaterElement(TreeMap<Integer, City> cityTreeMap, City element){
        try {
            List<Integer> keysToRemove = cityTreeMap.entrySet()
                    .stream()
                    .filter(entry -> entry.getValue().compareTo(element) > 0)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());
            removedCount = 0;
            for (Integer key : keysToRemove) {
                usedID.remove(cityTreeMap.get(key).getID());
                cityTreeMap.remove(key);
                removedCount++;
            }
            if (removedCount == 0) {
                return "Элементов большего этого в коллекции нет.";
            } else if (removedCount == 1) {
                return "Элемент удален.";
            } else {
                return "Элементы удалены.";
            }
        }catch (NullPointerException e){
            return "Некорректный ввод, у этой команды нет аргумента!";
        }
    }

    /**
     * Deletes all elements that lower
     * @throws IncorrectDataException if data of input city not correct.
     */
    public String removeLowerElement(TreeMap<Integer, City> cityTreeMap, City element) throws NullPointerException{
        try {
            List<Integer> keysToRemove = cityTreeMap.entrySet()
                    .stream()
                    .filter(entry -> entry.getValue().compareTo(element) < 0)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());

            for (Integer key : keysToRemove) {
                usedID.remove(cityTreeMap.get(key).getID());
                cityTreeMap.remove(key);
                removedCount++;
            }
            removedCount = 0;
            if (removedCount == 0) {
                return "Элементов меньше этого в коллекции нет.";
            } else if (removedCount == 1) {
                return "Элемент удален.";
            } else {
                return "Элементы удалены.";
            }
        }catch (NullPointerException e){
            return "Некорректный ввод, у этой команды нет аргумента!";
        }
    }

    /**
     * Deletes all elements that keys lower which user input.
     * @param val Key val on which to delete lower elements.
     */

    public String removeLowerKey(TreeMap<Integer, City> cityTreeMap, int val) {
        List<Integer> keysToRemove = cityTreeMap.entrySet()
                .stream()
                .filter(entry -> entry.getKey() < val)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        removedCount = 0;
        for (Integer key : keysToRemove) {
            usedID.remove(cityTreeMap.get(key).getID());
            cityTreeMap.remove(key);
            removedCount++;
        }
        if (removedCount == 0) {
            return "Элементов с ключом меньше этого в коллекции нет.";
        } else if (removedCount == 1) {
            return "Элемент удален.";
        } else {
            return "Элементы удалены.";
        }
    }

    /**
     * Print all elements that MetersAboveSeaLevel equals user input.
     * @param db filter on which print to equal user input sea level.
     */
    public String filterByMetersAboveSeaLevel(TreeMap<Integer, City> cityTreeMap, Double db) {
        List<City> cities = cityTreeMap.entrySet().stream()
                .map(entry -> entry.getValue())
                .filter(city -> Double.compare(city.getMetersAboveSeaLevel(), db) == 0)
                .collect(Collectors.toList());
        if (!cities.isEmpty()) {
            String result = cities.stream().map(City::toString).collect(Collectors.joining(", "));
            return result;
        } else {
            return "В коллекции нет элементов с таким уровнем моря.";
        }
    }

    /**
     * Print all elements that name contains str.
     * @param str substring which match with name of element of all collection.
     */
    public String containsName(TreeMap<Integer, City> cityTreeMap, String str) {
        List<City> cities= cityTreeMap.entrySet().stream()
                .map(entry -> entry.getValue())
                .filter(city -> (city.getName().contains(str)))
                .collect(Collectors.toList());
        if(!cities.isEmpty()){
            String result = cities.stream().map(City::toString).collect(Collectors.joining(", "));
            return result;
        }else {
            return "В коллекции нет элементов с такой подстрокой.";
        }
    }

    /**
     * Print field StandardOfLiving of element of all collection.
     */
    public String printStandardOfLiving(TreeMap<Integer, City> cityTreeMap) {
        List<StandardOfLiving> allStandardOfLiving = cityTreeMap.entrySet().stream()
                .map(entry -> entry.getValue().getStandardOfLiving())
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        if (allStandardOfLiving.isEmpty()) {
            return "В коллекции нет элементов.";
        } else {
            return allStandardOfLiving.toString();
        }
    }
}