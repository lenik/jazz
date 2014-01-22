package net.bodz.bas.repr.view;

import net.bodz.bas.rtx.IOptions;

public interface IViewable {

    /**
     * Get a named view.
     *
     * @see IViewStruct
     */
    Object view(String viewName, IOptions options);

}
