package net.bodz.bas.html.vbo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.html.AbstractHtmlViewBuilder;
import net.bodz.bas.html.IHtmlViewBuilder;
import net.bodz.bas.html.IHtmlViewBuilderFactory;
import net.bodz.bas.html.IHttpReprContext;
import net.bodz.bas.html.IndexedHtmlViewBuilderFactory;
import net.bodz.bas.potato.ref.IRefEntry;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathArrivalEntry;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;

public class PathFramesVbo
        extends AbstractHtmlViewBuilder<IPathArrival> {

    IHtmlViewBuilderFactory viewBuilderFactory;

    public PathFramesVbo() {
        super(PathArrival.class);
        viewBuilderFactory = IndexedHtmlViewBuilderFactory.getInstance();
    }

    @Override
    public IHttpReprContext buildHtmlView(IHttpReprContext ctx, IRefEntry<IPathArrival> entry, IOptions options)
            throws ViewBuilderException, IOException {
        IPathArrival arrival = entry.get();

        List<Frame> frames = new ArrayList<Frame>();

        IPathArrival a = arrival;
        while (a != null) {
            Object target = a.getTarget();
            Class<?> targetClass = target.getClass();

            String[] features = {};
            IHtmlViewBuilder<Object> htmlVbo = viewBuilderFactory.getViewBuilder(targetClass, features);
            if (htmlVbo == null)
                throw new UnexpectedException("Can't build view for " + targetClass);

            if (frames.isEmpty() || htmlVbo.isFrame()) {
                Frame frame = new Frame(a);
                frame.viewBuilder = htmlVbo;
                frames.add(frame);
            }

            if (htmlVbo.isOrigin(target))
                break;
            else
                a = a.getPrevious();
        }

        IOptions viewOptions = IOptions.NULL;

        int size = frames.size();
        for (int i = size - 1; i >= 0; i--) {
            Frame frame = frames.get(i);
            // System.out.println("A: " + frame.arrival);
            // System.out.println("    VBO: " + frame.viewBuilder);
            frame.ctx0 = ctx;
            ctx = frame.viewBuilder.buildHtmlView(ctx, frame, viewOptions);
        }

        for (int i = 0; i < size; i++) {
            Frame frame = frames.get(i);
            frame.viewBuilder.buildHtmlViewTail(frame.ctx0, frame, viewOptions);
        }

        return null;
    }

    static class Frame
            extends PathArrivalEntry<Object> {

        private static final long serialVersionUID = 1L;

        IHtmlViewBuilder<Object> viewBuilder;
        IHttpReprContext ctx0;

        public Frame(IPathArrival arrival) {
            super(arrival);
        }

    }

}
