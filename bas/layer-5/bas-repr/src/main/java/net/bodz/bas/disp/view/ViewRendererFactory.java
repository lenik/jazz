package net.bodz.bas.disp.view;

import java.util.Collections;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.TreeSet;

public class ViewRendererFactory {

    static Set<IHttpRenderer> views;
    static {
        views = new TreeSet<IHttpRenderer>(ViewRendererComparator.INSTANCE);

        for (IHttpRenderer view : ServiceLoader.load(IHttpRenderer.class))
            views.add(view);
    }

    public static Set<IHttpRenderer> getViews() {
        return Collections.unmodifiableSet(views);
    }

}
