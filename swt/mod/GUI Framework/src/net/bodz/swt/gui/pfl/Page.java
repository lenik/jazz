package net.bodz.swt.gui.pfl;

import net.bodz.swt.gui.ValidateException;

import org.eclipse.swt.graphics.ImageData;

public interface Page {

    String getPageTitle();

    ImageData getPageIcon();

    /**
     * @param prev
     *            full path of previous page
     */
    void enter(String prev, int reason);

    void leave(String next, int reason);

    /**
     * The page is locked and leave is forbidden.
     */
    boolean isLocked();

    /**
     * Can't go ahead (or submit) if validation is failed.
     * 
     * @throws ValidateException
     */
    void validate() throws ValidateException;

    /**
     * Exit state may be used as a path string to the next page.
     */
    Object exitState();

    void addPageStateChangeListener(PageStateChangeListener listener);

    void removePageStateChangeListener(PageStateChangeListener listener);

}
