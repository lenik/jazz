package net.bodz.bas.util.event;

import java.util.EventListener;

public interface IContentChangeListener
        extends EventListener {

    void contentChange(ContentChangeEvent event);

}
