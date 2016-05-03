package net.bodz.bas.html.viz;

public interface IPathArrivalFrameAware {

    void enter(IHtmlViewContext ctx, PathArrivalFrame frame);

    void leave(IHtmlViewContext ctx, PathArrivalFrame frame);

}
