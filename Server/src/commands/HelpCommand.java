package commands;


import Exeception.IncorrectDataException;
import Manager.Command;
import Manager.CommandManager;
import Manager.CommandResult;
import Manager.IOManager;
import data.City;

import java.util.Map;

public class HelpCommand extends Command {
    private final CommandManager com;
    private final IOManager io;

    /**
     * Command 'help'. Print info about all programs.
     */
    public HelpCommand(CommandManager com, IOManager io) {
        super("help", "вывести справку по доступным командам.");
        this.com = com;
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
        if (!val.isEmpty() && !end.equals("")) {
            return new CommandResult("help","Некорректный ввод, у этой команды нет аргумента!",false);
        }
        String message = "";
        Map<String, Command> commandsMap = com.getMap();
        for (Map.Entry<String, Command> entry : commandsMap.entrySet()) {
            message += entry.getValue().getName() + " :" + entry.getValue().getDescription() + "\n";
        }

        return new CommandResult("help", message ,true);
    }

}

