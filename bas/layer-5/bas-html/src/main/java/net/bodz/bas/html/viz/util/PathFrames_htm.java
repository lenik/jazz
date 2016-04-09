package net.bodz.bas.html.viz.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.viz.AbstractHtmlViewBuilder;
import net.bodz.bas.html.viz.IHtmlViewBuilder;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.http.viz.IHttpViewBuilderFactory;
import net.bodz.bas.http.viz.IndexedHttpViewBuilderFactory;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.repr.viz.ViewBuilderSet;
import net.bodz.bas.ui.dom1.IUiRef;

public class PathFrames_htm
        extends AbstractHtmlViewBuilder<IPathArrival> {

    IHttpViewBuilderFactory viewBuilderFactory = new IndexedHttpViewBuilderFactory();

    public PathFrames_htm() {
        super(PathArrival.class);
    }

    @Override
    public void buildHtmlViewStart(IHtmlViewContext ctx, IHtmlTag out, IUiRef<IPathArrival> ref)
            throws ViewBuilderException, IOException {
        HttpServletResponse resp = ctx.getResponse();
        IPathArrival arrival = ref.get();

        // From inside to outside.
        List<PathHtmlFrame> frames = new ArrayList<PathHtmlFrame>();

        IPathArrival a = arrival;
        while (a != null) {
            Object target = a.getTarget();
            Class<?> targetClass = target.getClass();

            String[] features = {};
            ViewBuilderSet<Object> viewBuilders = viewBuilderFactory.getViewBuilders(targetClass, features);
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
            else
                a = a.getPrevious();
        }

        int size = frames.size();
        for (int i = size - 1; i >= 0; i--) {
            PathHtmlFrame frame = frames.get(i);
            frame.viewBuilder.preview(ctx, frame);
        }

        int builtFrames = 0;
        while (builtFrames < size) {
            PathHtmlFrame frame = frames.get(size - 1 - builtFrames);
            resp.addHeader("X-Page-Frame", frame.viewBuilder.getClass().getSimpleName());
            frame.outer = out;
            frame.viewBuilder.buildHtmlViewStart(ctx, out, frame);
            // XXX TODO
            frame.viewBuilder.buildHtmlViewEnd(ctx, out, frame);
            if (out == null)
                break;
            else
                builtFrames++;
        }

        return out;
    }

}