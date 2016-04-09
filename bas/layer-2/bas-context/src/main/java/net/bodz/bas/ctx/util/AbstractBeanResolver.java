package net.bodz.bas.ctx.util;

import java.util.Map;

import net.bodz.bas.c.java.util.AbstractRoMap;

public abstract class AbstractBeanResolver
        extends AbstractRoMap<String, Object>
        implements IBeanResolver {

    @Override
    public int getPriority() {
        return 0;
    }

    /** ⇱ Implementation Of {@link Map}. */
    /* _____________________________ */static section.iface __MAP__;

    @Override
    public int size() {
        return keySet().size();
    }

    @Override
    public boolean containsKey(Object key) {
        if (key instanceof String)
            return contains((String) key);
        else
            return false;
    }

    @Override
    public Object get(Object key) {
        if (key instanceof String)
            return resolve((String) key);
        else
            return null;
    }

}
