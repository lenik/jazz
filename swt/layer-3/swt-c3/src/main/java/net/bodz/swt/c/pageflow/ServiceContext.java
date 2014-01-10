package net.bodz.swt.c.pageflow;

import net.bodz.bas.repr.req.MutableRequest;
import net.bodz.bas.t.pojo.PathEntries;

public interface ServiceContext {

    IPageContext getPageContext();

    MutableRequest getRequest();

    PathEntries getReferrerPath();

}
