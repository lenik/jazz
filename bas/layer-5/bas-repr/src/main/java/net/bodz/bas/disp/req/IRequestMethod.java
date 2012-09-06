package net.bodz.bas.disp.req;

import net.bodz.bas.util.variant.IVariantLookupMap;

public interface IRequestMethod {

    String ATTRIBUTE_KEY = IRequestMethod.class.getCanonicalName();

    String getMethodName();

    void setMethodName(String methodName);

    IVariantLookupMap<String> getParameters();

}
