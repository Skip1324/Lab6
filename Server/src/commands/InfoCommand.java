package commands;


import Exeception.IncorrectDataException;
import Manager.CollectionManager;
import Manager.Command;
import Manager.CommandResult;
import Manager.IOManager;
import data.City;

/**
 * Command 'info'. Prints information about the collection.
 */
public class InfoCommand extends Command {
    private final CollectionManager col;
    private final IOManager io;
    static String f;
    public InfoCommand(CollectionManager col, IOManager io) {
        super("info", "выводит  информацию о коллекции.");
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
            return new CommandResult("info","Некорректный ввод, у этой команды нет аргумента!",false);
        }
        String message = ("Класс коллекции: " +col.getClass() +"\n" + "Время инициализации: " +col.getTime() + "\n" + "Размер коллекции: " +col.getSize());
        return new CommandResult("info", message,true);

    }

}

