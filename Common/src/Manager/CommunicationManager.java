package Manager;

import Exeception.IncorrectDataException;
import Util.ReceiveServer;
import data.*;


import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.function.Function;
import java.util.function.Predicate;

public class CommunicationManager {
    /**
     * Class which fill all fields of city element.
     */
        public IOManager io;
        private final ReceiveServer inputManager;
        private final IOManager ioManager;

        public CommunicationManager(ReceiveServer inputManager, IOManager ioManager) {
            this.inputManager = inputManager;
            this.ioManager = ioManager;
        }
        public CommunicationManager(IOManager ioManager) {
            this.ioManager = ioManager;
            inputManager  = new ReceiveServer(new CommandManager(), new IOManager());
    }

        public <T> T asker(Function<String, T> function,
                           Predicate<T> predicate,
                           String askField,
                           String wrongValue,
                           Boolean nullable) throws IOException {
            String stringIn;
            T value;
            while (true) {
                ioManager.writeln(askField);
                try {
                    stringIn = ioManager.readLine().trim();
                    if ("".equals(stringIn) && nullable) {
                        return null;
                    }
                    value = function.apply(stringIn);
                } catch (IllegalArgumentException e) {
                    ioManager.printerr(wrongValue);
                    continue;
                }
                if (predicate.test(value)) {
                    return value;
                } else {
                    ioManager.printerr(wrongValue);
                }
            }
        }

        public String askName() throws IOException{
            String name;
            if (!inputManager.getStatusFile()) {
                name = asker(arg -> arg,
                        arg -> ((String ) arg).length() > 0,
                        "Введите имя:",
                        "Имя не может быть пустым.", false);

            } else {
                name = ioManager.readLine().trim();
                if (name.equals("")) {
                    throw new IncorrectDataException();
                }
            }
            if (name.matches("^[a-zA-Zа-яА-ЯёЁ]+$")){
                return name;
            }else {
                io.printerr("Имя может содержать только буквы!");
                return askName();
            }
        }

        public Coordinates askCoordinates() throws IOException{
            int coordinateX = 0;
            Float coordinateY = null;
            if (!inputManager.getStatusFile()) {
                coordinateX = asker(Integer::parseInt,
                        arg -> true,
                        "Введите х:",
                        "Введите число!",
                        false);
                coordinateY = asker(Float::parseFloat,
                        arg -> true,
                        "Введите y:",
                        "Введите число!",
                        false);
            } else {
                try {
                    coordinateX = Integer.parseInt(ioManager.readLine());
                    coordinateY = Float.parseFloat(ioManager.readLine());
                } catch (NumberFormatException e) {
                    throw new IncorrectDataException();
                }
            }
            Coordinates cor = new Coordinates(coordinateX, coordinateY);
            return cor;
        }



        public long askArea() throws IOException {
            Long area;
            if (!inputManager.getStatusFile()) {
                area = asker(Long::parseLong,
                        arg -> true,
                        "Введите область:",
                        "Введите число!",
                        false);
            } else {
                try {
                    area = Long.parseLong(ioManager.readLine());
                } catch (NumberFormatException e) {
                    throw new IncorrectDataException();
                }
            }
            if(area > 0){
                return area;
            }else {
                io.printerr("Область не может быть отрицательной!");
                return askArea();
            }
        }

        public int askPopulation() throws IOException {
            Integer population;
            if (!inputManager.getStatusFile()) {
                population = asker(Integer::parseInt,
                        arg -> true,
                        "Введите численность население:",
                        "Введите число!",
                        false);
            } else {
                try {
                    population = Integer.parseInt(ioManager.readLine());
                } catch (NumberFormatException e) {
                    throw new IncorrectDataException();
                }
            }
            if(population > 0){
                return population;
            }else {
                io.printerr("Население не может быть отрицательным!");
                return askPopulation();
            }
        }


        public long askMetersAboveSeaLevel() throws IOException {
            long meters;
            if (!inputManager.getStatusFile()) {
                meters = asker(Long::parseLong,
                        arg -> true,
                        "Введите расстояние над уровнем моря:",
                        "Введите число!",
                        false);
            } else {
                try {
                    meters = Long.parseLong(ioManager.readLine());
                } catch (NumberFormatException e) {
                    throw new IncorrectDataException();
                }
            }
            return meters;
        }

        public Double askAgglomeration() throws IOException {
            Double aglo;
            if (!inputManager.getStatusFile()) {
                aglo = asker(Double::parseDouble,
                        arg -> true,
                        "Введите число агломераций:",
                        "Введите число!",
                        false);
            } else {
                try {
                    aglo = Double.parseDouble(ioManager.readLine());
                } catch (NumberFormatException e) {
                    throw new IncorrectDataException();
                }
            }
            return aglo;
        }


        public Climate askClimate() throws IOException{
            Climate category;
            if (!inputManager.getStatusFile()) {
                category = asker(arg -> Climate.valueOf(arg.toUpperCase()),
                        arg -> true,
                        "Введите климат из списка: " + Climate.sortClimate(),
                        "Некорректные данные!",
                        false);
            } else {
                try {
                    String str = ioManager.readLine().trim().toUpperCase();
                    category = Climate.valueOf(str);
                }
                catch (IllegalArgumentException exception) {
                    throw new IncorrectDataException();
                }
            }
            return category;
        }

        public StandardOfLiving askStandard() throws IOException{
            StandardOfLiving category;
            if (!inputManager.getStatusFile()) {
                category = asker(arg -> StandardOfLiving.valueOf(arg.toUpperCase()),
                        arg -> true,
                        "Выберете уровень жизни из списка:" + StandardOfLiving.sortStandard(),
                        "Некорректные данные!",
                        false);
            } else {
                try {
                    String str = ioManager.readLine().trim().toUpperCase();
                    category = StandardOfLiving.valueOf(str);
                }
                catch (IllegalArgumentException exception) {
                    throw new IncorrectDataException();
                }
            }
            return category;
        }

        public Human askGovernor() throws IOException{
            String nameG;
            if (!inputManager.getStatusFile()) {
                nameG = asker(arg -> arg,
                        arg -> ((String) arg).length() > 0,
                        "Введите имя губернатора:",
                        "Имя не может быть пустым!", false);
            } else {
                nameG = ioManager.readLine().trim();
                if (nameG.equals("")) {
                    throw new IncorrectDataException();
                }
            }
            if (nameG.matches("^[a-zA-Zа-яА-ЯёЁ]+$")){
                Human gov = new Human(nameG);
                return gov;
            }else {
                io.printerr("Имя может содержать только буквы!");
                return askGovernor();
            }
        }
        public City askCity() throws IOException{
            String name = askName();
            Coordinates coordinates = askCoordinates();
            Long area = askArea();
            Integer pop = askPopulation();
            long meter = askMetersAboveSeaLevel();
            Double aglo = askAgglomeration();
            Climate cl = askClimate();
            StandardOfLiving st = askStandard();
            Human h = askGovernor();
            return new City(name, coordinates, Date.from(Instant.now()), area, pop, meter, aglo,cl,st,h);
        }

    }


