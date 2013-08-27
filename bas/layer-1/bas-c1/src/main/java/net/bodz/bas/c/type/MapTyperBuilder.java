package net.bodz.bas.c.type;

import java.util.HashMap;

public class MapTyperBuilder
        extends AbstractTyperBuilder
        implements IMapEntryBuilder<Object, Object> {

    public MapTyperBuilder(Class<?> typerClass) {
        super(typerClass);
    }

    @Override
    public Object buildTyper(Class<?> type) {
        HashMap<Object, Object> map = new HashMap<Object, Object>();
        return new LazyEntryMap<Object, Object>(map, this);
    }

    @Override
    public boolean containsEntry(Object key) {
        Object val = buildEntry(key);
        return val == null;
    }

    @Override
    public Object buildEntry(Object key) {
        return null;
    }

}
