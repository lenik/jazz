package net.bodz.bas.typer.util;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.rtx.IQueryable;

public class TyperImplCache {

    static final Map<Class<?>, Object> typerImplCache;
    static {
        typerImplCache = new HashMap<Class<?>, Object>();
    }

    public static <T> T getTyper(Class<?> typerImplType, Class<T> typerClass)
            throws ReflectiveOperationException {
        Object typerImpl = typerImplCache.get(typerImplType);
        if (typerImpl == null) {
            typerImpl = typerImplType.newInstance();
            typerImplCache.put(typerImplType, typerImpl);
        }

        if (IQueryable.class.isAssignableFrom(typerImplType)) {
            IQueryable querable = (IQueryable) typerImpl;
            T typers = querable.query(typerClass);
            return typers;

        } else {
            return typerClass.cast(typerImpl);
        }

    }

}
