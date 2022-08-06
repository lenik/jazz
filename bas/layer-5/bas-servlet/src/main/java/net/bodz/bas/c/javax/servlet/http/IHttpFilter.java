package net.bodz.bas.c.javax.servlet.http;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.t.order.IPriority;

@IndexedType
public interface IHttpFilter
        extends
            HttpFilter,
            IPriority {

    String getPreferredMapping();

}
