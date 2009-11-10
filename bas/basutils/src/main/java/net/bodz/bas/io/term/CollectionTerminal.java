package net.bodz.bas.io.term;

import java.util.ArrayList;
import java.util.Collection;

import net.bodz.bas.types.util.Strings;

public class CollectionTerminal extends BufferedTerminal {

    private final Object prefix;
    private final Collection<String> collection;

    public CollectionTerminal(Object prefix, Collection<String> collection) {
        if (collection == null)
            throw new NullPointerException("list"); //$NON-NLS-1$
        this.prefix = prefix;
        this.collection = collection;
    }

    public CollectionTerminal() {
        this.prefix = null;
        this.collection = new ArrayList<String>();
    }

    public Collection<String> getCollection() {
        return collection;
    }

    @Override
    protected void _p(String s) {
        if (prefix != null)
            s = prefix + s;
        collection.add(s);
    }

    @Override
    public String toString() {
        return Strings.join("\n", collection); //$NON-NLS-1$
    }

}
