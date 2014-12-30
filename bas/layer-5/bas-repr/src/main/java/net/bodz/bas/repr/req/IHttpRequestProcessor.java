package net.bodz.bas.repr.req;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.codegen.IndexedType;

@IndexedType
public interface IHttpRequestProcessor {

    /**
     * @return <code>false</code> if the request is invalid and the process should be canceled.
     */
    boolean apply(HttpServletRequest request, HttpServletResponse response)
            throws ParseException;

}
