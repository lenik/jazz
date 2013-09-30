package net.bodz.bas.exec.job;

import java.util.EventListener;

public interface StateChangeListener
        extends EventListener {

    void stateChange(StateChangeEvent event);

}
