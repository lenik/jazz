package net.bodz.bas.util;

import java.util.EventListener;

public interface ProgressChangeListener
        extends EventListener {

    void progressChange(ProgressChangeEvent e);

}
