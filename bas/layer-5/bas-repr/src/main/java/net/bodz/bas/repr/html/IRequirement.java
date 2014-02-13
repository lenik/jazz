package net.bodz.bas.repr.html;

import net.bodz.bas.meta.build.VersionRange;
import net.bodz.bas.t.order.IPriority;

public interface IRequirement
        extends IPriority {

    String HTTP_HEADER = "http";
    String JAVASCRIPT = "js";
    String CSS = "css";

    /**
     * Id is always unique regardless of the type.
     */
    String getId();

    String getType();

    VersionRange getVersionRange();

    /**
     * Tell the loader to:
     * <ul>
     * <li>Lazy load is possible, for example, load javascript at the end of the body.
     * <li>Ignore the loading error if possible.
     * </ul>
     */
    boolean isOptional();

    /**
     * A candidate URL if the ID is undefined.
     */
    String getURL();

    Object getData();

}
