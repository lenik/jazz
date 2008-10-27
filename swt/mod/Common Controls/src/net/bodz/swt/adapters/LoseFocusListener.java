package net.bodz.swt.adapters;

import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.internal.SWTEventListener;

public interface LoseFocusListener extends SWTEventListener {

    /**
     * @return whether `doit', <code>false</code> to grab focus again
     */
    boolean loseFocus(FocusEvent event);

}
