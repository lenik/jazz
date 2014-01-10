package net.bodz.swt.c.pageflow;

import net.bodz.bas.repr.req.MutableRequest;
import net.bodz.bas.t.pojo.PathEntries;

public interface IPageFlow {

    PathEntries getLocation();

    /**
     * Go to relative page in history.
     * 
     * @param pageCount
     *            Positive number to go forward, or negative number to go backward.
     * @throws IllegalStateException
     *             if current page is sticked
     */
    boolean go(int pageCount);

    boolean go(PathEntries next);

    boolean submit(MutableRequest request);

    void addLocationChangeListener(LocationChangeListener listener);

    void removeLocationChangeListener(LocationChangeListener listener);

    void addBadPathListener(IBadPathListener listener);

    void removeBadPathListener(IBadPathListener listener);

}
