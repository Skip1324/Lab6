package commands;

import Manager.CollectionManager;
import Manager.Command;
import Manager.CommandResult;
import Manager.IOManager;
import Util.ParsJson;
import Util.ReceiveServer;
import data.City;


import java.io.File;
import java.io.IOException;

public class ExitCommand extends Command {
    private final CollectionManager col;
    private final ParsJson pars;
    private final File file;
    private final IOManager ioManager;
    private ReceiveServer receiveServer;
    /**
     * Command 'save'. Saves the collection to str file.
     */
    public ExitCommand(CollectionManager col, ParsJson pars, File file, IOManager ioManager, ReceiveServer receiveServer) {
        super("save", "сохраняет коллекцию в файл.");
        this.col = col;
        this.pars = pars;
        this.file = file;
        this.ioManager = ioManager;
        this.receiveServer = receiveServer;
    }

    /**
     * Executes the command.
     *
     * @return Command exit status.
     * @throws IOException When something with file went wrong.
     */

    @Override
    public CommandResult execute(String val, City city, String end) throws IOException {
        try {
            if (!val.isEmpty() && !end.equals("")) {
                return new CommandResult("exit","Некорректный ввод, у этой команды нет аргумента!",false);
            }

            if (pars.serialize(col, file)) {
//                receiveServer.turnOffInput();
                return new CommandResult("exit","До встречи!",true);
            } else {
                ioManager.writeln("Коллекция  не сохранилась!");
            }
            return new CommandResult();
        } catch (IOException e) {
            ioManager.printerr("У пользователя не достаточно прав!");
            return new CommandResult();
        }
    }

}

