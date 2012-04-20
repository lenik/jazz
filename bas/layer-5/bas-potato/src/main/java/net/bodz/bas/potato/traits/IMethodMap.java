package net.bodz.bas.potato.traits;

import java.util.Map;

import net.bodz.bas.c.reflect.MethodSignature;

public interface IMethodMap
        extends Map<MethodSignature, IMethod> {

    /**
     * Find the matching method.
     * <p>
     * If there are multiple matching methods, arbitrary will be returned.
     * 
     * @param signature
     *            Non-<code>null</code> method key to match.
     * @return The matched method if any, or <code>null</code> if none matched.
     */
    IMethod getMethod(MethodSignature signature);

}
