package net.bodz.swt.c.pageflow;

import java.util.EventObject;

import net.bodz.bas.t.pojo.PathEntries;

public class BadPathEvent
        extends EventObject {

    private static final long serialVersionUID = 2285635185383477276L;

    public PathEntries path;

    public BadPathEvent(IPageContext pageContext, PathEntries path) {
        super(pageContext);
        this.path = path;
    }

    public IPageContext getPageContext() {
        return (IPageContext) super.getSource();
    }

    @Override
    public String toString() {
        return "Bad path: " + path;
    }

}
