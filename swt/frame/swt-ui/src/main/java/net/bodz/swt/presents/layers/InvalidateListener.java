package net.bodz.swt.presents.layers;

import java.util.EventListener;

public interface InvalidateListener
        extends EventListener {

    void becameInvalid(InvalidateEvent event);

}
