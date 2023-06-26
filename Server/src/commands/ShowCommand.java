package commands;

import Manager.CollectionManager;
import Manager.Command;
import Manager.CommandResult;
import Manager.IOManager;
import data.City;

import java.io.IOException;
/**
 * Command 'show'. Shows information about all elements of the collection.
 */
public class ShowCommand extends Command {
    private final CollectionManager col;
    private final IOManager io;
    public ShowCommand(CollectionManager col, IOManager io) {
        super("show", "выводит все элементы коллекции в строковом представлении.");
        this.col = col;
        this.io = io;
    }

    /**
     * Executes the command.
     *
     * @return Command exit status.
     */
    @Override
    public CommandResult execute(String val, City city, String end) throws IOException {
        if(!val.isEmpty() && !end.equals("")){
            return new CommandResult("show","Некорректный ввод, у этой команды нет аргумента!",false);
        }
        if(col.getSize() == 0){
            String message = "Коллекция пустая.";
            return new CommandResult("exit",message,true);
        }else {
           String message = col.show();
            return new CommandResult("exit",message,true);
        }

    }

}

