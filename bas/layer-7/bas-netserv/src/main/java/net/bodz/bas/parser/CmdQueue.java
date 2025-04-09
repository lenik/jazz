package net.bodz.bas.parser;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.NotNull;

public class CmdQueue
        extends BufferedLineParser
        implements Iterable<Command> {

    LinkedList<Command> list = new LinkedList<>();

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public boolean isNotEmpty() {
        return !list.isEmpty();
    }

    public int size() {
        return list.size();
    }

    public void clear() {
        list.clear();
    }

    @NotNull
    @Override
    public Iterator<Command> iterator() {
        return list.iterator();
    }

    public void parseLine(String line)
            throws ParseException {
        // QuoteFormat.QQ.split("")
        String[] args = line.split("\\s+");
        Command cmd = new Command();
        cmd.name = args[0];
        for (int i = 1; i < args.length; i++)
            cmd.addArgument(args[i]);
        list.add(cmd);
    }

}
