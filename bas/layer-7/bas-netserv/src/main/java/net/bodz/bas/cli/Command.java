package net.bodz.bas.cli;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.meta.decl.NotNull;

/**
 * Represents a command with a name and arguments.
 */
public class Command
        implements IJsonForm {

    /**
     * The name of the command.
     */
    String name;

    /**
     * A list of arguments associated with the command.
     */
    List<String> args = new ArrayList<>();

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

    /**
     * Returns a list of arguments for the command.
     *
     * @return a list of arguments, not null
     */
    @NotNull
    public List<String> getArguments() {
        return args;
    }

    /**
     * Sets the list of arguments for the command.
     *
     * @param args the new list of arguments, not null
     */
    public void setArguments(@NotNull List<String> args) {
        this.args = args;
    }

    /**
     * Returns an argument at a specified index (1-based).
     *
     * @param index the 1-based index of the argument to retrieve
     * @return the argument at the specified index, not null
     */
    @NotNull
    public String getArgument(int index) {
        index--;
        return args.get(index);
    }

    /**
     * Returns an argument at a specified index (1-based), with a fallback value if the index is out of bounds.
     *
     * @param index the 1-based index of the argument to retrieve
     * @param fallback the fallback value to return if the index is out of bounds
     * @return the argument at the specified index or the fallback value
     */
    public String getArgument(int index, String fallback) {
        index--;
        if (index >= 0 && index < args.size())
            return args.get(index);
        else
            return fallback;
    }

    /**
     * Adds an argument to the command.
     *
     * @param arg the argument to add, not null
     */
    public void addArgument(@NotNull String arg) {
        args.add(arg);
    }

    /**
     * Returns the number of arguments associated with the command.
     *
     * @return the number of arguments
     */
    public int getArgumentCount() {
        return args.size();
    }

    /**
     * Checks if there are no arguments associated with the command.
     *
     * @return true if there are no arguments, false otherwise
     */
    public boolean isNoArgument() {
        return args.isEmpty();
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
        return !args.isEmpty();
    }

    /**
     * Checks if an argument is present at a specified index (1-based).
     *
     * @param index the 1-based index of the argument to check
     * @return true if the argument is present at the specified index, false otherwise
     */
    public boolean isArgumentPresent(int index) {
        index--;
        if (index < 0 || index >= args.size())
            return false;
        String arg = args.get(index);
        return arg != null;
    }

    /**
     * Returns a string representation of all arguments, separated by spaces.
     *
     * @return a string of arguments
     */
    @NotNull
    public String getArgumentsLine() {
        StringBuilder buf = new StringBuilder();
        int n = 0;
        for (String arg : args) {
            if (n != 0)
                buf.append(" ");
            buf.append(arg);
            n++;
        }
        return buf.toString();
    }

    /**
     * Returns the first argument in the list, or null if there are no arguments.
     *
     * @return the first argument or null
     */
    public String peek() {
        if (args.isEmpty())
            return null;
        else
            return args.get(0);
    }

    /**
     * Removes and returns the first argument from the list, or null if there are no arguments.
     *
     * @return the first argument or null
     */
    public String shift() {
        if (args.isEmpty())
            return null;
        else
            return args.remove(0);
    }

    /**
     * Removes the specified number of arguments from the start of the list.
     *
     * @param count the number of arguments to remove
     */
    public void shift(int count) {
        if (count <= 0)
            return;
        int size = args.size();
        if (count > size)
            count = size;
        for (int i = count - 1; i >= 0; i--)
            args.remove(i);
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
        for (String arg : args)
            buf.append(" ").append(arg);
        return buf.toString();
    }

    private static final String K_NAME = "name";
    private static final String K_ARGS = "args";

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        name = o.getString(K_NAME);
        args = o.getArrayList(K_ARGS, JsonVariant::castToString);
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        out.entryNotNull(K_NAME, name);
        out.entryNotNull(K_ARGS, args);
    }

}