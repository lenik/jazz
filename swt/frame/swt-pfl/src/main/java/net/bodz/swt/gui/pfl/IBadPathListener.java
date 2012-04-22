package net.bodz.swt.gui.pfl;

import java.util.EventListener;

public interface IBadPathListener
        extends EventListener {

    /**
     * Still within the current page.
     * 
     * To safely dispose the shell, call {@link IPageContext#getShell()}...
     */
    void badPath(BadPathEvent e);

}
