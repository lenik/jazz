package net.bodz.bas.potato.traits;

import java.util.Map;

import net.bodz.bas.c.reflect.MethodSignature;

public interface IConstructorMap
        extends Map<MethodSignature, IConstructor> {

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
