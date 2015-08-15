package net.bodz.bas.html.viz.util;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.viz.IHtmlViewBuilder;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.PathArrivalEntry;

public class PathHtmlFrame
        extends PathArrivalEntry<Object> {

    private static final long serialVersionUID = 1L;

    IHtmlViewBuilder<Object> viewBuilder;
    IHtmlTag outer;

    public PathHtmlFrame(IPathArrival arrival) {
        super(arrival);
    }

    @Override
    public String toString() {
        String tagName = outer.getTagName();
        String id = outer.getAttributeMap().get("id");
        return String.format("<%s:%s> | %s", tagName, id, getArrival());
    }

}
