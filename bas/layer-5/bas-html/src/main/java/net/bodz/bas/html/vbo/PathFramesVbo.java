package net.bodz.bas.html.vbo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.html.AbstractHtmlViewBuilder;
import net.bodz.bas.html.IHtmlViewBuilder;
import net.bodz.bas.html.IHtmlViewBuilderFactory;
import net.bodz.bas.html.IHtmlViewContext;
import net.bodz.bas.html.IndexedHtmlViewBuilderFactory;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathArrivalEntry;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.ui.dom1.IUiRef;

public class PathFramesVbo
        extends AbstractHtmlViewBuilder<IPathArrival> {

    IHtmlViewBuilderFactory viewBuilderFactory;

    public PathFramesVbo() {
        super(PathArrival.class);
        viewBuilderFactory = IndexedHtmlViewBuilderFactory.getInstance();
    }

    @Override
    public IHtmlViewContext buildHtmlView(IHtmlViewContext ctx, IUiRef<IPathArrival> ref, IOptions options)
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
            IHtmlViewBuilder<Object> viewBuilder = viewBuilderFactory.getViewBuilder(targetClass, features);
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
            frame.ctx0 = ctx;
            ctx = frame.viewBuilder.buildHtmlView(ctx, frame, viewOptions);
            if (ctx == null)
                break;
            else
                builtFrames++;
        }

        for (int i = size - builtFrames; i < size; i++) {
            Frame frame = frames.get(i);
            frame.viewBuilder.buildHtmlViewTail(frame.ctx0, frame, viewOptions);
        }

        return null;
    }

    static class Frame
            extends PathArrivalEntry<Object> {

        private static final long serialVersionUID = 1L;

        IHtmlViewBuilder<Object> viewBuilder;
        IHtmlViewContext ctx0;

        public Frame(IPathArrival arrival) {
            super(arrival);
        }

    }

}
