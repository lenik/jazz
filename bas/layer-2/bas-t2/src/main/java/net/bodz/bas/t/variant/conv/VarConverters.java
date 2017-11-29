package net.bodz.bas.t.variant.conv;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableSet;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.TreeSet;

import net.bodz.bas.c.primitive.Primitives;
import net.bodz.bas.c.type.IndexedTypes;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.meta.codegen.IndexedTypeLoader;
import net.bodz.bas.t.order.PriorityComparator;

@IndexedTypeLoader
public class VarConverters {

    static Map<Class<?>, IVarConverter<?>> map = new HashMap<>();
    static Map<Class<?>, NavigableSet<IVarConverterExtension<?>>> extensions = new HashMap<>();

    static {
        load();
    }

    static void load() {
        for (Class<?> converterClass : IndexedTypes.list(IVarConverter.class, false)) {
            IVarConverter<?> instance;
            try {
                Field field = converterClass.getField("instance");
                instance = (IVarConverter<?>) field.get(null);
            } catch (ReflectiveOperationException e) {
                throw new IllegalUsageException("Failed to get the static converter instance.", e);
            }
            Class<?> type = instance.getType();
            IVarConverter<?> prev = map.get(type);
            if (prev != null) {
                if (prev.getPriority() < instance.getPriority())
                    continue;
            }
            map.put(type, instance);
        }

        for (IVarConverterExtension<?> ext : ServiceLoader.load(IVarConverterExtension.class)) {
            Class<?> type = ext.getType();
            NavigableSet<IVarConverterExtension<?>> set = extensions.get(type);
            if (set == null) {
                set = new TreeSet<>(PriorityComparator.INSTANCE);
                extensions.put(type, set);
            }
            set.add(ext);
        }
    }

    public static <T> IVarConverter<T> getConverter(Class<?> type) {
        if (type.isPrimitive())
            type = Primitives.box(type);
        IVarConverter<T> converter = (IVarConverter<T>) map.get(type);
        return converter;
    }

    @SuppressWarnings("unchecked")
    public static <T> Set<IVarConverterExtension<T>> getExtensions(Class<T> type) {
        NavigableSet<IVarConverterExtension<T>> set = (NavigableSet<IVarConverterExtension<T>>) (Object) extensions
                .get(type);
        if (set == null)
            // Java 8: set = Collections.emptySortedSet();
            set = new TreeSet<>();
        return set;
    }

}
