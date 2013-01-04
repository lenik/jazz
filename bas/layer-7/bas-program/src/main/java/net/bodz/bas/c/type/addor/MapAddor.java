package net.bodz.bas.c.type.addor;

import java.util.Map;
import java.util.Map.Entry;

import net.bodz.bas.err.IllegalUsageException;

public class MapAddor
        implements IAddor {

    final Class<?> createType;

    public MapAddor(Class<?> createType) {
        if (createType == null)
            throw new NullPointerException("createType");
        this.createType = createType;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object add(Object prev, Object item) {
        Map<Object, Object> map;
        if (prev == null)
            try {
                map = (Map<Object, Object>) createType.newInstance();
            } catch (ReflectiveOperationException e) {
                throw new IllegalUsageException("Failed to instantiate createType: " + createType, e);
            }
        else
            map = (Map<Object, Object>) prev;

        Entry<?, ?> entry = (Entry<?, ?>) item;
        map.put(entry.getKey(), entry.getValue());
        return map;
    }

}