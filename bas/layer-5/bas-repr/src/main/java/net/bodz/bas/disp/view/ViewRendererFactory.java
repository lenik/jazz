package net.bodz.bas.disp.view;

import java.util.Collections;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.TreeSet;

public class ViewRendererFactory {

    static Set<IViewRenderer> views;
    static {
        views = new TreeSet<IViewRenderer>(ViewRendererComparator.INSTANCE);

        for (IViewRenderer view : ServiceLoader.load(IViewRenderer.class))
            views.add(view);
    }

    public static Set<IViewRenderer> getViews() {
        return Collections.unmodifiableSet(views);
    }

}
