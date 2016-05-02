package net.bodz.bas.html.viz.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.c.java.util.Collections;
import net.bodz.bas.html.io.IHtmlOut;
import net.bodz.bas.html.servlet.DumpServlet;
import net.bodz.bas.html.viz.AbstractHtmlViewBuilder;
import net.bodz.bas.html.viz.IHtmlViewBuilder;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.html.viz.builtin.Throwable_htm;
import net.bodz.bas.http.viz.IHttpViewBuilderFactory;
import net.bodz.bas.http.viz.IndexedHttpViewBuilderFactory;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.repr.viz.ViewBuilderSet;
import net.bodz.bas.ui.dom1.IUiRef;
import net.bodz.bas.ui.dom1.UiVar;

public class PathFrames_htm
        extends AbstractHtmlViewBuilder<IPathArrival> {

    IHttpViewBuilderFactory viewBuilderFactory = new IndexedHttpViewBuilderFactory();

    boolean showExceptionInHtml = true;

    public PathFrames_htm() {
        super(PathArrival.class);
    }

    @Override
    public IHtmlOut buildHtmlViewStart(IHtmlViewContext ctx, IHtmlOut out, IUiRef<IPathArrival> ref)
            throws ViewBuilderException, IOException {
        HttpServletRequest req = ctx.getRequest();
        HttpServletResponse resp = ctx.getResponse();
        IPathArrival arrival = ref.get();

        // From inside to outside.
        PathHtmlFrames frames = new PathHtmlFrames();

        for (IPathArrival a : arrival.toList().rightToLeft()) {
            Object target = a.getTarget();
            Class<?> targetClass = target.getClass();

            String[] initialTags = {};
            ViewBuilderSet<Object> viewBuilders = viewBuilderFactory.getViewBuilders(targetClass, initialTags);
            IHtmlViewBuilder<Object> viewBuilder = viewBuilders.findFirst(IHtmlViewBuilder.class);
            if (viewBuilder == null)
                throw new ViewBuilderException("Can't build html view for " + targetClass);

            if (frames.isEmpty() || viewBuilder.isFrame()) {
                PathHtmlFrame frame = new PathHtmlFrame(a);
                frame.viewBuilder = viewBuilder;
                frames.add(frame);
            }

            if (viewBuilder.isOrigin(target))
                break;
        }
        Collections.reverse(frames);

        ctx.setAttribute(PathHtmlFrames.ATTRIBUTE_KEY, frames);

        for (PathHtmlFrame frame : frames) {
            frame.viewBuilder.precompile(ctx, frame);
        }

        for (PathHtmlFrame frame : frames) {
            resp.addHeader("X-Page-Frame", frame.viewBuilder.getClass().getSimpleName());
            frame.out = out;

            IHtmlOut body;
            try {
                body = frame.viewBuilder.buildHtmlViewStart(ctx, out, frame);
            } catch (Exception e) {
                if (showExceptionInHtml) {
                    Throwable_htm vb = new Throwable_htm();
                    vb.buildHtmlView(ctx, out, UiVar.<Throwable> wrap(e));
                }
                req.setAttribute(DumpServlet.EXCEPTION_HANDLED, true);
                throw e;
            }

            frame.body = body;
            if (ctx.isStopped())
                return null;
            if (body == null)
                break;
            out = body;
        }

        Collections.reverse(frames);
        for (PathHtmlFrame frame : frames) {
            frame.viewBuilder.buildHtmlViewEnd(ctx, frame.out, frame.body, frame);
        }

        return out;
    }

}