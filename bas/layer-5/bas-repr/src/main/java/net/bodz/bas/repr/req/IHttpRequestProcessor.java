package net.bodz.bas.repr.req;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.codegen.IndexedType;

@IndexedType
public interface IHttpRequestProcessor {

    void apply(HttpServletRequest request)
            throws ParseException;

}
