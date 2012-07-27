package net.bodz.bas.util;

import java.util.EventListener;

public interface StateChangeListener
        extends EventListener {

    void stateChange(StateChangeEvent event);

}
