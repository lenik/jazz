package net.bodz.bas.repr.req;

import java.util.ServiceLoader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.meta.codegen.IndexedTypeLoader;

@IndexedType
public interface IHttpRequestProcessor {

    /**
     * @return <code>false</code> if the request is invalid and the process should be canceled.
     */
    boolean apply(HttpServletRequest request, HttpServletResponse response)
            throws ParseException;

    @IndexedTypeLoader(IHttpRequestProcessor.class)
    class fn {

        /**
         * @return <code>false</code> if the request is invalid and the process should be canceled.
         */
        public static boolean applyAll(HttpServletRequest req, HttpServletResponse resp) {
            for (IHttpRequestProcessor proc : ServiceLoader.load(IHttpRequestProcessor.class))
                try {
                    if (!proc.apply(req, resp)) {
                        // invalid request.
                        return false;
                    }
                } catch (ParseException e) {
                    throw new RuntimeException(e.getMessage(), e);
                }
            return true;
        }

    }

}
