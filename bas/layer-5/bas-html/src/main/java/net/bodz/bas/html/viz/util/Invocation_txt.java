package net.bodz.bas.html.viz.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.potato.invoke.IInvocation;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.repr.viz.web.AbstractHttpViewBuilder;
import net.bodz.bas.servlet.IHttpViewContext;
import net.bodz.bas.std.rfc.mime.ContentType;
import net.bodz.bas.std.rfc.mime.ContentTypes;
import net.bodz.bas.typer.Typers;
import net.bodz.bas.typer.std.IParser;
import net.bodz.bas.ui.dom1.IUiRef;

public class Invocation_txt
        extends AbstractHttpViewBuilder<IInvocation> {

    static final Logger logger = LoggerFactory.getLogger(Invocation_txt.class);

    public Invocation_txt() {
        super(IInvocation.class);
    }

    @Override
    public boolean isOrigin(IInvocation value) {
        return true;
    }

    @Override
    public ContentType getContentType(HttpServletRequest request, IInvocation value) {
        return ContentTypes.text_plain;
    }

    @Override
    public Object buildHttpViewStart(IHttpViewContext ctx, HttpServletResponse resp, IUiRef<IInvocation> ref)
            throws ViewBuilderException, IOException {
        PrintWriter out = resp.getWriter();
        IInvocation invocation = ref.get();

        ITokenQueue tokenQueue = ctx.query(ITokenQueue.class);
        if (tokenQueue == null)
            throw new IllegalUsageException("Token queue is null: " + ctx.getRequest().getRequestURL());

        String[] args = tokenQueue.shiftAll();
        int max = invocation.getParameterCount();
        if (args.length > max)
            throw new IllegalArgumentException("Too many arguments.");

        int n = invocation.getParameterCount();
        int missing = 0;

        next_arg: //
        for (int i = 0; i < n; i++) {
            Class<?> parameterType = invocation.getParameterType(i);
            Object parameter;

            do {
                Object injection = ctx.query(parameterType);
                if (injection != null) {
                    parameter = injection;
                    break;
                }

                if (i >= args.length) {
                    missing++;
                    continue next_arg;
                }

                // assert i < args.length;
                String arg = args[i];
                if (parameterType == String.class || parameterType == Object.class) {
                    parameter = arg;
                    break;
                }

                IParser<?> parser = Typers.getTyper(parameterType, IParser.class);
                if (parser == null)
                    throw new ViewBuilderException("No parser for " + parameterType);
                try {
                    parameter = parser.parse(arg);
                } catch (ParseException e) {
                    throw new ViewBuilderException(e.getMessage(), e);
                }
            } while (false);

            invocation.setParameter(i, parameter);
        }

        if (missing > 0) {
            logger.warn("not all parameters are determined.");
            // return 404;
        }

        try {
            resp.setStatus(HttpServletResponse.SC_OK);

            Object result = invocation.invoke();
            // IFormatter formatter = Typers.getTyper(result, IFormatter.class);
            if (invocation.getReturnType() == void.class)
                result = "succeeded";

            out.println(result);
        } catch (InvocationTargetException e) {
            Throwable target = e.getTargetException();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, target.getMessage());
        } catch (Exception e) {
            // resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return null;
    }

}
