package net.bodz.bas.potato.util;

import net.bodz.bas.c.java.util.IMapEntryLoader;
import net.bodz.bas.err.LazyLoadException;
import net.bodz.bas.typer.Typers;

public class TyperEntryLoader<T>
        implements IMapEntryLoader<Class<?>, T> {

    private final Class<T> typerClass;

    public TyperEntryLoader(Class<T> typerClass) {
        this.typerClass = typerClass;
    }

    @Override
    public T loadValue(Class<?> key)
            throws LazyLoadException {
        T typer = Typers.getTyper(key, typerClass);
        return typer;
    }

    public static <T> TyperEntryLoader<T> forClass(Class<T> typerClass) {
        return new TyperEntryLoader<T>(typerClass);
    }

}
