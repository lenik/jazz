package net.bodz.bas.potato.element;

import net.bodz.bas.c.reflect.MethodSignature;

public interface IConstructorMap {

    int getConstructorCount();

    Iterable<IConstructor> getConstructors();

    boolean containsConstructor(MethodSignature signature);

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
