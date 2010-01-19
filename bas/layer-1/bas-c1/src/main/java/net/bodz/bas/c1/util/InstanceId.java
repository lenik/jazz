package net.bodz.bas.c1.util;

import java.util.HashMap;
import java.util.Map;

public class InstanceId {

    private final Class<?> clazz;
    int index;

    public InstanceId(Class<?> clazz) {
        this.clazz = clazz;
    }

    private static Map<Class<?>, Integer> map;
    static {
        map = new HashMap<Class<?>, Integer>();
    }

    public int getInstanceIndex() {
        if (index == 0) {
            Integer nextId = map.get(clazz);
            if (nextId == null) {
                index = 1;
                map.put(clazz, 2);
            } else {
                index = nextId;
                map.put(clazz, index + 1);
            }
        }
        return index;
    }

    public String getSimpleId() {
        return getClass().getSimpleName() + "@" + getInstanceIndex();
    }

    public String getId() {
        return getClass().getName() + "@" + getInstanceIndex();
    }

    public String getNativeId() {
        return getClass().getName() + "@" + System.identityHashCode(null);
    }

    @Override
    public String toString() {
        return getSimpleId();
    }

}
