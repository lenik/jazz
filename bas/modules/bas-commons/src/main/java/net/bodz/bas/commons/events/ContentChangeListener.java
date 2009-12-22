package net.bodz.bas.commons.events;

import java.util.EventListener;

public interface ContentChangeListener
        extends EventListener {

    void contentChange(ContentChangeEvent event);

}
