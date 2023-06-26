package commands;


import Exeception.IncorrectDataException;
import Manager.Command;
import Manager.CommandResult;
import Manager.IOManager;
import Util.ReceiveServer;
import data.City;

import java.io.*;
import java.util.Stack;
/**
 * Command 'execute_script'. Executes scripts from a file.
 */
public class ExecuteScriptCommand extends Command {
    private final ReceiveServer inp;
    private final IOManager io;
    private final Stack<BufferedReader> previosReaders = new Stack<>();
    private final Stack<File> currentFiles = new Stack<>();
    private String message;
    private String result = "";
    private String scriptResult;
    String start = ("Выполнение скрипта:\n" + "------------------------------------------\n");
    String endik = ("------------------------------------------");

    public ExecuteScriptCommand(ReceiveServer inp, IOManager io) {
        super("execute_script", "считывает и исполняет скрипт из указанного файла.");
        this.inp = inp;
        this.io = io;
    }

    /**
     * Executes the command.
     *
     * @return Command exit status.
     * @throws IncorrectDataException if something goes wrong with data in collection.
     * @throws FileNotFoundException  if file not found.
     */
    @Override
    public CommandResult execute(String val, City city, String end) throws IncorrectDataException, FileNotFoundException {
        if (val.isEmpty() && !end.equals("")) {
            result="Введите аргументом команды файл для скрипта!";
            return new CommandResult("execute_script", result,false);
        }
        File file = new File(val);
        try (BufferedReader newReader = new BufferedReader(new FileReader(file))) {
            if (file.exists() && !currentFiles.contains(file)) {
                currentFiles.push(file);
                previosReaders.push(io.getReader());
                io.setReader(newReader);
                inp.turnOnFile();
                scriptResult = inp.execute_script();
                currentFiles.pop();
                io.setReader(previosReaders.pop());
                inp.turnOffFile();
            } else if (!file.exists()) {
                result = ("Файла не существует!\n");
                return new CommandResult("execute_script", start + result + endik, false);
            } else if (currentFiles.contains(file)) {
                result = ("Возникла рекурсия!\n");
                return new CommandResult("execute_script", start + result + endik, false);
            }
        } catch (IncorrectDataException | NullPointerException e) {
            result = ("Неверные данные в файле!\n");
            return new CommandResult("execute_script", start + scriptResult + result + endik, false);
        } catch (IOException e) {
            result = ("Файла не существует или у вас недостаточно прав!\n");
            return new CommandResult("execute_script", start + result + endik, false);
        }
        return new CommandResult("execute_script", start + scriptResult + endik, true);
    }
}