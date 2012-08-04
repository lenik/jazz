package net.bodz.swt.c3.pageflow;

import net.bodz.bas.collection.tree.TreePath;
import net.bodz.bas.variant.map.SimpleRequest;

public interface ServiceContext {

    IPageContext getPageContext();

    SimpleRequest getRequest();

    TreePath getReferrerPath();

}
