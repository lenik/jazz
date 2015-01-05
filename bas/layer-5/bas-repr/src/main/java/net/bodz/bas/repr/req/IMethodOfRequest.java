package net.bodz.bas.repr.req;

import net.bodz.bas.t.variant.IVariantMap;

public interface IMethodOfRequest {

    String ATTRIBUTE_KEY = IMethodOfRequest.class.getName();

    /**
     * @return Non-<code>null</code> method name.
     * @see MethodNames
     */
    String getMethodName();

    void setMethodName(String methodName);

    IVariantMap<String> getParameters();

}
