package net.bodz.bas.exec.job;

import java.util.EventListener;

public interface IDurationChangeListener
        extends EventListener {

    void durationChange(DurationChangeEvent e);

}
