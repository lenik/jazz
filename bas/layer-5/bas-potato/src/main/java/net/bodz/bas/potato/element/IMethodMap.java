package net.bodz.bas.potato.element;

import net.bodz.bas.c.reflect.MethodSignature;

public interface IMethodMap {

    int getMethodCount();

    Iterable<IMethod> getMethods();

    boolean containsMethod(MethodSignature signature);

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
