package commands;


import Exeception.IncorrectDataException;
import Manager.*;
import data.City;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;

/**
 * Command 'update'. Updates the information about selected city by id.
 */
public class UpdateIdCommand extends Command {
    private final CollectionManager col;
    private final IOManager io;
    private final CommunicationManager com;
    private String message;
    public UpdateIdCommand(CollectionManager col, IOManager io, CommunicationManager com) {
        super("update", " обновляет значение элемента коллекции, id которого равен заданному.");
        this.col = col;
        this.io = io;
        this.com = com;
    }
    /**
     * Executes the command.
     *
     * @return Command exit status.
     * @throws IncorrectDataException When data for element is incorrect.
     */
    @Override
    public CommandResult execute(String val, City c, String end) throws IOException {
        if(val.isEmpty() && !end.equals("")){
        message="Введите id элемента,который хотите обновить в аргумент команды!";
        return new CommandResult("update",message,false);
        }
        if(c != null) {
            try {
                message = col.updId(col.getCollection(), Long.parseLong(val), c);
            } catch (NumberFormatException e) {
                message = "Значение должно быть числом!";
            }
            return new CommandResult("update", message, true);
        }
        if (end.equals("") && val.matches("^[0-9]+$")) {
            City city = com.askCity();
            message = col.updId(col.getCollection(), Long.parseLong(val), city);
            return new CommandResult("insert", message, true);
        } else {
            message = "У этой команды 1 аргумент, который является числом!";
            return new CommandResult("insert", message, false);
        }


    }

}

