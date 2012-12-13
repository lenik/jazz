package net.bodz.bas.trait.util;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.rtx.IQueryable;

public class TraitImplCache {

    static final Map<Class<?>, Object> traitImplCache;
    static {
        traitImplCache = new HashMap<Class<?>, Object>();
    }

    public static <T> T getTrait(Class<?> traitImplType, Class<T> traitType)
            throws ReflectiveOperationException {
        Object traitImpl = traitImplCache.get(traitImplType);
        if (traitImpl == null) {
            traitImpl = traitImplType.newInstance();
            traitImplCache.put(traitImplType, traitImpl);
        }

        if (IQueryable.class.isAssignableFrom(traitImplType)) {
            IQueryable querable = (IQueryable) traitImpl;
            T traits = querable.query(traitType);
            return traits;

        } else {
            return traitType.cast(traitImpl);
        }

    }

}
