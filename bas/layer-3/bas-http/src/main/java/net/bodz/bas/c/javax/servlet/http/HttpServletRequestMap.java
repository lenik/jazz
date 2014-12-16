package net.bodz.bas.c.javax.servlet.http;

import net.bodz.bas.t.variant.ILookupMap;
import net.bodz.bas.t.variant.VariantMapImpl;

public class HttpServletRequestMap
        extends VariantMapImpl<String> {

    public HttpServletRequestMap(ILookupMap<String, Object> lookupMap) {
        super(lookupMap);
    }

}
