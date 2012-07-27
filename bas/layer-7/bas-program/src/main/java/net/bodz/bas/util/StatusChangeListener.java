package net.bodz.bas.util;

import java.util.EventListener;

public interface StatusChangeListener
        extends EventListener {

    void statusChange(StatusChangeEvent e);

}
