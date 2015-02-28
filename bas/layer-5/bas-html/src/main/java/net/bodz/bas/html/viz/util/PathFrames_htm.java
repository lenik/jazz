package net.bodz.bas.html.viz.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.viz.AbstractHttpViewBuilder;
import net.bodz.bas.html.viz.IHttpViewBuilder;
import net.bodz.bas.html.viz.IHttpViewBuilderFactory;
import net.bodz.bas.html.viz.IHttpViewContext;
import net.bodz.bas.html.viz.IndexedHttpViewBuilderFactory;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathArrivalEntry;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.ui.dom1.IUiRef;

public class PathFrames_htm
        extends AbstractHttpViewBuilder<IPathArrival> {

    IHttpViewBuilderFactory viewBuilderFactory;

    public PathFrames_htm() {
        super(PathArrival.class);
        viewBuilderFactory = IndexedHttpViewBuilderFactory.getInstance();
    }

    @Override
    public IHtmlTag buildHtmlView(IHttpViewContext ctx, IHtmlTag out, IUiRef<IPathArrival> ref, IOptions options)
            throws ViewBuilderException, IOException {
        HttpServletResponse resp = ctx.getResponse();
        IPathArrival arrival = ref.get();

        // From inside to outside.
        List<Frame> frames = new ArrayList<Frame>();

        IPathArrival a = arrival;
        while (a != null) {
            Object target = a.getTarget();
            Class<?> targetClass = target.getClass();

            String[] features = {};
            IHttpViewBuilder<Object> viewBuilder = viewBuilderFactory.getViewBuilder(targetClass, features);
            if (viewBuilder == null)
                throw new ViewBuilderException("Can't build view for " + targetClass);

            if (frames.isEmpty() || viewBuilder.isFrame()) {
                Frame frame = new Frame(a);
                frame.viewBuilder = viewBuilder;
                frames.add(frame);
            }

            if (viewBuilder.isOrigin(target))
                break;
            else
                a = a.getPrevious();
        }

        IOptions viewOptions = IOptions.NULL;

        int size = frames.size();
        for (int i = size - 1; i >= 0; i--) {
            Frame frame = frames.get(i);
            frame.viewBuilder.preview(ctx, frame, viewOptions);
        }

        int builtFrames = 0;
        while (builtFrames < size) {
            Frame frame = frames.get(size - 1 - builtFrames);
            resp.addHeader("X-Page-Frame", frame.viewBuilder.getClass().getSimpleName());
            frame.out0 = out;
            out = frame.viewBuilder.buildHtmlView(ctx, out, frame, viewOptions);
            if (out == null)
                break;
            else
                builtFrames++;
        }

        return out;
    }

    static class Frame
            extends PathArrivalEntry<Object> {

        private static final long serialVersionUID = 1L;

        IHttpViewBuilder<Object> viewBuilder;
        IHtmlTag out0;

        public Frame(IPathArrival arrival) {
            super(arrival);
        }

        @Override
        public String toString() {
            String tagName = out0.getTagName();
            String id = out0.getAttributeMap().get("id");
            return String.format("<%s:%s> | %s", tagName, id, getArrival());
        }

    }

}
