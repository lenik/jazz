package net.bodz.bas.exec.job;

import java.util.EventListener;

public interface ProgressChangeListener
        extends EventListener {

    void progressChange(ProgressChangeEvent e);

}
