package commands;
import Exeception.IncorrectDataException;
import Manager.CollectionManager;
import Manager.Command;
import Manager.CommandResult;
import Manager.IOManager;
import data.City;

import java.io.IOException;

/**
 * command 'remove_lower_key' Removes elements lower than key, which user entered.
 */

public class RemoveLowerKeyCommand extends Command {
    private final CollectionManager col;
    private final IOManager io;
    private String message;
    public RemoveLowerKeyCommand(CollectionManager col, IOManager io) {
        super("remove_lower_key", "удаляет из коллекции все элементы, ключ которых меньше, чем заданный.");
        this.col = col;
        this. io = io;
    }

    /**
     * Executes the command.
     *
     * @return Command exit status.
     * @throws IncorrectDataException if something goes wrong with data in collection.
     */

    @Override
    public CommandResult execute(String val, City city, String end) throws IOException {
        if (val.isEmpty() && !end.equals("")) {
            message = "Введите ключ по которому хотите удалить в аргумент команды!";
            return new CommandResult("remove_lower_key",message,false);
        }
        try{
            message = col.removeLowerKey(col.getCollection(),Integer.parseInt(val));
        }catch (NumberFormatException e){
            message = "Значение должно быть числом!";
        }
        return new CommandResult("remove_lower_key", message, true);
    }


}
