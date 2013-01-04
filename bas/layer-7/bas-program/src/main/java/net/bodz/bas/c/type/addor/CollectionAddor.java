package net.bodz.bas.c.type.addor;

import java.util.Collection;

import net.bodz.bas.err.IllegalUsageException;

public class CollectionAddor
        implements IAddor {

    final Class<?> createType;

    public CollectionAddor(Class<?> createType) {
        if (createType == null)
            throw new NullPointerException("createType");
        this.createType = createType;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object add(Object prev, Object item) {
        Collection<Object> collection;
        if (prev == null)
            try {
                collection = (Collection<Object>) createType.newInstance();
            } catch (ReflectiveOperationException e) {
                throw new IllegalUsageException("Failed to instantiate createType: " + createType, e);
            }
        else
            collection = (Collection<Object>) prev;

        collection.add(item);
        return collection;
    }

}
