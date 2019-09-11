package net.bodz.bas.t.variant.conv;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.c.primitive.Primitives;
import net.bodz.bas.c.type.IndexedTypes;
import net.bodz.bas.c.type.SingletonUtil;
import net.bodz.bas.meta.codegen.IndexedTypeLoader;

@IndexedTypeLoader
public class VarConverters {

    static Map<Class<?>, IVarConverter<?>> map = new HashMap<Class<?>, IVarConverter<?>>();

    static {
        load();
    }

    static void load() {
        for (Class<?> converterClass : IndexedTypes.list(IVarConverter.class, false)) {
            IVarConverter<?> INSTANCE = (IVarConverter<?>) SingletonUtil.getInstanceField(converterClass);
            Class<?> type = INSTANCE.getType();
            IVarConverter<?> prev = map.get(type);
            if (prev != null) {
                if (prev.getPriority() < INSTANCE.getPriority())
                    continue;
            }
            map.put(type, INSTANCE);
        }
    }

    public static <T> IVarConverter<T> getConverter(Class<?> type) {
        if (type.isPrimitive())
            type = Primitives.box(type);
        IVarConverter<T> converter = (IVarConverter<T>) map.get(type);
        return converter;
    }

}
