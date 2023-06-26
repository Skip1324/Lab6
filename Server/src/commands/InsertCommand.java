package commands;


import Exeception.IncorrectDataException;
import Manager.*;
import data.City;

import java.io.*;
import java.time.Instant;
import java.util.Date;

/**
 * Command 'insert'. Insert a new element to collection.
 */
public class InsertCommand extends Command {
    private final CollectionManager collectionManager;
    private final IOManager io;
    private final CommunicationManager com;
    private final CommunicationManager communicationManager;
    private String message;

    public InsertCommand(CollectionManager collectionManager, IOManager io, CommunicationManager com, CommunicationManager communicationManager) {
        super("insert {element}", "add new element in collection.");
        this.collectionManager = collectionManager;
        this.io = io;
        this.com = com;
        this.communicationManager = communicationManager;

    }


    /**
     * Executes the command.
     * @return Command exit status.
     * @throws IncorrectDataException When data for element is incorrect.
     */
    @Override
    public CommandResult execute(String val, City city, String end) throws IOException {
        if (val.isEmpty() && !end.equals("")) {
            message = ("Введите ключ для нового элемента в аргумент команды!");
            return new CommandResult("insert", message, false);
        }
        if (city != null) {
            try {
                collectionManager.insertElement(Integer.parseInt(val), city);
                message = ("Элемент успешно добавлен.");
                return new CommandResult("insert", message, true);
            } catch (NumberFormatException e) {
                message = "Аргумент команды должен быть числом!";
                return new CommandResult("insert", message, false);
            }
        }
        if (end.equals("") && val.matches("^[0-9]+$")) {
            City c = new City(communicationManager.askName(), communicationManager.askCoordinates(), Date.from(Instant.now()), communicationManager.askArea(), communicationManager.askPopulation(), communicationManager.askMetersAboveSeaLevel(), communicationManager.askAgglomeration(), communicationManager.askClimate(), communicationManager.askStandard(), communicationManager.askGovernor());
            collectionManager.insertElement(Integer.parseInt(val), c);
            message = ("Элемент успешно добавлен.");
            return new CommandResult("insert", message, true);
        } else {
            message = "У этой команды 1 аргумент, который является числом!";
            return new CommandResult("insert", message, false);
        }

    }
}
