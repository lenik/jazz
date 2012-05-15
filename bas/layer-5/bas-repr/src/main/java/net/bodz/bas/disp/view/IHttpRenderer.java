package net.bodz.bas.disp.view;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.disp.req.IRequestDispatch;
import net.bodz.bas.util.order.IPriority;

public interface IHttpRenderer
        extends IPriority {

    boolean isFallback();

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
    boolean render(Class<?> clazz, Object obj, HttpServletRequest req, HttpServletResponse resp)
            throws IOException;

    /**
     * The same as {@link #render(Class, Object, IRequestDispatch, IResponseInfo)}, with the actual
     * object class.
     * 
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
