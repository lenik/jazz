package net.bodz.bas.t.variant.conv;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

import net.bodz.bas.c.primitive.Primitives;
import net.bodz.bas.c.type.IndexedTypes;
import net.bodz.bas.c.type.SingletonUtil;
import net.bodz.bas.err.DuplicatedKeyException;
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
            register(INSTANCE);
        }

        for (IVarConverterProvider provider : ServiceLoader.load(IVarConverterProvider.class)) {
            for (IVarConverter<?> converter : provider.getConverters()) {
                register(converter);
            }
        }
    }

    static void register(IVarConverter<?> converter) {
        if (converter == null)
            throw new NullPointerException("converter");
        Class<?> type = converter.getType();
        IVarConverter<?> prev = map.get(type);
        if (prev != null) {
            int priority1 = prev.getPriority();
            int priority2 = converter.getPriority();
            if (priority1 < priority2)
                return;
            if (priority1 == priority2)
                throw new DuplicatedKeyException(String.format(//
                        "Var converter for type %s is already existed: prev=%s, new=%s", //
                        type.getName(), prev.getClass().getName(), converter.getClass().getName()));
        }
        map.put(type, converter);
    }

    public static <T> IVarConverter<T> getConverter(Class<?> type) {
        if (type.isPrimitive())
            type = Primitives.box(type);
        @SuppressWarnings("unchecked")
        IVarConverter<T> converter = (IVarConverter<T>) map.get(type);
        return converter;
    }

}
