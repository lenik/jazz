package net.bodz.bas.html.viz.util;

import net.bodz.bas.html.io.IHtmlOut;
import net.bodz.bas.html.viz.IHtmlViewBuilder;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.PathArrivalEntry;

public class PathHtmlFrame
        extends PathArrivalEntry<Object> {

    private static final long serialVersionUID = 1L;

    IHtmlViewBuilder<Object> viewBuilder;
    IHtmlOut out;
    IHtmlOut body;

    public PathHtmlFrame(IPathArrival arrival) {
        super(arrival);
    }

    @Override
    public String toString() {
        String tagName = out.getTagName();
        // String id = outer.getAttributeMap().get("id");
        return String.format("<%s> | %s", tagName/* , id */, getArrival());
    }

}
