package net.bodz.bas.html.viz;

import net.bodz.bas.html.io.IHtmlOut;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.PathArrivalEntry;

/**
 * Frame 总是有对应视图.
 * 
 * 不管 var 是给 htm 用还是给后面的 frame-aware 用， ctx.var 总是和视图相关.
 * 
 * 因此，non-frame path arrival 不会有对应的 PathArrivalFrame，也不会调用相关的 aware 设置.
 * 
 * 同时，因为格式或 view qualifier tags 选择问题，不存在相应的 view builder，也就不成为 frame.
 */
public class PathArrivalFrame
        extends PathArrivalEntry<Object> {

    private static final long serialVersionUID = 1L;

    IHtmlViewBuilder<Object> viewBuilder;
    IHtmlOut out;
    IHtmlOut body;

    public PathArrivalFrame(IPathArrival arrival) {
        super(arrival);
    }

    public IHtmlViewBuilder<Object> getViewBuilder() {
        return viewBuilder;
    }

    public void setViewBuilder(IHtmlViewBuilder<Object> viewBuilder) {
        this.viewBuilder = viewBuilder;
    }

    @Override
    public String toString() {
        String tagName = out == null ? null : out.getTagName();
        // String id = outer.getAttributeMap().get("id");
        return String.format("<%s> | %s", tagName/* , id */, getArrival());
    }

}
