package net.bodz.bas.cli;

import java.util.Iterator;
import java.util.LinkedList;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.text.parser.BufferedLineParser;
import net.bodz.bas.t.queue.IQueue;

public class CmdQueue
        extends BufferedLineParser
        implements IQueue<Command> {

    LinkedList<Command> list = new LinkedList<>();

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void clear() {
        list.clear();
    }

    @NotNull
    @Override
    public Iterator<Command> iterator() {
        return list.iterator();
    }

    @Override
    public Command take() {
        return list.removeFirst();
    }

    public void parseLine(@NotNull String line)
            throws ParseException {
        // QuoteFormat.QQ.split("")
        String[] args = line.split("\\s+");
        Command cmd = new Command(args[0]);
        for (int i = 1; i < args.length; i++)
            cmd.addArgument(args[i]);
        list.add(cmd);
    }

}
