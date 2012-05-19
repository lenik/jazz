package net.bodz.bas.potato.traits.util;

import net.bodz.bas.c.java.util.IMapEntryLoader;
import net.bodz.bas.err.LazyLoadException;
import net.bodz.bas.trait.Traits;

public class TraitEntryLoader<T>
        implements IMapEntryLoader<Class<?>, T> {

    private final Class<T> traitClass;

    public TraitEntryLoader(Class<T> traitClass) {
        this.traitClass = traitClass;
    }

    @Override
    public T loadEntry(Class<?> key)
            throws LazyLoadException {
        T trait = Traits.getTrait(key, traitClass);
        return trait;
    }

    public static <T> TraitEntryLoader<T> forClass(Class<T> traitClass) {
        return new TraitEntryLoader<>(traitClass);
    }

}
