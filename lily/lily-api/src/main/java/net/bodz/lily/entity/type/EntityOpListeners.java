package net.bodz.lily.entity.type;

import java.util.LinkedHashSet;
import java.util.Map.Entry;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.TreeSet;

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
        return closureMap.meet(type);
    }

    static Set<IEntityOpListener<Object>> getOrCreateType(Class<?> type) {
        Set<IEntityOpListener<Object>> set = map.get(type);
        if (set == null) {
            map.put(type, set = new LinkedHashSet<>());
        }
        return set;
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
        for (IEntityOpListener<?> l : query(type)) {
            @SuppressWarnings("unchecked")
            IEntityOpListener<Object> listener = (IEntityOpListener<Object>) l;
            listener.onCreate(o);
        }
    }

    public static void onLoad(Class<?> type, Object o) {
        for (IEntityOpListener<?> l : query(type)) {
            @SuppressWarnings("unchecked")
            IEntityOpListener<Object> listener = (IEntityOpListener<Object>) l;
            listener.onLoad(o);
        }
    }

    public static void validate(Class<?> type, Object o) {
        for (IEntityOpListener<?> l : query(type)) {
            @SuppressWarnings("unchecked")
            IEntityOpListener<Object> listener = (IEntityOpListener<Object>) l;
            listener.validate(o);
        }
    }

    public static void beforeUpdate(Class<?> type, Object o, boolean updateExisting) {
        for (IEntityOpListener<?> l : query(type)) {
            @SuppressWarnings("unchecked")
            IEntityOpListener<Object> listener = (IEntityOpListener<Object>) l;
            listener.beforeUpdate(o, updateExisting);
        }
    }

    public static void afterUpdate(Class<?> type, Object o, boolean updateExisting) {
        for (IEntityOpListener<?> l : query(type)) {
            @SuppressWarnings("unchecked")
            IEntityOpListener<Object> listener = (IEntityOpListener<Object>) l;
            listener.afterUpdate(o, updateExisting);
        }
    }

    public static boolean canDelete(Class<?> type, Object o) {
        for (IEntityOpListener<?> l : query(type)) {
            @SuppressWarnings("unchecked")
            IEntityOpListener<Object> listener = (IEntityOpListener<Object>) l;
            if (! listener.canDelete(o))
                return false;
        }
        return true;
    }

    public static void beforeDelete(Class<?> type, Object o) {
        for (IEntityOpListener<?> l : query(type)) {
            @SuppressWarnings("unchecked")
            IEntityOpListener<Object> listener = (IEntityOpListener<Object>) l;
            listener.beforeDelete(o);
        }
    }

    public static void afterDelete(Class<?> type, Object o) {
        for (IEntityOpListener<?> l : query(type)) {
            @SuppressWarnings("unchecked")
            IEntityOpListener<Object> listener = (IEntityOpListener<Object>) l;
            listener.afterDelete(o);
        }
    }

}
