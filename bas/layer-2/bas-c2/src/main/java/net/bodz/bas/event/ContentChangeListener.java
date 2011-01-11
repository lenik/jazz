package net.bodz.bas.event;

import java.util.EventListener;

public interface ContentChangeListener
        extends EventListener {

    void contentChange(ContentChangeEvent event);

}
