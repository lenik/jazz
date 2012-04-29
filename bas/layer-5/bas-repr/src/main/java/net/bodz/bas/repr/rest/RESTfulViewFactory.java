package net.bodz.bas.repr.rest;

import java.util.Collections;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.TreeSet;

public class RESTfulViewFactory {

    static Set<IRESTfulView> views;
    static {
        views = new TreeSet<IRESTfulView>(RESTfulViewComparator.INSTANCE);

        for (IRESTfulView view : ServiceLoader.load(IRESTfulView.class))
            views.add(view);
    }

    public static Set<IRESTfulView> getViews() {
        return Collections.unmodifiableSet(views);
    }

}
