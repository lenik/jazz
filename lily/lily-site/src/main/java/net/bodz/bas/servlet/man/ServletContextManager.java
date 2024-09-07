package net.bodz.bas.servlet.man;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandler.Context;

import net.bodz.bas.c.jakarta.servlet.DecoratedServletContext;
import net.bodz.bas.c.org.json.JsonBuffer;
import net.bodz.bas.c.org.json.JsonWriter;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.IPathDispatchable;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.servlet.ctx.CurrentHttpService;
import net.bodz.bas.servlet.persist.AppFileFormat;
import net.bodz.bas.t.variant.IVariantMap;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;

public class ServletContextManager
        implements
            IPathDispatchable {

    AppFileFormat fmt = new AppFileFormat();

    public ServletContextManager() {
    }

    @Override
    public IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        HttpServletRequest request = CurrentHttpService.getRequest();
        ServletContext servletContext = request.getServletContext();

        Object target = null;
        String token = tokens.peek();
        if (token == null)
            return null;

        switch (token) {
        // case "attributes":
        // return null;
        case "list":
            DecoratedServletContext contextAttrs = new DecoratedServletContext(servletContext);
            JsonBuffer out = JsonWriter.buffer();
            try {
                fmt.saveAttributes(out, contextAttrs);
            } catch (Exception e) {
                throw new PathDispatchException(e.getMessage(), e);
            }
            try {
                target = out.reconstruct();
            } catch (ParseException e) {
                throw new PathDispatchException("failed to parse json: " + e.getMessage(), e);
            }
            break;

        case "stop":
            if (servletContext instanceof Context) {
                Context context = (Context) servletContext;
                ContextHandler contextHandler = context.getContextHandler();
                Server server = contextHandler.getServer();
                try {
                    server.stop();
                } catch (Exception e) {
                    throw new PathDispatchException(e.getMessage(), e);
                }
            }
            target = "ok";
            break;
        }

        if (target != null)
            return PathArrival.shift(previous, this, target, tokens);
        return null;
    }

}
