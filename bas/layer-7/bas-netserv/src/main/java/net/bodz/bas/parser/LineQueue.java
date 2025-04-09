package net.bodz.bas.parser;

import java.util.Iterator;
import java.util.LinkedList;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.NotNull;

public class LineQueue
        extends BufferedLineParser
        implements Iterable<String> {

    LinkedList<String> list = new LinkedList<>();

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
    public Iterator<String> iterator() {
        return list.iterator();
    }

    public void parseLine(String line)
            throws ParseException {
        list.add(line);
    }

}
