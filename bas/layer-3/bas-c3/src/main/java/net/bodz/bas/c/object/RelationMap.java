package net.bodz.bas.c.object;

import java.util.TreeMap;

/**
 * RelationName -> Object -> Property
 */
public class RelationMap
        extends TreeMap<String, IdentityObjectSet> {

    private static final long serialVersionUID = 1L;

    public synchronized void link(String relation, Object... dests) {
        IdentityObjectSet set = get(relation);
        if (set == null) {
            set = new IdentityObjectSet();
            put(relation, set);
        }
        for (Object dest : dests)
            set.add(dest);
    }

    public synchronized boolean unlink(String relation, Object... dests) {
        IdentityObjectSet set = get(relation);
        if (set == null)
            return false;
        boolean dirty = false;
        for (Object dest : dests)
            dirty |= set.remove(dest);
        if (set.isEmpty())
            remove(relation);
        return dirty;
    }

}
