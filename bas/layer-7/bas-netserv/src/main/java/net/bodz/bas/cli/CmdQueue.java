package net.bodz.bas.cli;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import net.bodz.bas.c.java.util.regex.QuoteFormat;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.queue.IQueue;
import net.bodz.bas.text.parser.BufferedLineParser;
import net.bodz.bas.text.parser.IParsedValueCallback;
import net.bodz.bas.text.parser.IParseContext;

public class CmdQueue
        extends BufferedLineParser<Command>
        implements IQueue<Command>,
                   IParsedValueCallback<Command> {

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

    public void parse()
            throws IOException, ParseException {
        parse(this);
    }

    @Override
    public void parsedValue(IParseContext context, Command value) {
        if (value != null)
            list.add(value);
    }

    public Command parseLine(@NotNull String line)
            throws ParseException {
        line = line.trim();
        if (line.isEmpty())
            return null;
        if (line.startsWith("#"))
            return null;

        String[] args = QuoteFormat.QQ.splitDequoted("\\s+", line);
        Command cmd = new Command(args[0]);
        for (int i = 1; i < args.length; i++)
            cmd.addArgument(args[i]);
        return cmd;
    }

}
