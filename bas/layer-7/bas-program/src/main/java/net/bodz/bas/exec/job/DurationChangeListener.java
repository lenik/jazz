package net.bodz.bas.exec.job;

import java.util.EventListener;

public interface DurationChangeListener
        extends EventListener {

    void durationChange(DurationChangeEvent e);

}