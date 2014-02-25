package net.bodz.bas.repr.req;

import net.bodz.bas.t.variant.IVariantLookupMap;

public interface IMethodOfRequest {

    String ATTRIBUTE_KEY = IMethodOfRequest.class.getCanonicalName();

    String getMethodName();

    void setMethodName(String methodName);

    IVariantLookupMap<String> getParameters();

}
