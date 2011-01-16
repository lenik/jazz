package net.bodz.bas.potato.traits;

import java.util.Map;

public interface IConstructorMap
        extends Map<ConstructorKey, IConstructor> {

    /**
     * Find the matching constructor.
     * <p>
     * If there are multiple matching constructors, arbitrary will be returned.
     * 
     * @param constructorKey
     *            Non-<code>null</code> constructor key to match.
     * @return The matched constructor if any, or <code>null</code> if none matched.
     */
    IConstructor getConstructor(ConstructorKey constructorKey);

}
