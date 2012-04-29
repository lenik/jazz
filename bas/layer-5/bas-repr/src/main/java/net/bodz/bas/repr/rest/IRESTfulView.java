package net.bodz.bas.repr.rest;

import java.io.IOException;

import net.bodz.bas.util.order.IPriority;

public interface IRESTfulView
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
    boolean render(Class<?> clazz, Object obj, IRESTfulRequest req, IRESTfulResponse resp)
            throws IOException;

    /**
     * The same as {@link #render(Class, Object, IRESTfulRequest, IRESTfulResponse)}, with the
     * actual object class.
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
    boolean render(Object obj, IRESTfulRequest req, IRESTfulResponse resp)
            throws IOException;

    boolean renderTx(Class<?> clazz, Object obj, IRESTfulRequest req, IRESTfulResponse resp)
            throws IOException;

    boolean renderTx(Object obj, IRESTfulRequest req, IRESTfulResponse resp)
            throws IOException;

}
