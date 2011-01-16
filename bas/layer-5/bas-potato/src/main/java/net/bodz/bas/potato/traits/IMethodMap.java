package net.bodz.bas.potato.traits;

import java.util.Map;

public interface IMethodMap
        extends Map<MethodKey, IMethod> {

    /**
     * Find the matching method.
     * <p>
     * If there are multiple matching methods, arbitrary will be returned.
     * 
     * @param methodKey
     *            Non-<code>null</code> method key to match.
     * @return The matched method if any, or <code>null</code> if none matched.
     */
    IMethod getMethod(MethodKey methodKey);

}
