package net.bodz.bas.exec.job;

import java.util.EventListener;

public interface StatusChangeListener
        extends EventListener {

    void statusChange(StatusChangeEvent e);

}
