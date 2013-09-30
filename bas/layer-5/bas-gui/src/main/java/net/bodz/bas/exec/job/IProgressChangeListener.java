package net.bodz.bas.exec.job;

import java.util.EventListener;

public interface IProgressChangeListener
        extends EventListener {

    void progressChange(ProgressChangeEvent e);

}
