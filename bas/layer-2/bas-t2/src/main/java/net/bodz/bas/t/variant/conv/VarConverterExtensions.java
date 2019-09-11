package net.bodz.bas.t.variant.conv;

import java.util.HashMap;
import java.util.Map;
import java.util.NavigableSet;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.TreeSet;

import net.bodz.bas.t.order.PriorityComparator;

public class VarConverterExtensions {

    static Map<Class<?>, NavigableSet<IVarConverterExtension<?>>> extensions //
    = new HashMap<Class<?>, NavigableSet<IVarConverterExtension<?>>>();

    static {
        load();
    }

    static void load() {
        for (IVarConverterExtension<?> ext : ServiceLoader.load(IVarConverterExtension.class)) {
            Class<?> type = ext.getType();
            NavigableSet<IVarConverterExtension<?>> set = extensions.get(type);
            if (set == null) {
                set = new TreeSet<IVarConverterExtension<?>>(PriorityComparator.INSTANCE);
                extensions.put(type, set);
            }
            set.add(ext);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> Set<IVarConverterExtension<T>> getExtensions(Class<T> type) {
        NavigableSet<IVarConverterExtension<T>> set = (NavigableSet<IVarConverterExtension<T>>) (Object) extensions
                .get(type);
        if (set == null)
            // Java 8: set = Collections.emptySortedSet();
            set = new TreeSet<IVarConverterExtension<T>>();
        return set;
    }
}
