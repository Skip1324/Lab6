package commands;

import Exeception.IncorrectDataException;
import Manager.CollectionManager;
import Manager.Command;
import Manager.CommandResult;
import Manager.IOManager;
import data.City;

/**
 * Command 'clear'. Clears the collection.
 */
public class ClearCommand extends Command {
    private final CollectionManager col;
    private final IOManager io;
    private String end;
    public ClearCommand(CollectionManager col, IOManager io) {
        super("clear", "очищает коллекцию.");
        this.col = col;
        this.io = io;
    }

    /**
     * Executes the command.
     *
     * @return Command exit status.
     * @throws IncorrectDataException If something goes wrong with data in collection.
     */
    @Override
    public CommandResult execute(String val, City city, String end) throws IncorrectDataException {
        if(!val.isEmpty() && !end.equals("")) {
            return new CommandResult("clear","Некорректный ввод, у этой команды нет аргумента!",false);
        }
        col.clearCollection();
        String message = "Коллекция очищена!";
        return new CommandResult("clear",message,true);
    }

}
