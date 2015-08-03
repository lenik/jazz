package net.bodz.bas.html.viz.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.viz.AbstractHtmlViewBuilder;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.potato.invoke.IInvocation;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.std.rfc.mime.ContentType;
import net.bodz.bas.std.rfc.mime.ContentTypes;
import net.bodz.bas.typer.Typers;
import net.bodz.bas.typer.std.IParser;
import net.bodz.bas.ui.dom1.IUiRef;

public class Invocation_txt
        extends AbstractHtmlViewBuilder<IInvocation> {

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
    public IHtmlTag buildHtmlView(IHtmlViewContext ctx, IHtmlTag parent, IUiRef<IInvocation> ref, IOptions options)
            throws ViewBuilderException, IOException {
        HttpServletResponse resp = ctx.getResponse();
        PrintWriter out = resp.getWriter();
        IInvocation invocation = ref.get();

        ITokenQueue tokenQueue = ctx.query(ITokenQueue.class);
        String[] args = tokenQueue.shiftAll();
        int max = invocation.getParameterCount();
        if (args.length > max)
            throw new IllegalArgumentException("Too many arguments.");

        for (int i = 0; i < args.length; i++) {
            String arg = args[i];

            Class<?> parameterType = invocation.getParameterType(i);
            Object parameter;

            if (parameterType == String.class || parameterType == Object.class)
                parameter = arg;
            else {
                IParser<?> parser = Typers.getTyper(parameterType, IParser.class);
                if (parser == null)
                    throw new ViewBuilderException("No parser for " + parameterType);
                try {
                    parameter = parser.parse(arg);
                } catch (ParseException e) {
                    throw new ViewBuilderException(e.getMessage(), e);
                }
            }

            invocation.setParameter(i, parameter);
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
        return parent;
    }

}
