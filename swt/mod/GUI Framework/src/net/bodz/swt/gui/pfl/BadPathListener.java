package net.bodz.swt.gui.pfl;

import java.util.EventListener;

public interface BadPathListener extends EventListener {

    /**
     * Still within the current page.
     * 
     * To safely dispose the shell, call {@link PageContext#getShell()}...
     */
    void badPath(BadPathEvent e);

}
