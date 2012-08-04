package net.bodz.swt.director.layers;

import java.util.EventListener;

public interface InvalidateListener
        extends EventListener {

    void becameInvalid(InvalidateEvent event);

}
