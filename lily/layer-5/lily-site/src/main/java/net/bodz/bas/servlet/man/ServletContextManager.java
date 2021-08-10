package net.bodz.bas.servlet.man;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandler.Context;

import net.bodz.bas.c.javax.servlet.DecoratedServletContext;
import net.bodz.bas.c.org.json.JsonBuffer;
import net.bodz.bas.c.org.json.JsonWriter;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.IPathDispatchable;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.servlet.ctx.CurrentHttpService;
import net.bodz.bas.servlet.persist.AppFileFormat;
import net.bodz.bas.t.variant.IVariantMap;

public class ServletContextManager
        implements IPathDispatchable {

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
            DecoratedServletContext dsc = new DecoratedServletContext(servletContext);
            JsonBuffer out = JsonWriter.buffer();
            try {
                fmt.saveAttributes(out, dsc);
            } catch (IOException e) {
                throw new PathDispatchException(e.getMessage(), e);
            }
            target = out.reconstruct();
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
