package net.bodz.xml.xmod.modpdb;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.freejava.fp.dump.XMLDump;
import net.sf.freejava.util.ConcatIterator;

public class Modpdb extends Docobj {

    @XMLDump
    protected List<Table> tables = new ArrayList<Table>();

    @XMLDump
    protected List<View>  views  = new ArrayList<View>();

    public void add(Object o) {
        if (o instanceof Table)
            tables.add((Table) o);
        else if (o instanceof View)
            views.add((View) o);
        else
            throw new IllegalArgumentException("Unknown type: " + o.getClass());
    }

    public int size() {
        return tables.size() + views.size();
    }

    public Object get(int index) {
        if (index < tables.size())
            return tables.get(index);
        else
            return views.get(index);
    }

    public Iterator<Docobj> iterator() {
        return new ConcatIterator<Docobj>(tables, views);
    }

}
