package commands;
import Exeception.IncorrectDataException;
import Manager.*;
import data.City;

import java.io.IOException;
/**
 * Command 'remove_lower'. Removes elements lower than user entered.
 */
public class RemoveLowerCommand extends Command {
    private final CollectionManager col;
    private final IOManager io;
    private final CommunicationManager com;
    private String message;
    public RemoveLowerCommand(CollectionManager col, IOManager io, CommunicationManager com) throws IncorrectDataException {
        super("remove_lower", "удаляет из коллекции все элементы, меньшие, чем заданный.");
        this.col = col;
        this.io = io;
        this.com =com;
    }


    /**
     * Executes the command.
     *
     * @return Command exit status.
     * @throws IOException When something with file went wrong.
     */
    @Override
    public CommandResult execute(String val, City c, String end) throws IOException {
        if(!val.isEmpty() && !end.equals("")){
            message = "Некорректный ввод, у этой команды нет аргумента!";
            return new CommandResult("remove_greater",message,false);
        }
        if(c != null) {
            message = col.removeLowerElement(col.getCollection(), c);
            return new CommandResult("remove_greater", message, true);
        }
        if (end.equals("")) {
            City city =  com.askCity();
            message = col.removeLowerElement(col.getCollection(), city);
            return new CommandResult("remove_greater", message, true);
        } else {
            message = "У этой команды нет аргумента!";
            return new CommandResult("remove_greater", message, false);
        }

    }
}
