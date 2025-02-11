package net.bodz.lily.entity.type;

import java.util.LinkedHashSet;
import java.util.Map.Entry;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.TreeSet;

import net.bodz.bas.c.java.util.Collections;
import net.bodz.bas.c.type.TypePoMap;
import net.bodz.bas.t.order.PriorityComparator;

public class EntityOpListeners {

    static TypePoMap<Set<IEntityOpListener<Object>>> map = new TypePoMap<>();
    static TypePoMap<Set<IEntityOpListener<Object>>> closureMap = new TypePoMap<>();

    static {
        load();
    }

    static void load() {
        for (IEntityOpListener<?> l : ServiceLoader.load(IEntityOpListener.class)) {
            @SuppressWarnings("unchecked")
            IEntityOpListener<Object> listener = (IEntityOpListener<Object>) l;

            Class<Object> type = listener.getValueType();
            Set<IEntityOpListener<Object>> set = getOrCreateType(type);
            set.add(listener);
        }

        for (Class<?> k : map.keySet()) {
            Set<IEntityOpListener<Object>> closure = computeClosure(k);
            closureMap.put(k, closure);
        }
    }

    public static Set<IEntityOpListener<Object>> query(Class<?> type) {
        Set<IEntityOpListener<Object>> set = closureMap.meet(type);
        return set != null ? set : Collections.emptySet();
    }

    static Set<IEntityOpListener<Object>> getOrCreateType(Class<?> type) {
        return map.computeIfAbsent(type, k -> new LinkedHashSet<>());
    }

    static Set<IEntityOpListener<Object>> computeClosure(Class<?> type) {
        Set<IEntityOpListener<Object>> list = new TreeSet<>(PriorityComparator.INSTANCE);
        Class<?> node = type;
        while (node != null) {
            Entry<Class<?>, Set<IEntityOpListener<Object>>> entry = map.meetEntry(node);
            if (entry == null)
                break;
            list.addAll(entry.getValue());
            node = entry.getKey().getSuperclass();
        }
        return list;
    }

    ////////////

    public static void onCreate(Object o) {
        onCreate(o.getClass(), o);
    }

    public static void onLoad(Object o) {
        onLoad(o.getClass(), o);
    }

    public static void validate(Object o) {
        validate(o.getClass(), o);
    }

    public static void beforeUpdate(Object o, boolean updateExisting) {
        beforeUpdate(o.getClass(), o, updateExisting);
    }

    public static void afterUpdate(Object o, boolean updateExisting) {
        afterUpdate(o.getClass(), o, updateExisting);
    }

    public static boolean canDelete(Object o) {
        return canDelete(o.getClass(), o);
    }

    public static void beforeDelete(Object o) {
        beforeDelete(o.getClass(), o);
    }

    public static void afterDelete(Object o) {
        afterDelete(o.getClass(), o);
    }

    public static void onCreate(Class<?> type, Object o) {
        for (IEntityOpListener<Object> l : query(type)) {
            IEntityOpListener<Object> listener = l;
            listener.onCreate(o);
        }
    }

    public static void onLoad(Class<?> type, Object o) {
        for (IEntityOpListener<Object> l : query(type)) {
            l.onLoad(o);
        }
    }

    public static void validate(Class<?> type, Object o) {
        for (IEntityOpListener<Object> l : query(type)) {
            l.validate(o);
        }
    }

    public static int beforeUpdate(Class<?> type, Object o, boolean updateExisting) {
        int n = 0;
        for (IEntityOpListener<Object> l : query(type)) {
            if (l.beforeUpdate(o, updateExisting))
                n++;
        }
        return n;
    }

    public static int afterUpdate(Class<?> type, Object o, boolean updateExisting) {
        int n = 0;
        for (IEntityOpListener<Object> l : query(type)) {
            if (l.afterUpdate(o, updateExisting))
                n++;
        }
        return n;
    }

    public static boolean canDelete(Class<?> type, Object o) {
        for (IEntityOpListener<Object> l : query(type)) {
            if (!l.canDelete(o))
                return false;
        }
        return true;
    }

    public static int beforeDelete(Class<?> type, Object o) {
        int n = 0;
        for (IEntityOpListener<Object> l : query(type)) {
            if (l.beforeDelete(o))
                n++;
        }
        return n;
    }

    public static int afterDelete(Class<?> type, Object o) {
        int n = 0;
        for (IEntityOpListener<Object> l : query(type)) {
            if (l.afterDelete(o))
                n++;
        }
        return n;
    }

}
