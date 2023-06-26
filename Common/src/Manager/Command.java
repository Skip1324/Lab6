package Manager;


import data.City;

import java.io.IOException;
/**
 * Abstract class for all commands.
 */
public abstract class Command {
    protected final String name;
    private final String description;

    protected Command(String name, String description) {
        this.name = name;
        this.description = description;
    }
    /**
     * @return name of command.
     */
    public String getName() {
        return name;
    }
    /**
     * @return description of command.
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return name + " - " + description;
    }
    /**
     * abstract methode execute commands.
     */
    abstract public CommandResult execute(String val, City city, String end) throws IOException;
}