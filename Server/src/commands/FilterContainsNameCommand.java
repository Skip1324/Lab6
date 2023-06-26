package commands;


import Exeception.IncorrectDataException;
import Manager.CollectionManager;
import Manager.Command;
import Manager.CommandResult;
import Manager.IOManager;
import data.City;

/**
 * command 'Filter_contains_name' print element of collection if there is a substring in its name.
 */

public class FilterContainsNameCommand extends Command {
    private final CollectionManager col;
    private final IOManager io;
    private String message;
    public FilterContainsNameCommand(CollectionManager col,  IOManager io) {
        super("filter_contains_name", "выводит элементы, значение поля name которых содержит заданную подстроку.");
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
        if(val.isEmpty() && !end.equals("")){
            message = "Введите подстроку, которую хотите найти в аргумент команды!";
            return new CommandResult("filter_contains_name", message,false);
        }
        message = col.containsName(col.getCollection(),val);
        return new CommandResult("filter_contains_name",message,true);
    }

}

