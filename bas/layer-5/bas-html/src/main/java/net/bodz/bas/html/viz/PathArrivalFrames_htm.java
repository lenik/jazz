package net.bodz.bas.html.viz;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.c.java.util.Collections;
import net.bodz.bas.html.io.IHtmlOut;
import net.bodz.bas.html.servlet.DumpServlet;
import net.bodz.bas.html.viz.builtin.Throwable_htm;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.repr.viz.web.IHttpViewBuilderFactory;
import net.bodz.bas.repr.viz.web.IndexedHttpViewBuilderFactory;
import net.bodz.bas.ui.dom1.IUiRef;
import net.bodz.bas.ui.dom1.UiVar;

public class PathArrivalFrames_htm
        extends AbstractHtmlViewBuilder<PathArrivalFrames> {

    IHttpViewBuilderFactory viewBuilderFactory = new IndexedHttpViewBuilderFactory();

    boolean showExceptionInHtml = true;

    public PathArrivalFrames_htm() {
        super(PathArrivalFrames.class);
    }

    @Override
    public IHtmlOut buildHtmlViewStart(IHtmlViewContext ctx, IHtmlOut out, IUiRef<PathArrivalFrames> ref)
            throws ViewBuilderException, IOException {
        HttpServletRequest req = ctx.getRequest();
        HttpServletResponse resp = ctx.getResponse();

        PathArrivalFrames frames = ref.get();
        ctx.setAttribute(PathArrivalFrames.ATTRIBUTE_KEY, frames);

        for (PathArrivalFrame frame : frames) {
            frame.viewBuilder.precompile(ctx, frame);
        }

        for (PathArrivalFrame frame : frames) {
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
        for (PathArrivalFrame frame : frames) {
            frame.viewBuilder.buildHtmlViewEnd(ctx, frame.out, frame.body, frame);
        }

        return out;
    }

}