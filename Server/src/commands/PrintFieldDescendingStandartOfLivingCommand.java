package commands;


import Exeception.IncorrectDataException;
import Manager.CollectionManager;
import Manager.Command;
import Manager.CommandResult;
import Manager.IOManager;
import data.City;

/**
 * Command 'print_field_descending_standard_of_living'. Print all fields standard of living in descending order.
 */
public class PrintFieldDescendingStandartOfLivingCommand extends Command {
    private final CollectionManager col;
    private final IOManager io;
    public PrintFieldDescendingStandartOfLivingCommand(CollectionManager col, IOManager io) {
        super("print_field_descending_standard_of_living", "выводит значения поля standardOfLiving всех элементов в порядке убывания.");
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
        if(!val.isEmpty() && !end.equals("")){
            return new CommandResult("print_field_descending_standard_of_living","Некорректный ввод, у этой команды нет аргумента!",false);
        }
        String message = col.printStandardOfLiving(col.getCollection());
        return new CommandResult("print_field_descending_standard_of_living", message,true);
    }

}

