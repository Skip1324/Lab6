package Manager;

import java.io.*;

/**
 * Operates input and output.
 */

public class IOManager {
    private BufferedReader reader;
    private OutputStreamWriter writer;

    public IOManager(BufferedReader reader, OutputStreamWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public IOManager(BufferedReader reader) {
        this.reader = reader;
    }

    public IOManager() {
    }

    /**
     * @return BufferedReader.
     */
    public BufferedReader getReader() {
        return reader;
    }
    /**
     * Set BufferedReader.
     * @param reader BufferedReader.
     */
    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }

    /**
     * Read new line from input.
     * @return line.
     * @throws IOException If something goes wrong with file.
     */
    public  String readLine() throws IOException {
        return reader.readLine();
    }
    /**
     * Read file.
     * @return lines of file.
     * @throws IOException If something goes wrong with file.
     * @throws FileNotFoundException If file not found.
     */
    public String readFile(File file)throws IOException{
        StringBuilder data = new StringBuilder();
        String line;
        if (!file.exists()) {
            throw new FileNotFoundException();
        } else {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                while ((line = bufferedReader.readLine()) != null) {
                    data.append(line);
                }
            }
        }
        return data.toString();
    }

    /**
     * Print with new line in output.
     * @param o Object to print.
     */
    public static void writeln(Object o) {
        System.out.println(o);
    }

    /**
     * Print errors in output.
     * @param o Object to print.
     */
    public static void printerr(Object o) {
        System.out.println("Ошибка! " + o);
    }
}



