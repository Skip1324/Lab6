package commands;


import Exeception.IncorrectDataException;
import Manager.CollectionManager;
import Manager.Command;
import Manager.CommandResult;
import Manager.IOManager;
import data.City;

/**
 * command 'filter_by_meters_above_sea_level' print element of  the collection if it equals the input.
 */

public class FilterByMetersAboveSeaLevelCommand extends Command {
    private final CollectionManager col;
    private final IOManager io;
    private String message;
    public FilterByMetersAboveSeaLevelCommand(CollectionManager col,IOManager io) {
        super("filter_by_meters_above_sea_level", "выводит элементы, значение поля metersAboveSeaLevel которых равно заданному.");
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
        if(val.isEmpty()&& !end.equals("")){
            message = "Введите расположение над уровнем моря аргументом команды!";
            return new CommandResult("filter_by_meters_above_sea_level", message,false);
        }
        try {
            message = col.filterByMetersAboveSeaLevel(col.getCollection(),Double.parseDouble(val));

        }catch(NumberFormatException e){
            message = ("Передаваться должно только одно значение, которое является числом!");
        }
        return new CommandResult("filter_by_meters_above_sea_level", message,true);
    }


}
