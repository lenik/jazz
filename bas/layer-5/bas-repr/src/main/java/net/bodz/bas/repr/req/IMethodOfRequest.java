package net.bodz.bas.repr.req;

import net.bodz.bas.t.variant.IVariantLookupMap;

public interface IMethodOfRequest {

    String getMethodName();

    void setMethodName(String methodName);

    IVariantLookupMap<String> getParameters();

}
