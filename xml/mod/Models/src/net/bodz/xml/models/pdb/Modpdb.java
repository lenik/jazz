package net.bodz.xml.models.pdb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import net.bodz.xml.a.XMLDump;
import net.bodz.xml.xmod.util.Docobj;

public class Modpdb extends Docobj {

    @XMLDump
    protected List<Table>       tables = new ArrayList<Table>();

    @XMLDump
    protected List<View>        views  = new ArrayList<View>();

    private Map<String, Docobj> objs;

    public Modpdb() {
        objs = new HashMap<String, Docobj>();
    }

    public void add(Object o) {
        if (!(o instanceof Docobj))
            throw new IllegalArgumentException("Unknown type: " + o.getClass());

        Docobj dobj = (Docobj) o;
        String name = dobj.getName();
        if (objs.containsKey(name))
            throw new RuntimeException("Already added: " + name);
        objs.put(name, dobj);

        if (o instanceof Table)
            tables.add((Table) dobj);
        else if (o instanceof View)
            views.add((View) dobj);
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

    public Table getTable(String name) {
        Docobj dobj = objs.get(name);
        if (dobj == null)
            throw new NoSuchElementException(name);
        if (dobj instanceof Table)
            return (Table) dobj;
        throw new ClassCastException("Not a table: " + name);
    }

    public View getView(String name) {
        Docobj dobj = objs.get(name);
        if (dobj == null)
            throw new NoSuchElementException(name);
        if (dobj instanceof View)
            return (View) dobj;
        throw new ClassCastException("Not a view: " + name);
    }

}
