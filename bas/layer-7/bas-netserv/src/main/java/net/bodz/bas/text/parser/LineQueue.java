package net.bodz.bas.text.parser;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.queue.IQueue;

public class LineQueue
        extends BufferedLineParser<String>
        implements IQueue<String>,
                   IParsedValueCallback<String> {

    LinkedList<String> list = new LinkedList<>();

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
    public Iterator<String> iterator() {
        return list.iterator();
    }

    @Override
    public String take() {
        return list.removeFirst();
    }

    public void parse()
            throws IOException, ParseException {
        this.parse(this);
    }

    @Override
    public void parsedValue(IParseContext context, String value) {
        if (value != null)
            list.add(value);
    }

    public String parseLine(@NotNull String line)
            throws ParseException {
        return line;
    }

}
