package net.bodz.swt.c3.pageflow;

import net.bodz.bas.repr.req.SimpleRequest;
import net.bodz.bas.t.pojo.PathEntries;

public interface ServiceContext {

    IPageContext getPageContext();

    SimpleRequest getRequest();

    PathEntries getReferrerPath();

}
