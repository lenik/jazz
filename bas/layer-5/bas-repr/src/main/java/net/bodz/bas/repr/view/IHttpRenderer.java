package net.bodz.bas.repr.view;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.meta.codegen.IndexedType;

@IndexedType
public interface IHttpRenderer
        extends IRenderer {

    /**
     * Render the object of specific class.
     * 
     * @param clazz
     *            The class to be renderred as, must be assignable from the object.
     * @param obj
     *            The object to be rendererd.
     * @param req
     *            Restful request object.
     * @param resp
     *            Restful response object.
     * @return <code>true</code> if the corresponding view is defined and processed. Otherwise,
     *         <code>false</code> is returned.
     */
    boolean render(Object obj, HttpServletRequest req, HttpServletResponse resp)
            throws IOException;

}
