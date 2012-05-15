package net.bodz.bas.disp.req;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Set;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.disp.DispatchException;
import net.bodz.bas.disp.DispatchService;
import net.bodz.bas.disp.IPathArrival;
import net.bodz.bas.disp.ITokenQueue;
import net.bodz.bas.disp.PathArrival;
import net.bodz.bas.disp.naming.ReverseLookupRegistry;
import net.bodz.bas.disp.util.MethodLazyInjector;
import net.bodz.bas.disp.view.DefaultRequestResult;
import net.bodz.bas.disp.view.IHttpRenderer;
import net.bodz.bas.disp.view.IRequestResult;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.log.api.Logger;
import net.bodz.bas.log.api.LoggerFactory;
import net.bodz.bas.util.ObjectInfo;

public class RESTfulService {

    static Logger logger = LoggerFactory.getLogger(RESTfulService.class);

    private transient Object root;

    public void setRoot(Object root) {
        if (root == null)
            throw new NullPointerException("root");
        this.root = root;
    }

    /**
     * So make it Open Session In View.
     */
    // By using OSIV pattern, transactional here is unnecessary.
    // @Transactional
    public boolean processOrNot(final HttpServletRequest req, final HttpServletResponse resp)
            throws IOException, ServletException {

        final DefaultRequestDispatch disp = RESTfulRequestBuilder.build(req);
        if (disp == null)
            return false;

        final DefaultRequestResult qResult = new DefaultRequestResult();

        // 2, Path-dispatch
        ITokenQueue tq = disp.getTokenQueue();
        IPathArrival arrival = dispatch(tq);
        if (arrival == null)
            return false;

        final Object origin = arrival.getTarget();

        // 3, origin is a servlet delegate?
        if (origin instanceof Servlet) {
            Servlet resultServlet = (Servlet) origin;
            resultServlet.service(req, resp);
            return true;
        }

        // 3.1, otherwise execute origin..method-> target

        // Object renderObject = origin;
        // Date expires = arrival.getExpires();

        String method = disp.getMethod();
        if (method == null)
            throw new NullPointerException("method");

        // No controller method, is it a special view?
        disp.setArrival(arrival);

        if (MethodNames.READ.equals(method)) {
            // READ may also use the rest-path?
            if (!tq.isEmpty())
                return false;
        }

        // controller method chaining isn't supported, yet.
        // while (true) {}

        while (arrival != null) {
            Object obj = arrival.getTarget();
            Class<?> objClass = obj.getClass();

            disp.setArrival(arrival);

            if (doRender(obj, disp, resp, false))
                break;

            if (doControllerMethod(objClass, disp, resp))
                break;

            if (doInplaceMethod(objClass, disp, resp))
                break;

            arrival = arrival.getPrevious();
        }

        if (arrival == null) {
            // NOTICE: user should not write to response, if any target is returned.
            Object target = resp.getTarget();

            // Treat if the response have already been done.
            if (target == null) {
                // assert resp.isCommitted();
                return true;
            }

            if (target instanceof String) {
                String location = (String) target;
                resp.sendRedirect(location);
                return true;
            }

            String location = ReverseLookupRegistry.getInstance().getLocation(target);
            if (location != null) {
                location = ILocationConstants.WEB_APP.join(location).resolveAbsolute(req);

                String additionMethod = resp.getMethod();
                if (additionMethod != null) {
                    if (MethodNames.INDEX.equals(additionMethod))
                        location += "/";
                    else
                        location += "?X=" + additionMethod;
                }

                resp.sendRedirect(location);
                return true;
            }
        }

        doRender(origin, disp, resp, true);
        return true;
    } // processOrNot

    private IPathArrival dispatch(ITokenQueue tq)
            throws ServletException {
        Object rootObject = root == null ? moduleManager : root;
        if (rootObject == null)
            // throw new IllegalStateException("Root object isn't set");
            rootObject = ModuleIndex.getStaticInstance();

        DispatchService dispatcher = DispatchService.getInstance();
        IPathArrival arrival;
        try {
            arrival = dispatcher.dispatch(new PathArrival(rootObject), tq);
        } catch (DispatchException e) {
            // resp.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
            throw new ServletException(e.getMessage(), e);
        }
        return arrival;
    }

    /**
     * @return <code>true</code> if any controller method exists and the method is handled.
     */
    private boolean doControllerMethod(Class<?> originClass, DefaultRequestDispatch req, DefaultRequestResult resp)
            throws ServletException {

        ClassMethod cmethod = getControllerMethod(originClass, req.getMethod());
        if (cmethod == null)
            return false;

        // Get the controller instance.
        Class<?> controllerClass = cmethod.getType();
        Method method = cmethod.getMethod();

        Object controller;
        try {
            boolean wired = controllerClass.getAnnotation(Controller.class) != null;
            if (wired)
                controller = applicationContext.getBean(controllerClass);
            else
                controller = StatelessUtil.createOrReuse(controllerClass);
        } catch (Exception e) {
            throw new ServletException(e.getMessage(), e);
        }

        Object target;
        try {
            target = method.invoke(controller, req, resp);
        } catch (Exception e) {
            throw new ServletException(e.getMessage(), e);
        }

        if (target != null)
            resp.setTarget(target);
        return true;
    }

    /**
     * @return <code>true</code> if any inplace method exists and the method is handled.
     */
    private boolean doInplaceMethod(Object origin, final DefaultRequestDispatch req, final DefaultRequestResult resp)
            throws ServletException {
        Class<?> originClass = origin.getClass();
        final Method singleMethod = getSingleMethod(originClass, req.getMethod());
        if (singleMethod == null)
            return false;

        MethodLazyInjector injector = new MethodLazyInjector() {
            @Override
            protected Object require(Class<?> declType) {
                if (ServletRequest.class.isAssignableFrom(declType))
                    return req;

                if (ServletResponse.class.isAssignableFrom(declType))
                    return resp;

                throw new IllegalUsageException("Don't know how to inject parameter " + declType + " in "
                        + singleMethod);
            }
        };

        Object target;
        try {
            target = injector.invoke(origin, singleMethod);
        } catch (Exception e) {
            throw new ServletException(e.getMessage(), e);
        }

        if (target != null)
            resp.setTarget(target);
        return true;
    }

    /**
     * @throws IOException
     * @throws RESTfulException
     */
    private boolean doRender(Object object, IRequestDispatch req, IRequestResult resp, Boolean fallback)
            throws ServletException {

        if (logger.isDebugEnabled()) {
            String objectId = ObjectInfo.getObjectId(object);
            // object + "(" + object.getClass().getSimpleName() + ")";
            logger.debug("Render " + objectId + ", view=" + req.getMethod());
        }

        Class<?> clazz = object.getClass();

        // Set<IRestfulView> views = RestfulViewFactory.getViews();
        Set<IHttpRenderer> views = viewManager.getViews();

        for (IHttpRenderer view : views) {
            if (fallback != null && fallback != view.isFallback())
                continue;

            try {
                if (view.render(clazz, object, req, resp))
                    return true;
            } catch (IOException e) {
                throw new ServletException(e.getMessage(), e);
            }
        }

        return false;
    }

}
