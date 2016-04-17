package net.bodz.bas.ui.event;

import java.util.EventListener;

public interface IStateChangeListener
        extends EventListener {

    void stateChange(StateChangeEvent event);

}
