package net.bodz.bas.ui.model.action;

import java.util.List;

public interface ILocationSpec {

    /**
     * The element will be appeared in these locations.
     */
    List<? extends Class<?>> getLocations();

}
