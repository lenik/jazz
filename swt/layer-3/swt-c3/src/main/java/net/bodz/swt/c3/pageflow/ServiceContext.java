package net.bodz.swt.c3.pageflow;

import net.bodz.bas.t.pojo.PathEntries;
import net.bodz.bas.variant.map.SimpleRequest;

public interface ServiceContext {

    IPageContext getPageContext();

    SimpleRequest getRequest();

    PathEntries getReferrerPath();

}
