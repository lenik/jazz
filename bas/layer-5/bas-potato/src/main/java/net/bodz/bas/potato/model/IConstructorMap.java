package net.bodz.bas.potato.model;

import java.util.Collection;

import net.bodz.bas.c.reflect.MethodSignature;

public interface IConstructorMap {

    int size();

    Collection<IConstructor> getConstructors();

    /**
     * Find the matching constructor.
     * <p>
     * If there are multiple matching constructors, arbitrary will be returned.
     * 
     * @param signature
     *            Non-<code>null</code> constructor key to match.
     * @return The matched constructor if any, or <code>null</code> if none matched.
     */
    IConstructor getConstructor(MethodSignature signature);

}
