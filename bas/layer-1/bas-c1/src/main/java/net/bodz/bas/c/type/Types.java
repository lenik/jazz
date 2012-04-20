package net.bodz.bas.c.type;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Types {

    public static Iterable<Class<?>> implications(Class<?> type) {
        return new ImpliedTypes(type);
    }

}

class ImpliedTypes
        implements Iterable<Class<?>> {

    private final Class<?> type;
    private Set<Class<?>> all;

    public ImpliedTypes(Class<?> type) {
        if (type == null)
            throw new NullPointerException("type");
        this.type = type;
    }

    @Override
    public Iterator<Class<?>> iterator() {
        if (all == null) {
            all = new HashSet<Class<?>>();
            traverse(type);
        }
        return all.iterator();
    }

    void traverse(Class<?> type) {
        if (!all.add(type)) // already added
            return;

        if (type.isInterface())
            return;

        Class<?> superclass = type.getSuperclass();
        if (superclass != null)
            traverse(superclass);

        // getInterfaces == getDeclaredInterfaces
        for (Class<?> iface : type.getInterfaces()) {
            traverse(iface);
        }
    }

}
