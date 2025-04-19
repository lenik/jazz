package net.bodz.bas.cli;

import java.io.IOException;
import java.util.List;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.repr.path.TokenList;

/**
 * Represents a command with a name and arguments.
 */
public class Command
        extends TokenList
        implements IJsonForm {

    /**
     * The name of the command.
     */
    String name;

    public Command() {
    }

    public Command(String name, String... args) {
        super(args);
        this.name = name;
    }

    /**
     * Returns the name of the command.
     *
     * @return the command name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the command.
     *
     * @param name the new command name
     */
    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    public String[] getArguments() {
        String[] args = peek(available());
        return args;
    }

    public void setArguments(String[] args) {
        setArguments(args, 0, args.length);
    }

    public void setArguments(String[] args, int off, int len) {
        setBackedList(args, off, len);
    }

    /**
     * Returns an argument at a specified index (1-based).
     *
     * @param index the 1-based index of the argument to retrieve
     * @return the argument at the specified index, not null
     */
    @NotNull
    public String getArgument(int index) {
        return peekAt(index - 1);
    }

    /**
     * Returns an argument at a specified index (1-based), with a fallback value if the index is out of bounds.
     *
     * @param index the 1-based index of the argument to retrieve
     * @param fallback the fallback value to return if the index is out of bounds
     * @return the argument at the specified index or the fallback value
     */
    public String getArgument(int index, String fallback) {
        return peekAt(index - 1, fallback);
    }

    /**
     * Adds an argument to the command.
     *
     * @param arg the argument to add, not null
     */
    public void addArgument(@NotNull String arg) {
        add(arg);
    }

    /**
     * Returns the number of arguments associated with the command.
     *
     * @return the number of arguments
     */
    public int getArgumentCount() {
        return available();
    }

    /**
     * Checks if there are no arguments associated with the command.
     *
     * @return true if there are no arguments, false otherwise
     */
    public boolean isNoArgument() {
        return available() == 0;
    }

    /**
     * Checks if there is no argument at a specified index (1-based).
     *
     * @param index the 1-based index of the argument to check
     * @return true if there is no argument at the specified index, false otherwise
     */
    public boolean isNoArgument(int index) {
        return !isArgumentPresent(index);
    }

    /**
     * Checks if there are arguments associated with the command.
     *
     * @return true if there are arguments, false otherwise
     */
    public boolean isArgumentPresent() {
        return available() != 0;
    }

    /**
     * Checks if an argument is present at a specified index (1-based).
     *
     * @param index the 1-based index of the argument to check
     * @return true if the argument is present at the specified index, false otherwise
     */
    public boolean isArgumentPresent(int index) {
        index--;
        if (index < 0 || index >= available())
            return false;
        String arg = peekAt(index);
        return arg != null;
    }

    /**
     * Returns a string representation of all arguments, separated by spaces.
     *
     * @return a string of arguments
     */
    @NotNull
    public String getRemainingArguments() {
        return peek(" ");
    }

    /**
     * Returns the full command line, including the name and all arguments.
     *
     * @return the full command line
     */
    @NotNull
    public String getCommandLine() {
        StringBuilder buf = new StringBuilder();
        buf.append(name);
        int argc = size();
        for (int i = 0; i < argc; i++) {
            buf.append(" ");
            String arg = get(i);
            buf.append(arg);
        }
        return buf.toString();
    }

    @NotNull
    public String[] getCommandArray() {
        String[] array = new String[size() + 1];
        array[0] = name;
        int argc = size();
        for (int i = 0; i < argc; i++)
            array[i + 1] = get(i);
        return array;
    }

    private static final String K_NAME = "name";
    private static final String K_ARGS = "args";

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        name = o.getString(K_NAME);
        List<String> list = o.getArrayList(K_ARGS, JsonVariant::castToString);
        setBackedList(list);
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        out.entryNotNull(K_NAME, name);
        out.entryNotNull(K_ARGS, toArray());
    }

}