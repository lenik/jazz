package net.bodz.bas.c.event;

import java.util.EventListener;

public interface IStateChangeListener
        extends EventListener {

    void stateChange(StateChangeEvent event);

}
