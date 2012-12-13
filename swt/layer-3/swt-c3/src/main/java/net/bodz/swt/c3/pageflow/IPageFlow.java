package net.bodz.swt.c3.pageflow;

import net.bodz.bas.t.tree.TreePath;
import net.bodz.bas.variant.map.SimpleRequest;

public interface IPageFlow {

    TreePath getLocation();

    /**
     * Go to relative page in history.
     * 
     * @param pageCount
     *            Positive number to go forward, or negative number to go backward.
     * @throws IllegalStateException
     *             if current page is sticked
     */
    boolean go(int pageCount);

    boolean go(TreePath next);

    boolean submit(SimpleRequest request);

    void addLocationChangeListener(LocationChangeListener listener);

    void removeLocationChangeListener(LocationChangeListener listener);

    void addBadPathListener(IBadPathListener listener);

    void removeBadPathListener(IBadPathListener listener);

}
