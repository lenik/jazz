package net.bodz.swt.gui.pfl;

import java.util.EventObject;

import net.bodz.bas.collection.tree.TreePath;

public class BadPathEvent
        extends EventObject {

    private static final long serialVersionUID = 2285635185383477276L;

    public TreePath path;

    public BadPathEvent(PageContext pageContext, TreePath path) {
        super(pageContext);
        this.path = path;
    }

    public PageContext getPageContext() {
        return (PageContext) super.getSource();
    }

    @Override
    public String toString() {
        return "Bad path: " + path;
    }

}
