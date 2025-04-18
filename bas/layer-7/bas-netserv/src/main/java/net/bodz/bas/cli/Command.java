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
        implements IArgQueue,
                   IJsonForm {

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
    @Override
    public String[] toArray() {
        String[] args = peek(available());
        return args;
    }

    @NotNull
    public String toString(String delim) {
        return peek(delim);
    }

    @Override
    public void setArray(String[] args) {
        setArray(args, 0, args.length);
    }

    @Override
    public void setArray(String[] args, int off, int len) {
        setBackedList(args, off, len);
    }

    @NotNull
    @Override
    public String getArgument(int index) {
        return peekAt(index - 1);
    }

    @Override
    public String getArgument(int index, String fallback) {
        return peekAt(index - 1, fallback);
    }

    @Override
    public void addArgument(@NotNull String arg) {
        add(arg);
    }

    @Override
    public int getArgumentCount() {
        return available();
    }

    @Override
    public Command makeCommand() {
        int n = available();
        if (n == 0)
            return null;
        String name = peek();
        String[] args = new String[n - 1];
        for (int i = 0; i < args.length; i++)
            args[i] = peekAt(i + 1);
        return new Command(name, args);
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
    public String[] getCommandLineArray() {
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