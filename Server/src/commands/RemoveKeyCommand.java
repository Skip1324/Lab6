package commands;


import Exeception.IncorrectDataException;
import Manager.CollectionManager;
import Manager.Command;
import Manager.CommandResult;
import Manager.IOManager;
import data.City;

/**
 * command 'remove_key' remove element of collection, when it equals key of input.
 */

public class RemoveKeyCommand extends Command {
    private final CollectionManager col;
    private final IOManager io;
    private String message;

    public RemoveKeyCommand(CollectionManager col, IOManager io) {
        super("remove_key", "удаляет элемент из коллекции по его ключу.");
        this.col = col;
        this.io = io;
    }

    /**
     * Executes the command.
     *
     * @return Command exit status.
     * @throws IncorrectDataException if something goes wrong with data in collection.
     */

    @Override
    public CommandResult execute(String val, City city, String end) throws IncorrectDataException {
        if (val.isEmpty() && !end.equals("")) {
            message = "Введите ключ по которому хотите удалить в аргумент команды!";
            return new CommandResult("remove_key", message,false);
        }
        try {
            message = col.removeKeyElement(col.getCollection(), Integer.parseInt(val));
        } catch (NumberFormatException e) {
            message = "Значение должно быть числом!";
        }
        return new CommandResult("remove_key", message,true);

    }



}
