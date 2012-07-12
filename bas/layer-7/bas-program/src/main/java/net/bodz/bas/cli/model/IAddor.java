package net.bodz.bas.cli.model;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import net.bodz.bas.err.IllegalUsageException;

public interface IAddor {

    Object add(Object prev, Object item);

}

class ArrayAddor
        implements IAddor {

    final Class<?> componentType;

    public ArrayAddor() {
        componentType = Object.class;
    }

    public ArrayAddor(Class<?> componentType) {
        if (componentType == null)
            throw new NullPointerException("componentType");
        this.componentType = componentType;
    }

    @Override
    public Object add(Object prev, Object item) {
        Object array;
        if (prev == null) {
            array = Array.newInstance(componentType, 1);
            Array.set(prev, 0, item);
        } else {
            int len = Array.getLength(prev);
            // array = Arrays.copyOf((Object[]) prev, len + 1);
            Class<?> componentType = prev.getClass().getComponentType(); // this.componentType;
            array = Array.newInstance(componentType, len + 1);
            System.arraycopy(prev, 0, array, 0, len);
            Array.set(array, len, item);
        }
        return array;
    }

}

class CollectionAddor
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

class MapAddor
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