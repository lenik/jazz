package net.bodz.bas.t.event;

import java.util.EventListener;

public interface IContentChangeListener
        extends EventListener {

    void contentChange(ContentChangeEvent event);

}
