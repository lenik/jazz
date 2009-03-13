package net.bodz.swt.adapters;

import java.util.EventListener;

import org.eclipse.swt.events.FocusEvent;

public interface LoseFocusListener extends EventListener {

    /**
     * @return whether `doit', <code>false</code> to grab focus again
     */
    boolean loseFocus(FocusEvent event);

}
