package net.bodz.bas.c.util;

import java.lang.ref.WeakReference;
import java.util.IdentityHashMap;
import java.util.function.Function;

public abstract class MapGlobal<S, T> {

    final Class<T> targetType;

    // WeakHashMap<S, T> map = new WeakHashMap<>();
    IdentityHashMap<S, WeakReference<T>> map = new IdentityHashMap<>();

    public MapGlobal(Class<T> targetType) {
        this.targetType = targetType;
    }

    public static <S, T> MapGlobal<S, T> fn(Class<T> targetType, Function<S, T> creator) {
        return new FnMapper<>(targetType, creator);
    }

    public T cachedMap(S source) {
        if (source == null)
            return null;

        if (targetType.isInstance(source))
            return targetType.cast(source);

        WeakReference<T> ref = map.get(source);
        T mapped = ref == null ? null : ref.get();
        if (mapped == null) {
            mapped = create(source);
            ref = new WeakReference<>(mapped);
            map.put(source, ref);
        }
        return ref.get();
    }

    public T remove(S source) {
        WeakReference<T> ref = map.remove(source);
        return ref == null ? null : ref.get();
    }

    protected abstract T create(S source);

//    static Map<Class<?>, MapGlobal<?, ?>> all = new HashMap<>();
//
//    public static <S, T> MapGlobal<S, T> forClass(Class<S> sourceClass, Function<S, T> creator) {
//        @SuppressWarnings("unchecked")
//        MapGlobal<S, T> g = (MapGlobal<S, T>) all.get(sourceClass);
//        if (g == null) {
//            g = new FnMapper<>(creator);
//            all.put(sourceClass, g);
//        }
//        return g;
//    }
//
//    public static <S, T> T map(S source, IAdapterFactory<S, T> factory) {
//        return map(source, factory.getSourceType(), (S s) -> factory.create(s));
//    }
//
//    public static <S, T> T map(S source, Class<S> sourceClass, Function<S, T> creator) {
//        MapGlobal<S, T> g = forClass(sourceClass, creator);
//        return g.map(source);
//    }

}
