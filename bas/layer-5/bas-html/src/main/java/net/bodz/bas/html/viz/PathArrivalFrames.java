package net.bodz.bas.html.viz;

import java.util.ArrayList;

import net.bodz.bas.c.java.util.Collections;
import net.bodz.bas.ctx.util.IFramedMap;
import net.bodz.bas.http.viz.IHttpViewBuilderFactory;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.repr.viz.ViewBuilderSet;

public class PathArrivalFrames
        extends ArrayList<PathArrivalFrame> {

    private static final long serialVersionUID = 1L;

    public static final String ATTRIBUTE_KEY = PathArrivalFrames.class.getName();

    public void enterAll(IHtmlViewContext context) {
        IFramedMap<String, Object> framedMap = context.getVariableMap();

        for (PathArrivalFrame frame : this) {
            framedMap.enter();
            Object target = frame.get();
            if (target instanceof IPathArrivalFrameAware) {
                IPathArrivalFrameAware aware = (IPathArrivalFrameAware) target;
                aware.enter(context, frame);
            }
        }
    }

    public void leaveAll(IHtmlViewContext context) {
        IFramedMap<String, Object> framedMap = context.getVariableMap();

        for (PathArrivalFrame frame : this) {
            Object target = frame.get();
            if (target instanceof IPathArrivalFrameAware) {
                IPathArrivalFrameAware aware = (IPathArrivalFrameAware) target;
                aware.leave(context, frame);
            }
            framedMap.leave();
        }
    }

    public static PathArrivalFrames convert(IHttpViewBuilderFactory viewBuilderFactory, IPathArrival arrival)
            throws ViewBuilderException {
        // From inside to outside.
        PathArrivalFrames frames = new PathArrivalFrames();

        for (IPathArrival a : arrival.toList().rightToLeft()) {
            Object target = a.getTarget();
            Class<?> targetClass = target.getClass();

            String[] initialTags = {};
            ViewBuilderSet<Object> viewBuilders = viewBuilderFactory.getViewBuilders(targetClass, initialTags);
            IHtmlViewBuilder<Object> viewBuilder = viewBuilders.findFirst(IHtmlViewBuilder.class);
            if (viewBuilder == null)
                throw new ViewBuilderException("Can't build html view for " + targetClass);

            if (frames.isEmpty() || viewBuilder.isFrame()) {
                PathArrivalFrame frame = new PathArrivalFrame(a);
                frame.viewBuilder = viewBuilder;
                frames.add(frame);
            }

            if (viewBuilder.isOrigin(target))
                break;
        }
        Collections.reverse(frames);

        return frames;
    }

}
