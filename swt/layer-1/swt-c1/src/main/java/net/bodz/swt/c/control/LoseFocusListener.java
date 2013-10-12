package net.bodz.swt.c.control;

import org.eclipse.swt.events.FocusEvent;

public interface LoseFocusListener {

    /**
     * @return whether `doit', <code>false</code> to grab focus again
     */
    boolean loseFocus(FocusEvent event);

}
