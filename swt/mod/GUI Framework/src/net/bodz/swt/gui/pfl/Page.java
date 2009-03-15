package net.bodz.swt.gui.pfl;

import net.bodz.swt.gui.ValidateException;

public interface Page {

    /**
     * @param prev
     *            full path of previous page
     */
    void enter(String prev);

    void leave(String next);

    /**
     * @throws ValidateException
     */
    void validate() throws ValidateException;

    /**
     * Exit state may be used as a path string to the next page.
     */
    Object exitState();

    void addExitStateChangeListener(StateChangeListener listener);

    void removeExitStateChangeListener(StateChangeListener listener);

}
