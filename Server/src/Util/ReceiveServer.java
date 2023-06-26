package Util;
import Manager.*;
import data.City;
import org.apache.log4j.Logger;
import sun.misc.Signal;
import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;


public class ReceiveServer {
    private SenderClient sender;
    private final CommandManager commands;
    private IOManager io;
    private Boolean statusInput = true;
    private Boolean statusFile = false;
    public static Logger logger = Logger.getLogger(ReceiveServer.class);
    public ReceiveServer(CommandManager commands, IOManager io){
        this.commands = commands;
        this.io = io;
    }


    public void execute() throws IOException, ClassNotFoundException {
        DatagramChannel channel = DatagramChannel.open();
        channel.socket().bind(new InetSocketAddress(1050));
        channel.configureBlocking(false);
        Selector selector = Selector.open();
        channel.register(selector, SelectionKey.OP_READ);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            selector.select();

            Set selectedKeys = selector.selectedKeys();
            Iterator iter = selectedKeys.iterator();

            while (iter.hasNext()) {
                SelectionKey key = (SelectionKey) iter.next();

                if (key.isReadable() && key.isValid()) {
                    DatagramChannel datagramChannel = (DatagramChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(10000);
                    SocketAddress clientAddress = datagramChannel.receive(buffer);
                    buffer.flip();
                    byte[] data = new byte[buffer.limit()];
                    buffer.get(data);
                    ByteArrayInputStream bis = new ByteArrayInputStream(data);
                    ObjectInputStream ois = new ObjectInputStream(bis);
                    Object obj = ois.readObject();
                    SenderClient s = (SenderClient) obj;
                    sender = s;
                    String name = sender.getName();
                    String arg = sender.getArg();
                    City city = sender.getCity();
                    logger.info("Данные полученные с сервера:\nКоманда: " + name + "\nАргумент: " + arg + "\nГород: " + city);

                    CommandResult resultExecute;
                    if (Objects.equals(sender, null)) {
                        continue;
                    }
                    if (name.equals("")) {
                        continue;
                    }
                    if (!commands.getMap().containsKey(name)) {
                        if (!statusFile) {
                            String message = ("Неизвестная команда, напишите help, что бы вывести список доступных команд!");
                            resultExecute = new CommandResult("Ответ от сервера", message,false);
                            Serializator<CommandResult> serializator = new Serializator<>(resultExecute);
                            byte[] responseBuffer = serializator.serialize();
                            datagramChannel.send(ByteBuffer.wrap(responseBuffer), clientAddress);
                            logger.info("Данные отправлены на клиент");
                            continue;
                        }
                    }
                    if (!(arg.equals(""))) {
                        resultExecute = commands.getMap().get(name).execute(arg,city, sender.getEnd());
                    } else {
                        resultExecute = commands.getMap().get(name).execute("",city, sender.getEnd());
                    }
                    Serializator serializator = new Serializator<>(resultExecute);
                    byte[] responseBuffer = serializator.serialize();
                    datagramChannel.send(ByteBuffer.wrap(responseBuffer), clientAddress);
                    logger.info("Данные отправлены на клиент");
                }
                if (key.isReadable() && key.channel().equals(io.getReader())) {
                    if (scanner.hasNextLine()) {
                        String line = io.readLine();
                        if (line.equalsIgnoreCase("exit")) {
                            logger.info("Завершение работы сервера...");
                            channel.close();
                            selector.close();
                            scanner.close();
                            return;
                        } else {
                            logger.info("Неизвестная команда: " + line);
                        }
                    }
                }
                iter.remove();
            }
        }
    }


    public String execute_script() throws  IOException {
        String result = "";
        try {
            String name, line;
            String[] command;
            CommandResult resultExecute;
            while (statusInput) {
                line = io.readLine();
                if (Objects.equals(line, null)) {
                    break;
                }
                command = (line.trim() + " ").split(" ", 2);
                name = command[0];
                if (name.equals("")) {
                    continue;
                }
                if (!commands.getMap().containsKey(name)) {
                    if (!statusFile) {
                        result = ("Неизвестная команда, напишите help, что бы вывести список доступных команд!");
                    } else {
                        result = ("Неизвестная команда в файле!\n");
                        break;
                    }
                    continue;
                }
                if (!(command[1].trim()).equals("")) {
                    String value = command[1].trim();
                    resultExecute = commands.getMap().get(name).execute(value,null, "");
                    result += (String) resultExecute.getData() + "\n";
                    continue;
                }
                resultExecute = commands.getMap().get(name).execute("", null, "");
                result += resultExecute.getData() + "\n";
                if (!(!statusFile || resultExecute.getResult())) {
                    break;
                }

            }
        }catch (EOFException e){
            IOManager.printerr("Пока");
        }
        return result;

    }

        /**
         * Turn on file scanner.
         */
        public void turnOnFile(){
            statusFile = true;
        }
        /**
         * Turn off input.
         */
        public void turnOffInput(){
            statusInput = false;
        }
        /**
         * Turn off file scanner.
         */
        public void turnOffFile(){
            statusFile = false;
        }
        public Boolean getStatusFile(){
            return statusFile;
        }
    }




