package net.bodz.bas.exec.job;

import java.util.EventListener;

public interface IStatusChangeListener
        extends EventListener {

    void statusChange(StatusChangeEvent e);

}
