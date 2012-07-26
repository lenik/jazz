package net.bodz.bas.potato.traits;

import java.util.Collection;

import net.bodz.bas.c.reflect.MethodSignature;

public interface IMethodMap {

    int size();

    Collection<IMethod> getMethods();

    /**
     * Find the matching method.
     * <p>
     * If there are multiple matching methods, arbitrary will be returned.
     * 
     * @param signature
     *            Non-<code>null</code> method signature to match. The signature maybe
     *            {@link MethodSignature#isWild() wild}, to match more than single method.
     * @return The matched method if any, or <code>null</code> if none matched.
     */
    IMethod getMethod(MethodSignature signature);

}
