package net.bodz.bas.repr.content;

import net.bodz.bas.std.rfc.http.ICacheControl;
import net.bodz.bas.t.order.IPriority;

public interface IContent
        extends ICacheControl, IPriority {

    /**
     * Get the creation time in milliseconds.
     */
    long getCreationTime();

}
